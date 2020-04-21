package io.github.educontessi.domain.repository.infrastructure.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.educontessi.domain.filter.CidadeFilter;
import io.github.educontessi.domain.model.Cidade;

/**
 * Interface para filtros personalizados do cadastro de {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public interface CidadeRepositoryQuery {

	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);

}
