package io.github.educontessi.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Classe de teste para entidade {@link ViaCepJson}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class ViaCepJsonTest {

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void deveRetornarFalseQuandoCepEstaNulo() {
		// Arranjos
		ViaCepJson viaCepJson = new ViaCepJson();

		// Resultados
		assertFalse(viaCepJson.isValid());
	}

	@Test
	public void deveRetornarFalseQuandoCepEstaVazio() {
		// Arranjos
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep("");

		// Resultados
		assertFalse(viaCepJson.isValid());
	}

	@Test
	public void deveRetornarFalseQuandoTamanhoDoCepEstaInvalido() {
		// Arranjos
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep("9999999");

		// Resultados
		assertFalse(viaCepJson.isValid());
	}

	@Test
	public void deveRetornarTrueQuandoCepEstaValidoSemMascara() {
		// Arranjos
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep("99999999");

		// Resultados
		assertTrue(viaCepJson.isValid());
	}

	@Test
	public void deveRetornarTrueQuandoCepEstaValidoComMascara() {
		// Arranjos
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep("99999-999");

		// Resultados
		assertTrue(viaCepJson.isValid());
	}
}
