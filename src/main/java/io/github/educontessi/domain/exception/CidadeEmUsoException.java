package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class CidadeEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public CidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public CidadeEmUsoException(Long id) {
		this(String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
	}

}
