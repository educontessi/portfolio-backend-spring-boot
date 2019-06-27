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

import io.github.educontessi.model.Rua;
import io.github.educontessi.repository.RuaRepository;

/**
 * Classe de teste para entidade {@link RuaService}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class RuaServiceTest {

	private final Long id = 1L;

	@Mock
	private RuaRepository repository;

	@InjectMocks
	private RuaService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockRua();
	}

	private void mockRua() {
		when(repository.findById(any())).thenReturn(getRua());
	}

	@Test
	public void deveAtualizarRua() {
		// Arranjos
		RuaService serviceSpy = Mockito.spy(service);
		Rua entity = getRua().get();

		// Execução
		serviceSpy.update(this.id, entity);

		// Resultados
		verify(serviceSpy, times(1)).update(any(), any());
		verify(repository, times(1)).findById(any());
	}

	@Test
	public void deveRetornarArrayComPropriedadesIgnoradas() {
		// Arranjos
		RuaService serviceSpy = Mockito.spy(service);
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

	private Optional<Rua> getRua() {
		Rua rua = new Rua();
		rua.setId(this.id);
		rua.setNome("Teste");
		rua.setCidadeId(this.id);
		return Optional.of(rua);
	}

}
