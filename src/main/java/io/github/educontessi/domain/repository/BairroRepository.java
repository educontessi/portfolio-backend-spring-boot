package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.repository.infrastructure.bairro.BairroRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>, BairroRepositoryQuery {

	Optional<Bairro> findByNomeAndCidadeId(String nome, Long cidadeId);

	List<Bairro> findByCidadeId(Long cidadeId);

}
