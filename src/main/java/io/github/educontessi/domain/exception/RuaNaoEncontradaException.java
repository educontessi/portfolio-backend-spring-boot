package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class RuaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RuaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public RuaNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de rua com código %d", id));
	}

}
