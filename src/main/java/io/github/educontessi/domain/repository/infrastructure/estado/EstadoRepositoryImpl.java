package io.github.educontessi.domain.repository.infrastructure.estado;

import io.github.educontessi.domain.filter.EstadoFilter;
import io.github.educontessi.domain.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface {@link EstadoRepositoryQuery}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class EstadoRepositoryImpl implements EstadoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Estado> search(EstadoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
		Root<Estado> root = criteria.from(Estado.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Estado> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(EstadoFilter filter, CriteriaBuilder builder, Root<Estado> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(root.get(EstadoFilter.COLUNA_NOME), "%" + filter.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(filter.getUf())) {
			predicates.add(builder.equal(root.get(EstadoFilter.COLUNA_UF), filter.getUf().toUpperCase()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(EstadoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Estado> root = criteria.from(Estado.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
