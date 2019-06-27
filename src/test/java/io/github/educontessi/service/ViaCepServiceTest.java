package io.github.educontessi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.repository.BairroRepository;
import io.github.educontessi.repository.CidadeRepository;
import io.github.educontessi.repository.EstadoRepository;
import io.github.educontessi.repository.RuaRepository;

/**
 * Classe de teste para entidade {@link ViaCepService}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class ViaCepServiceTest {

	@Mock
	private RuaRepository ruaRepository;

	@Mock
	private BairroRepository bairroRepository;

	@Mock
	private CidadeRepository cidadeRepository;

	@Mock
	private EstadoRepository estadoRepository;

	@InjectMocks
	private ViaCepService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveAtualizarPais() {

	}

}
