package io.github.educontessi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Pessoa;
import io.github.educontessi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa update(Long id, Pessoa entity) {
		Pessoa saved = findById(id);
		BeanUtils.copyProperties(entity, saved, "id");
		return repository.save(saved);
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
	}

}
