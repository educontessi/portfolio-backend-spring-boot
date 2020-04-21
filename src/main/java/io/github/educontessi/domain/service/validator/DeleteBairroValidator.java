package io.github.educontessi.domain.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.BairroEmUsoException;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.PessoaRepository;

@Service
public class DeleteBairroValidator implements Validator {

	@Autowired
	private PessoaRepository pessoaRepository;

	private Bairro bairro;

	@Override
	public void validate() {
		List<Pessoa> pessoas = pessoaRepository.findByBairroId(bairro.getId());
		if (pessoas.size() > 0) {
			throw new BairroEmUsoException(bairro.getId());
		}
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

}
