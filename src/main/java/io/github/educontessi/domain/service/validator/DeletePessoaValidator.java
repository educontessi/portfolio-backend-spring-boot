package io.github.educontessi.domain.service.validator;

import org.springframework.stereotype.Service;

import io.github.educontessi.domain.model.Pessoa;

@Service
public class DeletePessoaValidator implements Validator {

	private Pessoa pessoa;

	@Override
	public void validate() {
		if (pessoa.isDeleted()) {
			// throw new PessoaEmUsoException(pessoa.getId());
		}
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
