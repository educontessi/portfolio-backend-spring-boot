package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de cidade com código %d", id));
	}

	public CidadeNaoEncontradaException(Long id, Integer ibge) {
		this(String.format("Não existe um cadastro de cidade com código do IBGE %d", ibge));
	}

}
