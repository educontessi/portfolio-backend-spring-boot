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

@Service
public class PaisService {

	@Autowired
	private PaisRepository repository;

	public Pais update(Long id, Pais entity) {
		Pais saved = findById(id);
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	private Pais findById(Long id) {
		Optional<Pais> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");

		return (String[]) list.toArray(new String[0]);
	}

}
