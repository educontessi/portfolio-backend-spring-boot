package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.model.Pessoa;
import org.springframework.stereotype.Service;

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
