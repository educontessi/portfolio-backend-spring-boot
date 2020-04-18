package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class PaisNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PaisNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PaisNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de país com código %d", id));
	}

}
