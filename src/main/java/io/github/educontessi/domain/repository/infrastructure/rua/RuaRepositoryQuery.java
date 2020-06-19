package io.github.educontessi.domain.repository.infrastructure.rua;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.RuaFilter;
import io.github.educontessi.domain.model.Rua;

/**
 * Interface para filtros personalizados do cadastro de {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface RuaRepositoryQuery {

	public Page<Rua> filtrar(RuaFilter filter, Pageable pageable);

}
