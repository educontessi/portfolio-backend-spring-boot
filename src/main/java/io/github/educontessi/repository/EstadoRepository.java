package io.github.educontessi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.educontessi.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public Optional<Estado> findByUf(String uf);

	public List<Estado> findByPaisId(Long paisId);

}
