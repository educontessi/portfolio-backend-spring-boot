package io.github.educontessi.domain.exception.negocio;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public EntidadeNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro com código %d", id));
	}

}
