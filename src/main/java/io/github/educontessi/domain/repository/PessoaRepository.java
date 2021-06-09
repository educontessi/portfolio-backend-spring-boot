package io.github.educontessi.domain.repository;

import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.infrastructure.pessoa.PessoaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

	Optional<Pessoa> findByCpfCnpj(String cpfCnpj);

	List<Pessoa> findByCidadeId(Long cidadeId);

	List<Pessoa> findByBairroId(Long bairroId);

	List<Pessoa> findByRuaId(Long ruaId);

}
