package io.github.educontessi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.repository.infrastructure.cidade.CidadeRepositoryQuery;

/**
 * Repository para {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQuery {

	public Optional<Cidade> findByIbge(Integer ibge);

	public List<Cidade> findByEstadoId(Long estadoId);

}
