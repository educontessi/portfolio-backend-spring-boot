package io.github.educontessi.model.enumeracoes;

public enum TipoPessoa {

	FISICA("F", "Física"), JURIDICA("J", "Jurídica");

	private String tipo;
	private String descricao;

	private TipoPessoa(String tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

}
