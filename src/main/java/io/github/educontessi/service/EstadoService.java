package io.github.educontessi.service;

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
		Estado estado = findById(id);
		BeanUtils.copyProperties(entity, estado, "id");
		return repository.save(estado);
	}

	public Estado findById(Long id) {
		Optional<Estado> estado = repository.findById(id);
		if (!estado.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return estado.get();
	}

}
