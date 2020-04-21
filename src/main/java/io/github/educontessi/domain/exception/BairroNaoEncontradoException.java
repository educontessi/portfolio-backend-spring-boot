package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class BairroNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public BairroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public BairroNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de bairro com código %d", id));
	}

}
