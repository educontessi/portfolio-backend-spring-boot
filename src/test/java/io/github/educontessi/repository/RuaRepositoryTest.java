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

import io.github.educontessi.model.Rua;

/**
 * Classe de teste para entidade {@link RuaRepository}
 * 
 * @author Eduardo Contessi
 *
 */
@SpringBootTest
public class RuaRepositoryTest {

	@Autowired
	private RuaRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscaRuarPorNome() {
		// Arranjos
		Rua entity = getRua();
		repository.save(entity);

		// Execução
		Optional<Rua> found = repository.findByNomeAndCidadeId(entity.getNome(), entity.getCidadeId());
		Rua rua = found.get();

		// Resultados
		assertNotNull(rua);
		assertEquals(rua.getNome(), entity.getNome());
		assertEquals(rua.getCidadeId(), entity.getCidadeId());

		// Final
		repository.delete(entity);
	}

	@Test
	public void deveListarRuasPorCidade() {
		// Arranjos
		Long idCidade = 4330L;

		// Execução
		List<Rua> cidades = repository.findByCidadeId(idCidade);

		// Resultados
		assertNotNull(cidades);
		assertFalse(cidades.isEmpty());
		verificaSeTodasRuasPertenceAoCidade(idCidade, cidades);
	}

	private void verificaSeTodasRuasPertenceAoCidade(Long idCidade, List<Rua> cidades) {
		for (Rua cidade : cidades) {
			assertTrue(cidade.getCidadeId().equals(idCidade));
		}
	}

	private Rua getRua() {
		Rua entity = new Rua();
		entity.setNome("Integration Test");
		entity.setCidadeId(1L);
		return entity;
	}
}
