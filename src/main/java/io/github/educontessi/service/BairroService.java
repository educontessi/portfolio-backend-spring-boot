package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Bairro;
import io.github.educontessi.repository.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository repository;

	public Bairro update(Long id, Bairro entity) {
		Bairro saved = findById(id);
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	private Bairro findById(Long id) {
		Optional<Bairro> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("cidade");

		return (String[]) list.toArray(new String[0]);
	}
}
