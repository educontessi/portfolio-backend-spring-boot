package io.github.educontessi.domain.repository.infrastructure.bairro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.BairroFilter;
import io.github.educontessi.domain.model.Bairro;

/**
 * Interface para filtros personalizados do cadastro de {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface BairroRepositoryQuery {

	public Page<Bairro> search(BairroFilter filter, Pageable pageable);

}
