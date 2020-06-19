package io.github.educontessi.domain.exception;

import io.github.educontessi.domain.exception.negocio.EntidadeNaoEncontradaException;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PessoaNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de pessoa com código %d", id));
	}

	public PessoaNaoEncontradaException(Long id, String cpfCnpj) {
		this(String.format("Não existe um cadastro de pessoa com CPF / CNPJ %d", cpfCnpj));
	}
}
