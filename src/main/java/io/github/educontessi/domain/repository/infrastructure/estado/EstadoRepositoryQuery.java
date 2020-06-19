package io.github.educontessi.domain.repository.infrastructure.estado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.EstadoFilter;
import io.github.educontessi.domain.model.Estado;

/**
 * Interface para filtros personalizados do cadastro de {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface EstadoRepositoryQuery {

	public Page<Estado> filtrar(EstadoFilter filter, Pageable pageable);

}
