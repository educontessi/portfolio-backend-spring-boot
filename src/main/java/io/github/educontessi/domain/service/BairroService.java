package io.github.educontessi.domain.service;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;
import io.github.educontessi.domain.filter.BairroFilter;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.parametros_do_sistema.Parametros;
import io.github.educontessi.domain.repository.BairroRepository;
import io.github.educontessi.domain.service.validator.DeleteBairroValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

	public Page<Bairro> search(BairroFilter filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

	public Bairro findById(Long id) {
		Optional<Bairro> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public List<Bairro> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Bairro save(Bairro entity) {
		Objects.requireNonNull(entity, "entity não pode ser null");
		return repository.save(entity);
	}

	public void delete(Long id) {
		if (Parametros.EXCLUIR_DEFINITIVO) {
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
			throw new EntidadeNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}

	protected void paranoidDelete(Long id) {
		Bairro saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Bairro saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteBairroValidator deleteValidator = new DeleteBairroValidator();
		deleteValidator.setBairro(saved);
		executor.add(deleteValidator);

		executor.execute();
	}
}
