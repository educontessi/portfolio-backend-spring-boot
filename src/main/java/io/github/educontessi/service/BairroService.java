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

	private BairroRepository repository;

	@Autowired
	public BairroService(BairroRepository repository) {
		this.repository = repository;
	}

	public List<Bairro> findAll() {
		return repository.findAll();
	}

	public List<Bairro> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Bairro save(Bairro entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Bairro update(Long id, Bairro entity) {
		Optional<Bairro> optionalSaved = findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Bairro saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Bairro> findById(Long id) {
		return repository.findById(id);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("cidade");

		return (String[]) list.toArray(new String[0]);
	}
}
