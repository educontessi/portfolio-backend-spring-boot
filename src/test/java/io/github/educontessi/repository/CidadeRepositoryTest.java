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

import io.github.educontessi.model.Cidade;

/**
 * Classe de teste para entidade {@link CidadeRepository}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class CidadeRepositoryTest {

	@Autowired
	private CidadeRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscarCidadePorIbge() {
		// Arranjos
		Cidade entity = getCidade();
		repository.save(entity);

		// Execução
		Optional<Cidade> found = repository.findByIbge(entity.getIbge());
		Cidade cidade = found.get();

		// Resultados
		assertNotNull(cidade);
		assertEquals(cidade.getNome(), entity.getNome());
		assertEquals(cidade.getIbge(), entity.getIbge());

		// Final
		repository.delete(entity);
	}

	@Test
	public void deveListarCidadesPorEstado() {
		// Arranjos
		Long idEstado = 1L;

		// Execução
		List<Cidade> cidades = repository.findByEstadoId(idEstado);

		// Resultados
		assertNotNull(cidades);
		assertFalse(cidades.isEmpty());
		verificaSeTodasCidadesPertenceAoEstado(idEstado, cidades);
	}

	private void verificaSeTodasCidadesPertenceAoEstado(Long idEstado, List<Cidade> cidades) {
		for (Cidade cidade : cidades) {
			assertTrue(cidade.getEstadoId().equals(idEstado));
		}
	}

	private Cidade getCidade() {
		Cidade entity = new Cidade();
		entity.setNome("Integration Test");
		entity.setEstadoId(1L);
		entity.setIbge(123456789);
		return entity;
	}
}
