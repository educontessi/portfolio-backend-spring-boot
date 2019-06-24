package io.github.educontessi.model.enumeracoes;

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
