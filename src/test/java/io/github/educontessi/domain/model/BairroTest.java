package io.github.educontessi.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Classe de teste para entidade {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class BairroTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Bairro> verificaMensagemBeanValidation;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
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
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
	}

	@Test
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
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
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
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

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

}
