package io.github.educontessi.api.dataconverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Classe de teste para {@link PaisV1DataConverter}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class PaisV1DataConverterTest {

	@InjectMocks
	private PaisV1DataConverter converter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveRetornarArrayComPropriedadesIgnoradas() {
		// Arranjos
		PaisV1DataConverter converterSpy = Mockito.spy(converter);
		String[] propriedadesIgnoradas = null;
		String[] resultadoEsperado = { "id", "created", "changed", "deletedDate" };

		// Execução
		propriedadesIgnoradas = converterSpy.getIgnoreProperties();

		// Resultados
		verify(converterSpy, times(1)).getIgnoreProperties();
		assertNotNull(propriedadesIgnoradas);
		assertEquals(4, propriedadesIgnoradas.length);
		assertArrayEquals(resultadoEsperado, propriedadesIgnoradas);
	}
}
