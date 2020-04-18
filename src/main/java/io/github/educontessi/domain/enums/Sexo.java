package io.github.educontessi.domain.enums;

import io.github.educontessi.domain.model.Pessoa;

/**
 * Enum {@link Sexo} para diferenciar o tipo de sexo da entidade {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
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