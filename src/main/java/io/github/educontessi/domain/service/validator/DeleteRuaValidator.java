package io.github.educontessi.domain.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.RuaEmUsoException;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.repository.PessoaRepository;

@Service
public class DeleteRuaValidator implements Validator {

	@Autowired
	private PessoaRepository pessoaRepository;

	private Rua rua;

	@Override
	public void validate() {
		List<Pessoa> pessoas = pessoaRepository.findByRuaId(rua.getId());
		if (pessoas.size() > 0) {
			throw new RuaEmUsoException(rua.getId());
		}
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

}
