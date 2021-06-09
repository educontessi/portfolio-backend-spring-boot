package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteRuaValidator implements Validator {

	@Autowired
	private PessoaRepository pessoaRepository;

	private Rua rua;

	@Override
	public void validate() {
		List<Pessoa> pessoas = pessoaRepository.findByRuaId(rua.getId());
		if (!pessoas.isEmpty()) {
			throw new EntidadeEmUsoException(rua.getId());
		}
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

}
