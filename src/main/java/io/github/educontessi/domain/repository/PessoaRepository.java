package io.github.educontessi.domain.repository;

import java.util.List;
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

	public List<Pessoa> findByCidadeId(Long cidadeId);

	public List<Pessoa> findByBairroId(Long bairroId);

	public List<Pessoa> findByRuaId(Long ruaId);

}
