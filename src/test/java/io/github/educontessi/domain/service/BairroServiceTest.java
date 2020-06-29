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

import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.repository.BairroRepository;

/**
 * Classe de teste para {@link BairroService}
 * 
 * @author Eduardo Possamai Contessi
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
		Optional<Bairro> optional = getBairro();
		// Bairro entity = optional.get();
		doReturn(optional).when(serviceSpy).findById(this.id);

		// Execução
		// serviceSpy.update(this.id, entity);

		// Resultados
		// verify(serviceSpy, times(1)).update(this.id, entity);
		verify(serviceSpy, times(1)).findById(this.id);
	}

	private Optional<Bairro> getBairro() {
		Bairro bairro = new Bairro();
		bairro.setId(this.id);
		bairro.setNome("Teste");
		bairro.setCidadeId(this.id);
		return Optional.of(bairro);
	}

}
