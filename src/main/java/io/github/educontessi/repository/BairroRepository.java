package io.github.educontessi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {

	public Optional<Bairro> findByNomeAndCidadeId(String nome, Long cidadeId);

	public List<Bairro> findByCidadeId(Long cidadeId);

}
