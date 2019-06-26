package io.github.educontessi.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste para entidade {@link ViaCepResposta}
 * 
 * @author Eduardo Contessi
 *
 */
public class ViaCepRespostaTest {

	@Before
	public void setUp() {

	}

	@Test
	public void deveRetornarTrueQuandoCepEstaValidoSemMascara() {
		// Arranjos
		ViaCepResposta viaCepResposta = new ViaCepResposta();
		viaCepResposta.setCep("99999999");

		// Resultados
		assertTrue(viaCepResposta.isValid());
	}

	@Test
	public void deveRetornarTrueQuandoCepEstaValidoComMascara() {
		// Arranjos
		ViaCepResposta viaCepResposta = new ViaCepResposta();
		viaCepResposta.setCep("99999-999");

		// Resultados
		assertTrue(viaCepResposta.isValid());
	}
}
