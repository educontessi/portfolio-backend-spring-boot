package io.github.educontessi.model.enumeracoes;

/**
 * Enum {@link Status} para diferenciar o status dos registros da tabela
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public enum Status {

	ATIVO("A", "Ativo"), INATIVO("I", "Inativo");

	private String status;
	private String descricao;

	private Status(String status, String descricao) {
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
