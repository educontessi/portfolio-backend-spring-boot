package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Rua;
import io.github.educontessi.repository.RuaRepository;

@Service
public class RuaService {

	private RuaRepository repository;

	@Autowired
	public RuaService(RuaRepository repository) {
		this.repository = repository;
	}

	public List<Rua> findAll() {
		return repository.findAll();
	}

	public List<Rua> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Rua save(Rua entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Rua update(Long id, Rua entity) {
		Optional<Rua> optionalSaved = findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Rua saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Rua> findById(Long id) {
		return repository.findById(id);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("cidade");

		return (String[]) list.toArray(new String[0]);
	}

}
