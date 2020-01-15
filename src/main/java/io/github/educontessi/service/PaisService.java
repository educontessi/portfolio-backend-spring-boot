package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Pais;
import io.github.educontessi.repository.PaisRepository;
import io.github.educontessi.service.validator.DeletePaisValidator;

/**
 * Service para {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class PaisService {

	@Autowired
	private PaisRepository repository;

	@Autowired
	private DeletePaisValidator deletePaisValidator;

	public List<Pais> findAll() {
		return repository.findAll();
	}

	public Pais update(Long id, Pais entity) {
		Optional<Pais> optionalSaved = findById(id);
		isPresent(optionalSaved);
		Pais saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Pais> findById(Long id) {
		return repository.findById(id);
	}

	public Pais save(Pais entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		Optional<Pais> optionalSaved = findById(id);
		isPresent(optionalSaved);

		Pais saved = optionalSaved.get();
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Pais saved) {
		deletePaisValidator.setPais(saved);
		deletePaisValidator.validate();
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");

		return (String[]) list.toArray(new String[0]);
	}

	protected void isPresent(Optional<Pais> optionalSaved) {
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
