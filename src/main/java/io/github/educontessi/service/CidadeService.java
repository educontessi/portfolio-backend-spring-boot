package io.github.educontessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.educontessi.model.Cidade;
import io.github.educontessi.repository.CidadeRepository;

/**
 * Service para {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class CidadeService {

	private final CidadeRepository repository;

	@Autowired
	public CidadeService(CidadeRepository repository) {
		this.repository = repository;
	}

	public List<Cidade> findAll() {
		return repository.findAll();
	}

	public List<Cidade> findByEstadoId(Long estadoId) {
		return repository.findByEstadoId(estadoId);
	}

	public Cidade save(Cidade entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Optional<Cidade> findByIbge(@PathVariable Integer ibge) {
		return repository.findByIbge(ibge);
	}

	public Cidade update(Long id, Cidade entity) {
		Optional<Cidade> optionalSaved = findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Cidade saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Cidade> findById(Long id) {
		return repository.findById(id);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("estado");

		return (String[]) list.toArray(new String[0]);
	}

}
