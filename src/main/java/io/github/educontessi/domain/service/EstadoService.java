package io.github.educontessi.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.EstadoEmUsoException;
import io.github.educontessi.domain.exception.EstadoNaoEncontradoException;
import io.github.educontessi.domain.filter.EstadoFilter;
import io.github.educontessi.domain.helpers.util.LoadProperties;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.repository.EstadoRepository;
import io.github.educontessi.domain.service.validator.DeleteEstadoValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;

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
			throw new EstadoNaoEncontradoException(id);
		}
		return optionalSaved.get();
	}

	public List<Estado> findByPaisId(Long paisId) {
		return repository.findByPaisId(paisId);
	}

	public Estado findByUf(String uf) {
		Optional<Estado> optionalSaved = repository.findByUf(uf);
		if (!optionalSaved.isPresent()) {
			throw new EstadoNaoEncontradoException(String.format("Não existe um cadastro de estado com UF %d", uf));
		}
		return optionalSaved.get();
	}

	public Estado save(Estado entity) {
		Objects.requireNonNull(entity, "entity nao pode ser null");
		return repository.save(entity);
	}

	public void delete(Long id) {
		boolean excluirDefinitivo = Boolean.valueOf(LoadProperties.getProperty("portifolio.excluir-definitivo"));
		if (excluirDefinitivo) {
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
			throw new EstadoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EstadoEmUsoException(id);
		}
	}

	protected void paranoidDelete(Long id) {
		Estado saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		save(saved);
	}

	protected void validarExclusao(Estado saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteEstadoValidator deleteValidator = new DeleteEstadoValidator();
		deleteValidator.setEstado(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
