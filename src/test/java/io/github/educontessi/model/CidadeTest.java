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
 * Classe de teste para entidade {@link Cidade}
 * 
 * @author Eduardo Contessi
 *
 */
public class CidadeTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Cidade> verificaMensagemBeanValidation;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
	@DisplayName("Deve retornar violações para campos obrigatórios")
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
	@DisplayName("Deve retornar violações para tamanhos mínimos de campos obrigatórios")
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
	@DisplayName("Deve retornar violações para tamanhos máximos de campos obrigatórios")
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

	@DisplayName("Deve setar estadoId quando setar o estado")
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
