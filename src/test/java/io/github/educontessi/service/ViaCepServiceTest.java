package io.github.educontessi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.model.ViaCepJson;
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
		mockViaCepJson();
	}

	private void mockViaCepJson() {
		when(service.requisicaoViaCep(any())).thenReturn(getViaCepJson());
	}

	@Test
	public void deveAtualizarPais() {

	}

	private ViaCepJson getViaCepJson() {
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep("01001-000");
		viaCepJson.setLogradouro("Praça da Sé");
		viaCepJson.setComplemento("lado ímpar");
		viaCepJson.setBairro("Sé");
		viaCepJson.setLocalidade("São Paulo");
		viaCepJson.setUf("SP");
		viaCepJson.setUnidade("");
		viaCepJson.setIbge("3550308");
		viaCepJson.setGia("1004");
		return viaCepJson;
	}

}
