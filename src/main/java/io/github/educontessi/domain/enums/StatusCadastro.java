package io.github.educontessi.domain.enums;

/**
 * Enum {@link StatusCadastro} para diferenciar o status dos registros da tabela
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public enum StatusCadastro {

	ATIVO("A", "Ativo"), INATIVO("I", "Inativo");

	private String status;
	private String descricao;

	private StatusCadastro(String status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public String getDescricao() {
		return descricao;
	}

}
