package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class RuaEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public RuaEmUsoException(String mensagem) {
		super(mensagem);
	}

	public RuaEmUsoException(Long id) {
		this(String.format("Rua de código %d não pode ser removida, pois está em uso", id));
	}

}
