package io.github.educontessi.domain.service;

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

import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.repository.PaisRepository;

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
		when(repository.findById(any())).thenReturn(Optional.of(getPais()));
	}

	@Test
	public void deveListarTodos() {
		// Arranjos
		PaisService serviceSpy = Mockito.spy(service);

		// Execução
		serviceSpy.findAll();

		// Resultados
		verify(repository, times(1)).findAll();
	}

	@Test
	public void deveLPesquisar() {
		// Arranjos
		PaisService serviceSpy = Mockito.spy(service);

		// Execução
		serviceSpy.search(any(), any());

		// Resultados
		verify(repository, times(1)).search(any(), any());
	}

	@Test
	public void deveAtualizarPais() {
		// Arranjos
		// PaisService serviceSpy = Mockito.spy(service);
		// Pais entity = getPais();

		// Execução
		// serviceSpy.update(this.id, entity);

		// Resultados
		// verify(serviceSpy, times(1)).update(this.id, entity);
	}

	@Test
	public void deveLancarExcecaoQuandoNaoEstiverPresenteObjeto() {
		// Arranjos
		// String resultadoEsperado = "Incorrect result size: expected 1, actual 0";
		// Optional<Pais> optionalSaved = Optional.empty();

		// Execução
		// EmptyResultDataAccessException exception =
		// assertThrows(EmptyResultDataAccessException.class, () -> {
		// service.isPresent(optionalSaved);
		// });

		// Resultados
		// assertNotNull(exception);
		// assertEquals(resultadoEsperado, exception.getMessage());
	}

	@Test
	public void naoDeveLancarExcecaoQuandoEstiverPresenteObjeto() {
		// Arranjos
		/*
		 * PaisService serviceSpy = Mockito.spy(service); Optional<Pais> optionalSaved =
		 * Optional.of(new Pais()); boolean exception = false;
		 * 
		 * // Execução try { serviceSpy.isPresent(optionalSaved); } catch (Exception e)
		 * { exception = true; }
		 * 
		 * // Resultados verify(serviceSpy, times(1)).isPresent(optionalSaved);
		 * assertFalse(exception);
		 */
	}

	private Pais getPais() {
		Pais pais = new Pais();
		pais.setId(this.id);
		pais.setNome("Teste");
		return pais;
	}

}
