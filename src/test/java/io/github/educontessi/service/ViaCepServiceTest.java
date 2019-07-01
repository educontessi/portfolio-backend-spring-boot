package io.github.educontessi.service;

import static io.github.educontessi.helpers.util.FuncoesString.removeMascaraDeNumeros;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.model.Cidade;
import io.github.educontessi.model.Estado;
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

	private final String CEP_VALIDO = "01001-000";
	private final String CEP_INVALIDO = "00000-000";
	private final String CEP_CONTEUDO_INVALIDO = "asdaddasdas";

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
		// when(service.requisicaoViaCep(any())).thenReturn(getViaCepJson());
	}

	@Test
	public void deveBuscaCep() {
		// Arranjos
		ViaCepJson compara = getViaCepJson();
		ViaCepJson resultado = null;

		// Execução
		resultado = service.requisicaoViaCep(CEP_VALIDO);

		// Resultados
		assertNotNull(resultado);
		assertEquals(compara.getCep(), removeMascaraDeNumeros(resultado.getCep()));
		assertEquals(compara.getLogradouro(), resultado.getLogradouro());
		assertEquals(compara.getComplemento(), resultado.getComplemento());
		assertEquals(compara.getBairro(), resultado.getBairro());
		assertEquals(compara.getLocalidade(), resultado.getLocalidade());
		assertEquals(compara.getUf(), resultado.getUf());
		assertEquals(compara.getUnidade(), resultado.getUnidade());
		assertEquals(compara.getIbge(), resultado.getIbge());
		assertEquals(compara.getGia(), resultado.getGia());

	}

	@Test
	public void deveRetornarNullQuandoCepForInvalido() {
		// Arranjos
		ViaCepJson resultado = null;

		// Execução
		resultado = service.requisicaoViaCep(CEP_INVALIDO);

		// Resultados
		assertFalse(resultado.isValid());
		assertNull(resultado.getCep());
		assertNull(resultado.getLogradouro());
		assertNull(resultado.getComplemento());
		assertNull(resultado.getBairro());
		assertNull(resultado.getLocalidade());
		assertNull(resultado.getUf());
		assertNull(resultado.getUnidade());
		assertNull(resultado.getIbge());
		assertNull(resultado.getGia());
	}

	@Test
	public void deveRetornarNullQuandoStatusDiferenteDe200() throws IOException {
		// Arranjos
		ViaCepJson resultado = null;
		ViaCepService serviceSpy = Mockito.spy(service);
		doReturn(false).when(serviceSpy).isSucess(any());

		// Execução
		resultado = serviceSpy.requisicaoViaCep(CEP_VALIDO);

		// Resultados
		assertNull(resultado);
		verify(serviceSpy, times(1)).isSucess(any());
		verify(serviceSpy, times(1)).request(any());
	}

	@Test
	public void deveRetornarNullQuandoConteudoDoCepForInvalido() throws IOException {
		// Arranjos
		// VAI FAZER COM QUE A REQUISIÇÃO RETORNE 400
		ViaCepJson resultado = null;
		ViaCepService serviceSpy = Mockito.spy(service);

		// Execução
		resultado = serviceSpy.requisicaoViaCep(CEP_CONTEUDO_INVALIDO);

		// Resultados
		assertNull(resultado);
		verify(serviceSpy, times(1)).isSucess(any());
		verify(serviceSpy, times(1)).request(any());
	}

	@Test
	public void deveRetornarEstado() {
		// Arranjos
		String uf = "SP";
		ViaCepJson resultado = getViaCepJson();
		Estado estadoMock = new Estado();
		estadoMock.setUf(uf);
		doReturn(Optional.of(estadoMock)).when(estadoRepository).findByUf(uf);

		// Execução
		Estado estado = service.getEstado(resultado);

		// Resultados
		assertNotNull(estado);
		assertEquals("SP", estado.getUf());
		verify(estadoRepository, times(1)).findByUf(uf);
	}

	@Test
	public void deveRetornarEstadoNullQuandoNaoEncontrarEstadoPelaUF() {
		// Arranjos
		String uf = "SP";
		ViaCepJson resultado = getViaCepJson();
		doReturn(Optional.empty()).when(estadoRepository).findByUf(uf);

		// Execução
		Estado estado = service.getEstado(resultado);

		// Resultados
		assertNull(estado);
		verify(estadoRepository, times(1)).findByUf(uf);
	}

	@Test
	public void deveRetornarCidade() {
		// Arranjos
		Integer ibge = 3550308;
		ViaCepJson resultado = getViaCepJson();
		Cidade cidadeMock = new Cidade();
		cidadeMock.setIbge(ibge);

		String uf = "SP";
		Estado estadoMock = new Estado();
		estadoMock.setUf(uf);
		cidadeMock.setEstado(estadoMock);

		doReturn(Optional.of(cidadeMock)).when(cidadeRepository).findByIbge(ibge);

		// Execução
		Cidade cidade = service.getCidade(resultado, estadoMock);

		// Resultados
		assertNotNull(cidade);
		assertEquals(ibge, cidade.getIbge());
		verify(cidadeRepository, times(1)).findByIbge(ibge);
	}

	@Test
	public void deveIncluirUmaNovaCidade() {
		// Arranjos
		ViaCepService serviceSpy = Mockito.spy(service);
		Integer ibge = 3550308;
		ViaCepJson resultado = getViaCepJson();

		String uf = "SP";
		Estado estadoMock = new Estado();
		estadoMock.setUf(uf);

		doReturn(Optional.empty()).when(cidadeRepository).findByIbge(ibge);
		doNothing().when(service).incluirCidade(any(), any());

		// Execução
		Cidade cidade = serviceSpy.getCidade(resultado, estadoMock);

		// Resultados
		assertNotNull(cidade);
		assertEquals(ibge, cidade.getIbge());
		verify(cidadeRepository, times(1)).findByIbge(ibge);
		verify(serviceSpy, times(1)).incluirCidade(any(), any());
	}

	private ViaCepJson getViaCepJson() {
		ViaCepJson viaCepJson = new ViaCepJson();
		viaCepJson.setCep(CEP_VALIDO);
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
