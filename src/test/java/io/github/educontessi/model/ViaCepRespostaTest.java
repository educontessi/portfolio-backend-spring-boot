package io.github.educontessi.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Classe de teste para entidade {@link ViaCepResposta}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class ViaCepRespostaTest {

	@BeforeEach
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
