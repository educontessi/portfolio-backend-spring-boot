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

import io.github.educontessi.domain.exception.RuaEmUsoException;
import io.github.educontessi.domain.exception.RuaNaoEncontradaException;
import io.github.educontessi.domain.filter.RuaFilter;
import io.github.educontessi.domain.helpers.util.LoadProperties;
import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.repository.RuaRepository;
import io.github.educontessi.domain.service.validator.DeleteRuaValidator;
import io.github.educontessi.domain.service.validator.ValidatorExecutor;

/**
 * Service para {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
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

	public Page<Rua> pesquisar(RuaFilter filter, Pageable pageable) {
		return repository.filtrar(filter, pageable);
	}

	public Rua findById(Long id) {
		Optional<Rua> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new RuaNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public List<Rua> findByCidadeId(Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	public Rua save(Rua entity) {
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
			throw new RuaNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new RuaEmUsoException(id);
		}
	}

	protected void paranoidDelete(Long id) {
		Rua saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		save(saved);
	}

	protected void validarExclusao(Rua saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeleteRuaValidator deleteValidator = new DeleteRuaValidator();
		deleteValidator.setRua(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
