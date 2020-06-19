package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class PaisEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public PaisEmUsoException(String mensagem) {
		super(mensagem);
	}

	public PaisEmUsoException(Long id) {
		this(String.format("País de código %d não pode ser removido, pois está em uso", id));
	}

}
