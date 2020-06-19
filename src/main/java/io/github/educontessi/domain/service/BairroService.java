package io.github.educontessi.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.BairroEmUsoException;
import io.github.educontessi.domain.exception.BairroNaoEncontradoException;
import io.github.educontessi.domain.filter.BairroFilter;
import io.github.educontessi.domain.helpers.util.LoadProperties;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.repository.BairroRepository;
import io.github.educontessi.domain.service.validator.DeleteBairroValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;

/**
 * Service para {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class BairroService {

	private final BairroRepository repository;

	@Autowired
	public BairroService(BairroRepository repository) {
		this.repository = repository;
	}

	public List<Bairro> findAll() {
		return repository.findAll();
	}

	public Page<Bairro> pesquisar(BairroFilter filter, Pageable pageable) {
		return repository.filtrar(filter, pageable);
	}

	public Bairro findById(Long id) {
		Optional<Bairro> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new BairroNaoEncontradoException(id);
		}
		return optionalSaved.get();
	}

	public List<Bairro> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Bairro save(Bairro entity) {
		Objects.requireNonNull(entity, "entity nao pode ser null");
		return repository.save(entity);
	}

	public void delete(Long id) {
		boolean excluirDefinitivo = Boolean.valueOf(LoadProperties.getProperty("portifolio.excluir-definitivo"));
		if (excluirDefinitivo) {
			definitiveDelete(id);
		} else {
			paranoidDelete(id);
		}
	}

	protected void definitiveDelete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new BairroNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new BairroEmUsoException(id);
		}
	}

	protected void paranoidDelete(Long id) {
		Bairro saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		save(saved);
	}

	protected void validarExclusao(Bairro saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteBairroValidator deleteValidator = new DeleteBairroValidator();
		deleteValidator.setBairro(saved);
		executor.add(deleteValidator);

		executor.execute();
	}
}
