package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de estado com código %d", id));
	}

}
