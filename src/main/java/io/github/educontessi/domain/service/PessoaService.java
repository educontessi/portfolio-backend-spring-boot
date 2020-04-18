package io.github.educontessi.domain.service;

import static io.github.educontessi.domain.helpers.util.FuncoesString.removeMascaraDeNumeros;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.filter.PessoaFilter;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.infrastructure.pessoa.PessoaRepository;

/**
 * Service para {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter, Pageable pageable) {
		return repository.filtrar(pessoaFilter, pageable);
	}

	public Optional<Pessoa> findByCpfCnpj(String cpfCnpj) {
		Optional<Pessoa> entity = repository.findByCpfCnpj(removeMascaraDeNumeros(cpfCnpj));
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity;
	}

	public Pessoa save(Pessoa entity) {
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Pessoa update(Long id, Pessoa entity) {
		Optional<Pessoa> optionalSaved = findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Pessoa saved = optionalSaved.get();
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Pessoa> findById(Long id) {
		return repository.findById(id);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("rua");
		list.add("bairro");
		list.add("cidade");

		return (String[]) list.toArray(new String[0]);
	}

}
