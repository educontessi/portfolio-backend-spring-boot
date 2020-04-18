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
 * Classe de teste para entidade {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class EstadoTest {

	private Validator validator;
	private VerificaMensagemBeanValidation<Estado> verificaMensagemBeanValidation;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		verificaMensagemBeanValidation = new VerificaMensagemBeanValidation<>();
	}

	@Test
	public void deveRetornarViolacoesParaCamposObrigatorios() {
		// Arranjos
		Estado estado = new Estado();

		String mensagem1 = "nome é obrigatório(a)";
		String mensagem2 = "uf é obrigatório(a)";
		String mensagem3 = "paisId é obrigatório(a)";

		// Execução
		Set<ConstraintViolation<Estado>> violacoes = validator.validate(estado);

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
		Estado estado = new Estado();
		estado.setNome("BR"); // MINIMO 3
		estado.setUf("B"); // MINIMO 2
		estado.setPaisId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";
		String mensagem2 = "uf deve ter o tamanho entre 2 e 10";

		// Execução
		Set<ConstraintViolation<Estado>> violacoes = validator.validate(estado);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(2, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
	}

	@Test
	public void deveRetornarViolacoesParaTamanhosMaximosDeCamposObrigatorios() {
		// Arranjos
		Estado estado = new Estado();
		estado.setNome(
				"BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRB"); // MAXIMO
																															// 100
		estado.setUf("BRBRBRBRBRB"); // MAXIMO 10
		estado.setPaisId(1L);

		String mensagem1 = "nome deve ter o tamanho entre 3 e 100";
		String mensagem2 = "uf deve ter o tamanho entre 2 e 10";

		// Execução
		Set<ConstraintViolation<Estado>> violacoes = validator.validate(estado);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(2, violacoes.size());
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem1));
		assertTrue(verificaMensagemBeanValidation.verificaMensagem(violacoes, mensagem2));
	}

	@Test
	public void deveSetarPaisIdQuandoSetarPais() {
		// Arranjos
		Long paisId = 1L;
		Estado estado = new Estado();
		Pais pais = new Pais();
		pais.setId(paisId);

		// Execução
		estado.setPais(pais);

		// Resultados
		assertNotNull(estado.getPaisId());
		assertEquals(paisId, estado.getPaisId());
		assertEquals(paisId, estado.getPais().getId());
	}

}
