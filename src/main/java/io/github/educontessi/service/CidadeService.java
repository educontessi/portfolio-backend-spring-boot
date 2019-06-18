package io.github.educontessi.service;

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
		Cidade cidade = findById(id);
		BeanUtils.copyProperties(entity, cidade, "id");
		return repository.save(cidade);
	}

	public Cidade findById(Long id) {
		Optional<Cidade> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

}
