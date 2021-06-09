package io.github.educontessi.domain.exception.negocio;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String  mensagem) {
		super(mensagem);
	}

	public EntidadeEmUsoException(Long id) {
		this(String.format("O cadastro de código %d não pode ser removido, pois está em uso", id));
	}

}
