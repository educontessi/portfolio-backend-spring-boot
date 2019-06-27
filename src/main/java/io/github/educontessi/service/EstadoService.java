package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Estado;
import io.github.educontessi.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public Estado update(Long id, Estado entity) {
		Estado saved = findById(id);
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	private Estado findById(Long id) {
		Optional<Estado> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("pais");

		return (String[]) list.toArray(new String[0]);
	}

}
