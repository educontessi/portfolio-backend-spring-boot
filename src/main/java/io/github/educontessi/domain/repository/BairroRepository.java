package io.github.educontessi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.repository.infrastructure.bairro.BairroRepositoryQuery;

/**
 * Repository para {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>, BairroRepositoryQuery {

	public Optional<Bairro> findByNomeAndCidadeId(String nome, Long cidadeId);

	public List<Bairro> findByCidadeId(Long cidadeId);

}
