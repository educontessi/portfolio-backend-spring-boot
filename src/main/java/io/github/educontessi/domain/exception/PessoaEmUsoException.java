package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class PessoaEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public PessoaEmUsoException(String mensagem) {
		super(mensagem);
	}

	public PessoaEmUsoException(Long id) {
		this(String.format("Pessoa de código %d não pode ser removida, pois está em uso", id));
	}

}
