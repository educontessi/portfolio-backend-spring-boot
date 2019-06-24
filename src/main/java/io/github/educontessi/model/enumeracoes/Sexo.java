package io.github.educontessi.model.enumeracoes;

public enum Sexo {

	MASCULINO("M", "Masculino"), FEMININO("F", "Feminino");

	private String tipo;
	private String descricao;

	private Sexo(String tipo, String descricao) {
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