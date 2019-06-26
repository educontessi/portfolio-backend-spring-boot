package io.github.educontessi.model;

import static io.github.educontessi.helpers.util.FuncoesString.adicionaMascara;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import io.github.educontessi.helpers.util.TipoMascara;
import io.github.educontessi.model.enumeracoes.Status;
import io.github.educontessi.model.enumeracoes.TipoPessoa;

/**
 * Classe de teste para entidade {@link Pessoa}
 * 
 * @author Eduardo Contessi
 *
 */
public class PessoaTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Pessoa> verificaMensagemBeanValidation;

	private final String CPF = "34654886524";
	private final String CNPJ = "05702348000144";
	private final String CEP = "88888888";

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
	public void deveRetornarViolacoesParaCamposObrigatorios() {
		// Arranjos
		Pessoa pessoa = new Pessoa();

		String mensagem1 = "nomeRazao é obrigatório(a)";
		String mensagem2 = "tipoPessoa é obrigatório(a)";
		String mensagem3 = "cpfCnpj é obrigatório(a)";
		String mensagem4 = "status é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Pessoa>> violacoes = validator.validate(pessoa);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(4, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem3));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem4));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMinimosDeCamposObrigatorios() {
		// Arranjos
		Pessoa pessoa = getPessoaFisica();
		pessoa.setNomeRazao("BR");
		pessoa.setCidadeId(1L);

		String mensagem1 = "nomeRazao deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Pessoa>> violacoes = validator.validate(pessoa);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMaximosDeCamposObrigatorios() {
		// Arranjos
		Pessoa pessoa = getPessoaFisica();
		pessoa.setNomeRazao(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB");

		String mensagem1 = "nomeRazao deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Pessoa>> violacoes = validator.validate(pessoa);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
	public void deveRetornarTipoDaMascaraCpf() {
		// Arranjos
		Pessoa pessoa = getPessoaFisica();

		// Execução
		TipoMascara tipoMascara = pessoa.getTipoMascara();

		// Resultados
		assertNotNull(tipoMascara);
		assertEquals(TipoPessoa.FISICA, pessoa.getTipoPessoa());
		assertEquals(tipoMascara, TipoMascara.CPF);
	}

	@Test
	public void deveRetornarTipoDaMascaraCnpj() {
		// Arranjos
		Pessoa pessoa = getPessoaJuridica();

		// Execução
		TipoMascara tipoMascara = pessoa.getTipoMascara();

		// Resultados
		assertNotNull(tipoMascara);
		assertEquals(TipoPessoa.JURIDICA, pessoa.getTipoPessoa());
		assertEquals(tipoMascara, TipoMascara.CNPJ);
	}

	@Test
	public void deveRetornarCpfComMascara() {
		// Arranjos
		Pessoa pessoa = null;

		// Execução
		pessoa = getPessoaFisica();

		// Resultados
		assertNotNull(pessoa.getCpfCnpj());
		assertEquals(TipoPessoa.FISICA, pessoa.getTipoPessoa());
		assertEquals(pessoa.getCpfCnpj(), adicionaMascara(TipoMascara.CPF, this.CPF));
	}

	@Test
	public void deveRetornarCnpjComMascara() {
		// Arranjos
		Pessoa pessoa = null;

		// Execução
		pessoa = getPessoaJuridica();

		// Resultados
		assertNotNull(pessoa.getCpfCnpj());
		assertEquals(TipoPessoa.JURIDICA, pessoa.getTipoPessoa());
		assertEquals(pessoa.getCpfCnpj(), adicionaMascara(TipoMascara.CNPJ, this.CNPJ));
	}

	@Test
	public void deveRetornarCepComMascara() {
		// Arranjos
		Pessoa pessoa = null;

		// Execução
		pessoa = getPessoaJuridica();

		// Resultados
		assertNotNull(pessoa.getCep());
		assertEquals(TipoPessoa.JURIDICA, pessoa.getTipoPessoa());
		assertEquals(pessoa.getCep(), adicionaMascara(TipoMascara.CEP, this.CEP));
	}

	@Test
	public void deveSetarBairroIdQuandoSetarBairro() {
		// Arranjos
		Long bairroId = 1L;
		Pessoa pessoa = getPessoaFisica();
		Bairro bairro = new Bairro();
		bairro.setId(bairroId);

		// Execução
		pessoa.setBairro(bairro);

		// Resultados
		assertNotNull(pessoa.getBairroId());
		assertEquals(bairroId, pessoa.getBairroId());
		assertEquals(bairroId, pessoa.getBairro().getId());
	}

	@Test
	public void deveSetarRuaIdQuandoSetarRua() {
		// Arranjos
		Long ruaId = 1L;
		Pessoa pessoa = getPessoaFisica();
		Rua rua = new Rua();
		rua.setId(ruaId);

		// Execução
		pessoa.setRua(rua);

		// Resultados
		assertNotNull(pessoa.getRuaId());
		assertEquals(ruaId, pessoa.getRuaId());
		assertEquals(ruaId, pessoa.getRua().getId());
	}

	@Test
	public void deveSetarCidadeIdQuandoSetarCidade() {
		// Arranjos
		Long cidadeId = 1L;
		Pessoa pessoa = getPessoaFisica();
		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		// Execução
		pessoa.setCidade(cidade);

		// Resultados
		assertNotNull(pessoa.getCidadeId());
		assertEquals(cidadeId, pessoa.getCidadeId());
		assertEquals(cidadeId, pessoa.getCidade().getId());
	}

	@Test
	public void deveRetornarViolacoesParaEmailInvalido() {
		// Arranjos
		Pessoa pessoa = getPessoaFisica();
		pessoa.setNomeRazao("Teste");
		pessoa.setEmail("teste");

		String mensagem1 = "não é um endereço de e-mail";

		// Execução
		Set<ConstraintViolation<Pessoa>> violacoes = validator.validate(pessoa);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhoMaximoDoEmail() {
		// Arranjos
		Pessoa pessoa = getPessoaFisica();
		pessoa.setNomeRazao("Teste");
		pessoa.setEmail(
				" contact-admin-hello-webmaster-info-services-peter-crazy-but-oh-so-ubber-cool-english-alphabet-loverer-abcdefghijklmnopqrstuvwxyz@please-try-to.send-me-an-email-if-you-can-possibly-begin-to-remember-this-coz.this-is-the-longest-email-address-known-to-man-but-to-be-honest.this-is-such-a-stupidly-long-sub-domain-it-could-go-on-forever.pacraig.com");

		String mensagem1 = "não é um endereço de e-mail";
		String mensagem2 = "E-mail não pode exceder mais que 100";

		// Execução
		Set<ConstraintViolation<Pessoa>> violacoes = validator.validate(pessoa);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(2, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
	}

	private Pessoa getPessoaFisica() {
		Pessoa pessoa = new Pessoa();
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		pessoa.setStatus(Status.ATIVO);
		pessoa.setCpfCnpj(this.CPF);
		pessoa.setCep(this.CEP);
		return pessoa;
	}

	private Pessoa getPessoaJuridica() {
		Pessoa pessoa = new Pessoa();
		pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
		pessoa.setStatus(Status.ATIVO);
		pessoa.setCpfCnpj(this.CNPJ);
		pessoa.setCep(this.CEP);
		return pessoa;
	}

}
