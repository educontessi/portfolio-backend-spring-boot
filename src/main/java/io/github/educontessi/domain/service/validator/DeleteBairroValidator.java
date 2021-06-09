package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteBairroValidator implements Validator {

	@Autowired
	private PessoaRepository pessoaRepository;

	private Bairro bairro;

	@Override
	public void validate() {
		List<Pessoa> pessoas = pessoaRepository.findByBairroId(bairro.getId());
		if (!pessoas.isEmpty()) {
			throw new EntidadeEmUsoException(bairro.getId());
		}
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

}
