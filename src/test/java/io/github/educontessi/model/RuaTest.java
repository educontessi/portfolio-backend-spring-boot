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
 * Classe de teste para entidade {@link Rua}
 * 
 * @author Eduardo Contessi
 *
 */
public class RuaTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Rua> verificaMensagemBeanValidation;

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
		Rua rua = new Rua();

		String mensagem1 = "nome é obrigatório(a)";
		String mensagem2 = "cidadeId é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Rua>> violacoes = validator.validate(rua);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(2, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
	}

	@Test
	@DisplayName("Deve retornar violações para tamanhos mínimos de campos obrigatórios")
	public void deveRetornarViolacoesParaTamanhosMinimosDeCamposObrigatorios() {
		// Arranjos
		Rua rua = new Rua();
		rua.setNome("BR"); // MINIMO 3
		rua.setCidadeId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Rua>> violacoes = validator.validate(rua);

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
		Rua rua = new Rua();
		rua.setNome(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB"); // MAXIMO
																															// 100
		rua.setCidadeId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";

		// Execução
		Set<ConstraintViolation<Rua>> violacoes = validator.validate(rua);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(1, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
	}

	@DisplayName("Deve setar cidadeId quando setar o cidade")
	@Test
	public void deveSetarCidadeIdQuandoSetarCidade() {
		// Arranjos
		Long cidadeId = 1L;
		Rua rua = new Rua();
		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		// Execução
		rua.setCidade(cidade);

		// Resultados
		assertNotNull(rua.getCidadeId());
		assertEquals(cidadeId, rua.getCidadeId());
		assertEquals(cidadeId, rua.getCidade().getId());
	}

}
