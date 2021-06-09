package io.github.educontessi.domain.repository.infrastructure.pais;

import io.github.educontessi.domain.filter.PaisFilter;
import io.github.educontessi.domain.model.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface PaisRepositoryQuery {

	Page<Pais> search(PaisFilter filter, Pageable pageable);

}
