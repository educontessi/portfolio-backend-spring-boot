package io.github.educontessi.model;

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
import org.junit.jupiter.api.DisplayName;

/**
 * Classe de teste para entidade {@link Bairro}
 * 
 * @author Eduardo Contessi
 *
 */
public class BairroTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@DisplayName("Deve retornar violações para campos obrigatórios")
	public void deveRetornarViolacoesParaCamposObrigatorios() {
		// Arranjos
		Bairro bairro = new Bairro();

		String mensagem1 = "nome é obrigatório(a)";
		String mensagem2 = "cidadeId é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Bairro>> violacoes = validator.validate(bairro);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(2, violacoes.size());
		assertTrue(verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagem(violacoes, mensagem2));
	}

	@Test
	@DisplayName("Deve retornar violações para tamanhos mínimos de campos obrigatórios")
	public void deveRetornarViolacoesParaTamanhosMinimosDeCamposObrigatorios() {
		// Arranjos
		Bairro bairro = new Bairro();
		bairro.setNome("BR"); // MINIMO 3
		bairro.setCidadeId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Bairro>> violacoes = validator.validate(bairro);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagem(violacoes, mensagem1));
	}

	@Test
	@DisplayName("Deve retornar violações para tamanhos máximos de campos obrigatórios")
	public void deveRetornarViolacoesParaTamanhosMaximosDeCamposObrigatorios() {
		// Arranjos
		Bairro bairro = new Bairro();
		bairro.setNome(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB"); // MAXIMO
																															// 100
		bairro.setCidadeId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Bairro>> violacoes = validator.validate(bairro);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagem(violacoes, mensagem1));
	}

	@DisplayName("Deve setar cidadeId quando setar o cidade")
	@Test
	public void deveSetarCidadeIdQuandoSetarCidade() {
		// Arranjos
		Long cidadeId = 1L;
		Bairro bairro = new Bairro();
		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		// Execução
		bairro.setCidade(cidade);

		// Resultados
		assertNotNull(bairro.getCidadeId());
		assertEquals(cidadeId, bairro.getCidadeId());
		assertEquals(cidadeId, bairro.getCidade().getId());
	}

	private boolean verificaMensagem(Set<ConstraintViolation<Bairro>> violacoes, String mensagemComparar) {
		boolean retorno = false;
		for (ConstraintViolation<Bairro> violacao : violacoes) {
			if (getMensagemViolacao(violacao).equals(mensagemComparar)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	private String getMensagemViolacao(ConstraintViolation<Bairro> violacao) {
		System.out.println(violacao.getMessage().replace("{0}", violacao.getPropertyPath().toString()));
		return violacao.getMessage().replace("{0}", violacao.getPropertyPath().toString());
	}

}
