package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.repository.infrastructure.cidade.CidadeRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQuery {

	Optional<Cidade> findByIbge(Integer ibge);

	List<Cidade> findByEstadoId(Long estadoId);

}
