package io.github.educontessi.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.model.Pessoa;
import io.github.educontessi.repository.filter.PessoaFilter;

/**
 * Interface para filtros personalizados do cadastro de {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface PessoaRepositoryQuery {

	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}
