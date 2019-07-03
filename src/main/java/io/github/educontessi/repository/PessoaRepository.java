package io.github.educontessi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.educontessi.model.Pessoa;
import io.github.educontessi.repository.pessoa.PessoaRepositoryQuery;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

	public Optional<Pessoa> findByCpfCnpj(String cpfCnpj);

}
