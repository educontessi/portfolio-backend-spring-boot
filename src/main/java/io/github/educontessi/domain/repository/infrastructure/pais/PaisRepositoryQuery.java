package io.github.educontessi.domain.repository.infrastructure.pais;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.PaisFilter;
import io.github.educontessi.domain.model.Pais;

/**
 * Interface para filtros personalizados do cadastro de {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface PaisRepositoryQuery {

	public Page<Pais> filtrar(PaisFilter filter, Pageable pageable);

}
