package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;

public class EstadoEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EstadoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EstadoEmUsoException(Long id) {
		this(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
	}

}
