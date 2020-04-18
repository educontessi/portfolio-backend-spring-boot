package io.github.educontessi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.PaisEmUsoException;
import io.github.educontessi.domain.exception.PaisNaoEncontradoException;
import io.github.educontessi.domain.helpers.util.LoadProperties;
import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.repository.PaisRepository;
import io.github.educontessi.domain.service.validator.DeletePaisValidator;

/**
 * Service para {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class PaisService {

	private PaisRepository repository;
	private DeletePaisValidator deletePaisValidator;

	@Autowired
	public PaisService(PaisRepository repository, DeletePaisValidator deletePaisValidator) {
		this.repository = repository;
		this.deletePaisValidator = deletePaisValidator;
	}

	public List<Pais> findAll() {
		return repository.findAll();
	}

	public Pais update(Long id, Pais entity) {
		return repository.saveAndFlush(entity);
	}

	public Pais findById(Long id) {
		Optional<Pais> optionalSaved = repository.findById(id);
		isPresent(optionalSaved);
		return optionalSaved.get();
	}

	public Pais save(Pais entity) {
		return repository.saveAndFlush(entity);
	}

	public void delete(Long id) {
		boolean excluirDefinitivo = Boolean.valueOf(LoadProperties.getProperty("portifolio.excluir-definitivo"));
		if (excluirDefinitivo) {
			try {
				repository.deleteById(id);
				repository.flush();

			} catch (EmptyResultDataAccessException e) {
				throw new PaisNaoEncontradoException(id);

			} catch (DataIntegrityViolationException e) {
				throw new PaisEmUsoException(id);
			}
		} else {
			Pais saved = findById(id);
			validarExclusao(saved);
			saved.setDeleted(true);
			repository.save(saved);
		}
	}

	protected void validarExclusao(Pais saved) {
		deletePaisValidator.setPais(saved);
		deletePaisValidator.validate();
	}

	protected void isPresent(Optional<Pais> optionalSaved) {
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
