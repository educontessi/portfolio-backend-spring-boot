package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Cidade;
import io.github.educontessi.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public Cidade update(Long id, Cidade entity) {
		Cidade saved = findById(id);
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	private Cidade findById(Long id) {
		Optional<Cidade> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("estado");

		return (String[]) list.toArray(new String[0]);
	}

}
