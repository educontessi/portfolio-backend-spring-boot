package io.github.educontessi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.domain.model.Estado;

/**
 * Repository para {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public Optional<Estado> findByUf(String uf);

	public List<Estado> findByPaisId(Long paisId);

}