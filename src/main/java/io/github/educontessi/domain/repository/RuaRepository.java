package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.repository.infrastructure.rua.RuaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface RuaRepository extends JpaRepository<Rua, Long>, RuaRepositoryQuery {

	Optional<Rua> findByNomeAndCidadeId(String nome, Long cidadeId);

	List<Rua> findByCidadeId(Long cidadeId);

}
