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

import io.github.educontessi.model.Bairro;

/**
 * Classe de teste para entidade {@link BairroRepository}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class BairroRepositoryTest {

	@Autowired
	private BairroRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscaBairrorPorNome() {
		// Arranjos
		Bairro entity = getBairro();
		repository.save(entity);

		// Execução
		Optional<Bairro> found = repository.findByNomeAndCidadeId(entity.getNome(), entity.getCidadeId());
		Bairro bairro = found.get();

		// Resultados
		assertNotNull(bairro);
		assertEquals(bairro.getNome(), entity.getNome());
		assertEquals(bairro.getCidadeId(), entity.getCidadeId());

		// Final
		repository.delete(entity);
	}

	@Test
	public void deveListarBairrosPorCidade() {
		// Arranjos
		Long idCidade = 4330L;

		// Execução
		List<Bairro> cidades = repository.findByCidadeId(idCidade);

		// Resultados
		assertNotNull(cidades);
		assertFalse(cidades.isEmpty());
		verificaSeTodasBairrosPertenceAoCidade(idCidade, cidades);
	}

	private void verificaSeTodasBairrosPertenceAoCidade(Long idCidade, List<Bairro> cidades) {
		for (Bairro cidade : cidades) {
			assertTrue(cidade.getCidadeId().equals(idCidade));
		}
	}

	private Bairro getBairro() {
		Bairro entity = new Bairro();
		entity.setNome("Integration Test");
		entity.setCidadeId(1L);
		return entity;
	}
}
