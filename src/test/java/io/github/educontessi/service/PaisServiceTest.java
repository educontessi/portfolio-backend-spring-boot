package io.github.educontessi.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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
import org.springframework.dao.EmptyResultDataAccessException;

import io.github.educontessi.model.Pais;
import io.github.educontessi.repository.PaisRepository;

/**
 * Classe de teste para entidade {@link PaisService}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class PaisServiceTest {

	private final Long id = 1L;

	@Mock
	private PaisRepository repository;

	@InjectMocks
	private PaisService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockPais();
	}

	private void mockPais() {
		when(repository.findById(any())).thenReturn(getPais());
	}

	@Test
	public void deveAtualizarPais() {
		// Arranjos
		PaisService serviceSpy = Mockito.spy(service);
		Optional<Pais> optional = getPais();
		Pais entity = optional.get();
		doReturn(optional).when(serviceSpy).findById(this.id);

		// Execução
		serviceSpy.update(this.id, entity);

		// Resultados
		verify(serviceSpy, times(1)).update(this.id, entity);
		verify(serviceSpy, times(1)).findById(this.id);
	}

	@Test
	public void deveRetornarArrayComPropriedadesIgnoradas() {
		// Arranjos
		PaisService serviceSpy = Mockito.spy(service);
		String[] propriedadesIgnoradas = null;
		String[] resultadoEsperado = { "id" };

		// Execução
		propriedadesIgnoradas = serviceSpy.getIgnoreProperties();

		// Resultados
		verify(serviceSpy, times(1)).getIgnoreProperties();
		assertNotNull(propriedadesIgnoradas);
		assertEquals(1, propriedadesIgnoradas.length);
		assertArrayEquals(resultadoEsperado, propriedadesIgnoradas);
	}

	@Test
	public void deveLancarExcecaoQuandoNaoEstiverPresenteObjeto() {
		// Arranjos
		String resultadoEsperado = "Incorrect result size: expected 1, actual 0";
		Optional<Pais> optionalSaved = Optional.empty();

		// Execução
		EmptyResultDataAccessException exception = assertThrows(EmptyResultDataAccessException.class, () -> {
			service.isPresent(optionalSaved);
		});

		// Resultados
		assertNotNull(exception);
		assertEquals(resultadoEsperado, exception.getMessage());
	}

	@Test
	public void naoDeveLancarExcecaoQuandoEstiverPresenteObjeto() {
		// Arranjos
		PaisService serviceSpy = Mockito.spy(service);
		Optional<Pais> optionalSaved = Optional.of(new Pais());
		boolean exception = false;

		// Execução
		try {
			serviceSpy.isPresent(optionalSaved);
		} catch (Exception e) {
			exception = true;
		}

		// Resultados
		verify(serviceSpy, times(1)).isPresent(optionalSaved);
		assertFalse(exception);
	}

	private Optional<Pais> getPais() {
		Pais pais = new Pais();
		pais.setId(this.id);
		pais.setNome("Teste");
		return Optional.of(pais);
	}

}
