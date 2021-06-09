package io.github.educontessi.domain.repository.infrastructure.cidade;

import io.github.educontessi.domain.filter.CidadeFilter;
import io.github.educontessi.domain.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface CidadeRepositoryQuery {

	Page<Cidade> search(CidadeFilter filter, Pageable pageable);

}
