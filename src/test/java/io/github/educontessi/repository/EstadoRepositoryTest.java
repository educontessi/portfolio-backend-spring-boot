package io.github.educontessi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.educontessi.model.Estado;

/**
 * Classe de teste para entidade {@link EstadoRepository}
 * 
 * @author Eduardo Contessi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoRepositoryTest {

	@Autowired
	private EstadoRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscarPorUf() {
		// Arranjos
		Estado entity = getEstado();
		repository.save(entity);

		// Execução
		Optional<Estado> found = repository.findByUf(entity.getUf());
		Estado estado = found.get();

		// Resultados
		assertNotNull(estado);
		assertEquals(estado.getNome(), entity.getNome());
		assertEquals(estado.getUf(), entity.getUf());

		// Final
		repository.delete(entity);
	}

	@Test
	public void deveListarEstadosPorPais() {
		// Arranjos
		Long idBrasil = 1L;

		// Execução
		List<Estado> estados = repository.findByPaisId(idBrasil);

		// Resultados
		assertNotNull(estados);
		assertFalse(estados.isEmpty());
		assertTrue(estados.size() == 27);
		verificaEstadosBrasileiros(estados);
		verificaSeOsEstadosPertenceAoPaisDoFiltro(idBrasil, estados);
	}

	private void verificaEstadosBrasileiros(List<Estado> estados) {
		assertTrue(verificaEstados(estados, "AC"));
		assertTrue(verificaEstados(estados, "AL"));
		assertTrue(verificaEstados(estados, "AP"));
		assertTrue(verificaEstados(estados, "AM"));
		assertTrue(verificaEstados(estados, "BA"));
		assertTrue(verificaEstados(estados, "CE"));
		assertTrue(verificaEstados(estados, "DF"));
		assertTrue(verificaEstados(estados, "ES"));
		assertTrue(verificaEstados(estados, "GO"));
		assertTrue(verificaEstados(estados, "MA"));
		assertTrue(verificaEstados(estados, "MT"));
		assertTrue(verificaEstados(estados, "MS"));
		assertTrue(verificaEstados(estados, "MG"));
		assertTrue(verificaEstados(estados, "PA"));
		assertTrue(verificaEstados(estados, "PB"));
		assertTrue(verificaEstados(estados, "PR"));
		assertTrue(verificaEstados(estados, "PE"));
		assertTrue(verificaEstados(estados, "PI"));
		assertTrue(verificaEstados(estados, "RJ"));
		assertTrue(verificaEstados(estados, "RN"));
		assertTrue(verificaEstados(estados, "RS"));
		assertTrue(verificaEstados(estados, "RO"));
		assertTrue(verificaEstados(estados, "RR"));
		assertTrue(verificaEstados(estados, "SC"));
		assertTrue(verificaEstados(estados, "SP"));
		assertTrue(verificaEstados(estados, "SE"));
		assertTrue(verificaEstados(estados, "TO"));
	}

	private void verificaSeOsEstadosPertenceAoPaisDoFiltro(Long idBrasil, List<Estado> estados) {
		for (Estado estado : estados) {
			assertTrue(estado.getPaisId().equals(idBrasil));
		}
	}

	private boolean verificaEstados(List<Estado> estados, String uf) {
		boolean retorno = false;

		for (Estado estado : estados) {
			if (estado.getUf().equals(uf)) {
				retorno = true;
				break;
			}
		}

		return retorno;
	}

	private Estado getEstado() {
		Estado entity = new Estado();
		entity.setNome("Integration Test");
		entity.setPaisId(1L);
		entity.setUf("UF");
		return entity;
	}
}
