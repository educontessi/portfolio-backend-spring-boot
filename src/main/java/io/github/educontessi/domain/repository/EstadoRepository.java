package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.repository.infrastructure.estado.EstadoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>, EstadoRepositoryQuery {

	Optional<Estado> findByUf(String uf);

	List<Estado> findByPaisId(Long paisId);

}
