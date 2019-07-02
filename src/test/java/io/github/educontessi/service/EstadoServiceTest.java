package io.github.educontessi.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import io.github.educontessi.model.Estado;
import io.github.educontessi.repository.EstadoRepository;

/**
 * Classe de teste para entidade {@link EstadoService}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class EstadoServiceTest {

	private final Long id = 1L;

	@Mock
	private EstadoRepository repository;

	@InjectMocks
	private EstadoService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockEstado();
	}

	private void mockEstado() {
		when(repository.findById(any())).thenReturn(getEstado());
	}

	@Test
	public void deveAtualizarEstado() {
		// Arranjos
		EstadoService serviceSpy = Mockito.spy(service);
		Estado entity = getEstado().get();

		// Execução
		serviceSpy.update(this.id, entity);

		// Resultados
		verify(serviceSpy, times(1)).update(any(), any());
		verify(repository, times(1)).findById(any());
	}

	@Test
	public void deveRetornarArrayComPropriedadesIgnoradas() {
		// Arranjos
		EstadoService serviceSpy = Mockito.spy(service);
		String[] propriedadesIgnoradas = null;
		String[] resultadoEsperado = { "id", "pais" };

		// Execução
		propriedadesIgnoradas = serviceSpy.getIgnoreProperties();

		// Resultados
		verify(serviceSpy, times(1)).getIgnoreProperties();
		assertNotNull(propriedadesIgnoradas);
		assertEquals(2, propriedadesIgnoradas.length);
		assertArrayEquals(resultadoEsperado, propriedadesIgnoradas);
	}

	private Optional<Estado> getEstado() {
		Estado estado = new Estado();
		estado.setId(this.id);
		estado.setNome("Teste");
		estado.setPaisId(this.id);
		return Optional.of(estado);
	}

}
