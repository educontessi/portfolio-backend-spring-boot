package io.github.educontessi.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste para entidade {@link ViaCepJson}
 * 
 * @author Eduardo Contessi
 *
 */
public class ViaCepJsonTest {

	@Before
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
