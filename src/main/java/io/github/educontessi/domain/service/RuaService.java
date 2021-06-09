package io.github.educontessi.domain.service;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;
import io.github.educontessi.domain.filter.RuaFilter;
import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.parametros_do_sistema.Parametros;
import io.github.educontessi.domain.repository.RuaRepository;
import io.github.educontessi.domain.service.validator.DeleteRuaValidator;
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
 * Service para {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class RuaService {

	private final RuaRepository repository;

	@Autowired
	public RuaService(RuaRepository repository) {
		this.repository = repository;
	}

	public List<Rua> findAll() {
		return repository.findAll();
	}

	public Page<Rua> search(RuaFilter filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

	public Rua findById(Long id) {
		Optional<Rua> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public List<Rua> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Rua save(Rua entity) {
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
		Rua saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Rua saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteRuaValidator deleteValidator = new DeleteRuaValidator();
		deleteValidator.setRua(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
