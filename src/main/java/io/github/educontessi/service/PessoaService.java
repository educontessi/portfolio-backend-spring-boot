package io.github.educontessi.service;

import static io.github.educontessi.helpers.util.FuncoesString.removeMascaraDeNumeros;

import java.util.ArrayList;
import java.util.List;
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
		BeanUtils.copyProperties(entity, saved, getIgnoreProperties());
		return repository.save(saved);
	}

	public Optional<Pessoa> findByCpfCnpj(String cpfCnpj) {
		Optional<Pessoa> entity = repository.findByCpfCnpj(removeMascaraDeNumeros(cpfCnpj));
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity;
	}

	private Pessoa findById(Long id) {
		Optional<Pessoa> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entity.get();
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
