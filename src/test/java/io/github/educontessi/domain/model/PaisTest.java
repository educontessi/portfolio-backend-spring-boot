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
 * Classe de teste para entidade {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class PaisTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Pais> verificaMensagemBeanValidation;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
	public void deveRetornarViolacoesParaCamposObrigatorios() {
		// Arranjos
		Pais pais = new Pais();

		String mensagem1 = "nome é obrigatório(a)";
		String mensagem2 = "bacen é obrigatório(a)";
		String mensagem3 = "sigla é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Pais>> violacoes = validator.validate(pais);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(3, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem3));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMinimosDeCamposObrigatorios() {
		// Arranjos
		Pais pais = new Pais();
		pais.setNome("BR"); // MINIMO 3
		pais.setSigla("B"); // MINIMO 2
		pais.setBacen("B"); // MINIMO 2

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";
		String mensagem2 = "bacen deve ter o tamanho entre 2 e 10";
		String mensagem3 = "sigla deve ter o tamanho entre 2 e 10";

		// Execução
		Set<ConstraintViolation<Pais>> violacoes = validator.validate(pais);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(3, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem3));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMaximosDeCamposObrigatorios() {
		// Arranjos
		Pais pais = new Pais();
		pais.setNome(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB"); // MAXIMO
																															// 100
		pais.setSigla("BRBRBRBRBRB"); // MAXIMO 10
		pais.setBacen("BRBRBRBRBRB"); // MAXIMO 10

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";
		String mensagem2 = "bacen deve ter o tamanho entre 2 e 10";
		String mensagem3 = "sigla deve ter o tamanho entre 2 e 10";

		// Execução
		Set<ConstraintViolation<Pais>> violacoes = validator.validate(pais);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(3, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem3));
	}

}
