package io.github.educontessi.domain.repository.infrastructure.estado;

import io.github.educontessi.domain.filter.EstadoFilter;
import io.github.educontessi.domain.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface EstadoRepositoryQuery {

	Page<Estado> search(EstadoFilter filter, Pageable pageable);

}
