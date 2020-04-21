package io.github.educontessi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.infrastructure.pessoa.PessoaRepositoryQuery;

/**
 * Repository para {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

	public Optional<Pessoa> findByCpfCnpj(String cpfCnpj);

}
