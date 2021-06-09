package io.github.educontessi.domain.repository.infrastructure.bairro;

import io.github.educontessi.domain.filter.BairroFilter;
import io.github.educontessi.domain.model.Bairro;
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
 * Implementação da interface {@link BairroRepositoryQuery}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class BairroRepositoryImpl implements BairroRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Bairro> search(BairroFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Bairro> criteria = builder.createQuery(Bairro.class);
		Root<Bairro> root = criteria.from(Bairro.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Bairro> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(BairroFilter filter, CriteriaBuilder builder, Root<Bairro> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(root.get(BairroFilter.COLUNA_NOME), "%" + filter.getNome().toLowerCase() + "%"));
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

	private Long total(BairroFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Bairro> root = criteria.from(Bairro.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
