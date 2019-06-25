package io.github.educontessi.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.educontessi.model.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoRepositoryIntegrationTest {

	@Autowired
	private EstadoRepository repository;

	@Before
	public void setUp() {
	}

	@Test
	public void deveBuscarPorUf() {
		// Arranjos
		Estado entity = new Estado();
		entity.setNome("Integration Test");
		entity.setPaisId(1L);
		entity.setUf("UF");
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

		// Execução
		List<Estado> estados = repository.findByPaisId(1L);

		// Resultados
		assertNotNull(estados);
		assertFalse(estados.isEmpty());
		assertTrue(estados.size() == 27);
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
}
