package io.github.educontessi.domain.repository.infrastructure.rua;

import io.github.educontessi.domain.filter.RuaFilter;
import io.github.educontessi.domain.model.Rua;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para filtros personalizados do cadastro de {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface RuaRepositoryQuery {

	Page<Rua> search(RuaFilter filter, Pageable pageable);

}
