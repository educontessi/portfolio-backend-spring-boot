package io.github.educontessi.domain.repository.infrastructure.pessoa;

import io.github.educontessi.domain.filter.PessoaFilter;
import io.github.educontessi.domain.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface PessoaRepositoryQuery {

	Page<Pessoa> search(PessoaFilter filter, Pageable pageable);

}
