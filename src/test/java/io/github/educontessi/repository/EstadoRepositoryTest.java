package io.github.educontessi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.model.Estado;

/**
 * Classe de teste para entidade {@link EstadoRepository}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class EstadoRepositoryTest {

	@Autowired
	private EstadoRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscarEstadoPorUf() {
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
		verificaSeTodosEstadosPertenceAoPais(idBrasil, estados);
	}

	private void verificaSeTodosEstadosPertenceAoPais(Long idBrasil, List<Estado> estados) {
		for (Estado estado : estados) {
			assertTrue(estado.getPaisId().equals(idBrasil));
		}
	}

	private Estado getEstado() {
		Estado entity = new Estado();
		entity.setNome("Integration Test");
		entity.setPaisId(1L);
		entity.setUf("UF");
		return entity;
	}
}
