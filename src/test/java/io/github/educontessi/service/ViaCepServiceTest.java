package io.github.educontessi.service;

import static io.github.educontessi.helpers.util.FuncoesString.removeMascaraDeNumeros;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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

import io.github.educontessi.model.Bairro;
import io.github.educontessi.model.Cidade;
import io.github.educontessi.model.Estado;
import io.github.educontessi.model.Rua;
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

		// Execução
		Cidade cidade = serviceSpy.getCidade(resultado, estadoMock);

		// Resultados
		assertNotNull(cidade);
		assertEquals(ibge, cidade.getIbge());
		assertEquals(resultado.getLocalidade(), cidade.getNome());
		verify(cidadeRepository, times(1)).findByIbge(ibge);
		verify(cidadeRepository, times(1)).save(any());
		verify(serviceSpy, times(1)).incluirCidade(any(), any());
	}

	@Test
	public void deveRetornarBairro() {
		// Arranjos
		Long cidadeId = 1L;
		ViaCepJson resultado = getViaCepJson();

		Bairro bairroMock = new Bairro();
		bairroMock.setNome(resultado.getBairro());
		bairroMock.setCidadeId(cidadeId);

		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		doReturn(Optional.of(bairroMock)).when(bairroRepository).findByNomeAndCidadeId(resultado.getBairro(), cidadeId);

		// Execução
		Bairro bairro = service.getBairro(resultado, cidade);

		// Resultados
		assertNotNull(bairro);
		assertEquals(cidadeId, bairro.getCidadeId());
		assertEquals(resultado.getBairro(), bairro.getNome());
		verify(bairroRepository, times(1)).findByNomeAndCidadeId(resultado.getBairro(), cidadeId);
	}

	@Test
	public void deveIncluirUmNovoBairro() {
		// Arranjos
		ViaCepService serviceSpy = Mockito.spy(service);
		Long cidadeId = 1L;
		ViaCepJson resultado = getViaCepJson();

		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		doReturn(Optional.empty()).when(bairroRepository).findByNomeAndCidadeId(resultado.getBairro(), cidadeId);

		// Execução
		Bairro bairro = serviceSpy.getBairro(resultado, cidade);

		// Resultados
		assertNotNull(bairro);
		assertEquals(cidadeId, bairro.getCidadeId());
		assertEquals(resultado.getBairro(), bairro.getNome());
		verify(bairroRepository, times(1)).findByNomeAndCidadeId(resultado.getBairro(), cidadeId);
		verify(bairroRepository, times(1)).save(any());
		verify(serviceSpy, times(1)).incluirBairro(any(), any());
	}

	@Test
	public void deveRetornarRua() {
		// Arranjos
		Long cidadeId = 1L;
		ViaCepJson resultado = getViaCepJson();

		Rua ruaMock = new Rua();
		ruaMock.setNome(resultado.getLogradouro());
		ruaMock.setCidadeId(cidadeId);

		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		doReturn(Optional.of(ruaMock)).when(ruaRepository).findByNomeAndCidadeId(resultado.getLogradouro(), cidadeId);

		// Execução
		Rua rua = service.getRua(resultado, cidade);

		// Resultados
		assertNotNull(rua);
		assertEquals(cidadeId, rua.getCidadeId());
		assertEquals(resultado.getLogradouro(), rua.getNome());
		verify(ruaRepository, times(1)).findByNomeAndCidadeId(any(), any());
	}

	@Test
	public void deveIncluirUmaNovaRua() {
		// Arranjos
		ViaCepService serviceSpy = Mockito.spy(service);
		Long cidadeId = 1L;
		ViaCepJson resultado = getViaCepJson();

		Cidade cidade = new Cidade();
		cidade.setId(cidadeId);

		doReturn(Optional.empty()).when(ruaRepository).findByNomeAndCidadeId(resultado.getLogradouro(), cidadeId);

		// Execução
		Rua rua = serviceSpy.getRua(resultado, cidade);

		// Resultados
		assertNotNull(rua);
		assertEquals(cidadeId, rua.getCidadeId());
		assertEquals(resultado.getLogradouro(), rua.getNome());
		verify(ruaRepository, times(1)).findByNomeAndCidadeId(any(), any());
		verify(ruaRepository, times(1)).save(any());
		verify(serviceSpy, times(1)).incluirRua(any(), any());
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
