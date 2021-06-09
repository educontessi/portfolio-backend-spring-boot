package io.github.educontessi.domain.service;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;
import io.github.educontessi.domain.filter.EstadoFilter;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.parametros_do_sistema.Parametros;
import io.github.educontessi.domain.repository.EstadoRepository;
import io.github.educontessi.domain.service.validator.DeleteEstadoValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service para {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class EstadoService {

	private final EstadoRepository repository;

	@Autowired
	public EstadoService(EstadoRepository repository) {
		this.repository = repository;
	}

	public List<Estado> findAll() {
		return repository.findAll();
	}

	public Page<Estado> search(EstadoFilter filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

	public Estado findById(Long id) {
		Optional<Estado> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public List<Estado> findByPaisId(Long paisId) {
		return repository.findByPaisId(paisId);
	}

	public Estado findByUf(String uf) {
		Optional<Estado> optionalSaved = repository.findByUf(uf);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com UF %s", uf));
		}
		return optionalSaved.get();
	}

	public Estado save(Estado entity) {
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
		Estado saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Estado saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteEstadoValidator deleteValidator = new DeleteEstadoValidator();
		deleteValidator.setEstado(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
