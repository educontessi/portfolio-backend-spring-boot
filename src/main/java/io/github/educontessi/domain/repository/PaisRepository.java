package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.repository.infrastructure.pais.PaisRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>, PaisRepositoryQuery {

}
