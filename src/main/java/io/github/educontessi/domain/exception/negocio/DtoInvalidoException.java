package io.github.educontessi.domain.exception.negocio;

public class DtoInvalidoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public DtoInvalidoException(String mensagem) {
		super(mensagem);
	}

	public DtoInvalidoException(StringBuilder builder) {
		this(builder.toString());
	}

}
