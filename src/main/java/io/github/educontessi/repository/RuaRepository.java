package io.github.educontessi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.model.Rua;

/**
 * Repository para {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface RuaRepository extends JpaRepository<Rua, Long> {

	public Optional<Rua> findByNomeAndCidadeId(String nome, Long cidadeId);

	public List<Rua> findByCidadeId(Long cidadeId);

}
