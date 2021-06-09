package io.github.educontessi.domain.service;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;
import io.github.educontessi.domain.filter.CidadeFilter;
import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.parametros_do_sistema.Parametros;
import io.github.educontessi.domain.repository.CidadeRepository;
import io.github.educontessi.domain.service.validator.DeleteCidadeValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service para {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class CidadeService {

	private final CidadeRepository repository;

	@Autowired
	public CidadeService(CidadeRepository repository) {
		this.repository = repository;
	}

	public List<Cidade> findAll() {
		return repository.findAll();
	}

	public Page<Cidade> search(CidadeFilter filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

	public Cidade findById(Long id) {
		Optional<Cidade> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public List<Cidade> findByEstadoId(Long estadoId) {
		return repository.findByEstadoId(estadoId);
	}

	public Cidade findByIbge(@PathVariable Integer ibge) {
		Optional<Cidade> optionalSaved = repository.findByIbge(ibge);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe uma cidade com código o código IBGE %d", ibge));
		}
		return optionalSaved.get();
	}

	public Cidade save(Cidade entity) {
		Objects.requireNonNull(entity, "entity não pode ser null");
		return repository.save(entity);
	}

	public void delete(Long id) {
		if (Parametros.EXCLUIR_DEFINITIVO) {
			definitiveDelete(id);
		} else {
			paranoidDelete(id);
		}
	}

	protected void definitiveDelete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}

	protected void paranoidDelete(Long id) {
		Cidade saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Cidade saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteCidadeValidator deleteValidator = new DeleteCidadeValidator();
		deleteValidator.setCidade(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
