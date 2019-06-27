package io.github.educontessi.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.model.Bairro;
import io.github.educontessi.repository.BairroRepository;

/**
 * Classe de teste para entidade {@link BairroService}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class BairroServiceTest {

	private final Long id = 1L;

	@Mock
	private BairroRepository repository;

	@InjectMocks
	private BairroService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockBairro();
	}

	private void mockBairro() {
		when(repository.findById(any())).thenReturn(getBairro());
	}

	@Test
	public void deveAtualizarBairro() {
		// Arranjos
		BairroService serviceSpy = Mockito.spy(service);
		Bairro entity = getBairro().get();

		// Execução
		serviceSpy.update(this.id, entity);

		// Resultados
		verify(serviceSpy, times(1)).update(any(), any());
		verify(repository, times(1)).findById(any());
	}

	@Test
	public void deveRetornarArrayComPropriedadesIgnoradas() {
		// Arranjos
		BairroService serviceSpy = Mockito.spy(service);
		String[] propriedadesIgnoradas = null;
		String[] resultadoEsperado = { "id", "cidade" };

		// Execução
		propriedadesIgnoradas = serviceSpy.getIgnoreProperties();

		// Resultados
		verify(serviceSpy, times(1)).getIgnoreProperties();
		assertNotNull(propriedadesIgnoradas);
		assertEquals(2, propriedadesIgnoradas.length);
		assertArrayEquals(resultadoEsperado, propriedadesIgnoradas);
	}

	private Optional<Bairro> getBairro() {
		Bairro bairro = new Bairro();
		bairro.setId(this.id);
		bairro.setNome("Teste");
		bairro.setCidadeId(this.id);
		return Optional.of(bairro);
	}

}
