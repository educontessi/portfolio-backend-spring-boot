package io.github.educontessi.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.repository.EstadoRepository;

/**
 * Service para {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public List<Estado> findAll() {
		return repository.findAll();
	}

	public List<Estado> findByPaisId(Long paisId) {
		return repository.findByPaisId(paisId);
	}

	public Optional<Estado> findByUf(String uf) {
		return repository.findByUf(uf);
	}

	public Estado save(Estado entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Estado update(Long id, Estado entity) {
		Optional<Estado> optionalSaved = findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Estado saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Estado> findById(Long id) {
		return repository.findById(id);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("pais");

		return (String[]) list.toArray(new String[0]);
	}

}
