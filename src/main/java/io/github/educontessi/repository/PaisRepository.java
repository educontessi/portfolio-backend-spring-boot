package io.github.educontessi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.model.Pais;

/**
 * Repository para {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
