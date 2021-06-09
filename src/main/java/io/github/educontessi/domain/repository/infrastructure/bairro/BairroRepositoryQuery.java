package io.github.educontessi.domain.repository.infrastructure.bairro;

import io.github.educontessi.domain.filter.BairroFilter;
import io.github.educontessi.domain.model.Bairro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface BairroRepositoryQuery {

	Page<Bairro> search(BairroFilter filter, Pageable pageable);

}
