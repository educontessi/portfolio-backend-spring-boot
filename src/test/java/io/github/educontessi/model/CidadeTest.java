package io.github.educontessi.model;

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
 * Classe de teste para entidade {@link Cidade}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class CidadeTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Cidade> verificaMensagemBeanValidation;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
	public void deveRetornarViolacoesParaCamposObrigatorios() {
		// Arranjos
		Cidade cidade = new Cidade();

		String mensagem1 = "nome é obrigatório(a)";
		String mensagem2 = "estadoId é obrigatório(a)";
		String mensagem3 = "ibge é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Cidade>> violacoes = validator.validate(cidade);

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
		Cidade cidade = new Cidade();
		cidade.setNome("BR"); // MINIMO 3
		cidade.setIbge(12345);
		cidade.setEstadoId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Cidade>> violacoes = validator.validate(cidade);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMaximosDeCamposObrigatorios() {
		// Arranjos
		Cidade cidade = new Cidade();
		cidade.setNome(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB"); // MAXIMO
																															// 100
		cidade.setIbge(12345);
		cidade.setEstadoId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Cidade>> violacoes = validator.validate(cidade);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@Test
	public void deveSetarEstadoIdQuandoSetarEstado() {
		// Arranjos
		Long estadoId = 1L;
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		estado.setId(estadoId);

		// Execução
		cidade.setEstado(estado);

		// Resultados
		assertNotNull(cidade.getEstadoId());
		assertEquals(estadoId, cidade.getEstadoId());
		assertEquals(estadoId, cidade.getEstado().getId());
	}

}
