package io.github.educontessi.domain.service;

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

import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.repository.RuaRepository;

/**
 * Classe de teste para entidade {@link RuaService}
 * 
 * @author Eduardo Possamai Contessi
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
		Optional<Rua> optional = getRua();
		// Rua entity = optional.get();
		doReturn(optional).when(serviceSpy).findById(this.id);

		// Execução
		// serviceSpy.update(this.id, entity);

		// Resultados
		// verify(serviceSpy, times(1)).update(this.id, entity);
		verify(serviceSpy, times(1)).findById(this.id);
	}

	private Optional<Rua> getRua() {
		Rua rua = new Rua();
		rua.setId(this.id);
		rua.setNome("Teste");
		rua.setCidadeId(this.id);
		return Optional.of(rua);
	}

}
