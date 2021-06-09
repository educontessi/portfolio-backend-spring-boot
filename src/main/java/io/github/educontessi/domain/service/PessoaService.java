package io.github.educontessi.domain.service;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;
import io.github.educontessi.domain.filter.PessoaFilter;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.parametros_do_sistema.Parametros;
import io.github.educontessi.domain.repository.PessoaRepository;
import io.github.educontessi.domain.service.validator.DeletePessoaValidator;
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

import static io.github.educontessi.domain.helpers.util.FuncoesString.removeMascaraDeNumeros;

/**
 * Service para {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Service
public class PessoaService {

	private final PessoaRepository repository;

	@Autowired
	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Page<Pessoa> search(PessoaFilter pessoaFilter, Pageable pageable) {
		return repository.search(pessoaFilter, pageable);
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> optionalSaved = repository.findById(id);
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(id);
		}
		return optionalSaved.get();
	}

	public Pessoa findByCpfCnpj(String cpfCnpj) {
		Optional<Pessoa> optionalSaved = repository.findByCpfCnpj(removeMascaraDeNumeros(cpfCnpj));
		if (!optionalSaved.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de pessoa com CPF/CNPJ %s", cpfCnpj));
		}
		return optionalSaved.get();
	}

	public Pessoa save(Pessoa entity) {
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
		Pessoa saved = findById(id);
		validarExclusao(saved);
		saved.setDeleted(true);
		repository.save(saved);
	}

	protected void validarExclusao(Pessoa saved) {
		ValidatorExecutor executor = new ValidatorExecutor();

		DeletePessoaValidator deleteValidator = new DeletePessoaValidator();
		deleteValidator.setPessoa(saved);
		executor.add(deleteValidator);

		executor.execute();
	}

}
