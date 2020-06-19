package io.github.educontessi.domain.repository.infrastructure.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.PessoaFilter;
import io.github.educontessi.domain.model.Pessoa;

/**
 * Interface para filtros personalizados do cadastro de {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface PessoaRepositoryQuery {

	public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);

}
