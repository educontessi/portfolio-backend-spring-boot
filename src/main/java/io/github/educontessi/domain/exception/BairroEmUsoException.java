package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class BairroEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public BairroEmUsoException(String mensagem) {
		super(mensagem);
	}

	public BairroEmUsoException(Long id) {
		this(String.format("Bairro de código %d não pode ser removido, pois está em uso", id));
	}

}
