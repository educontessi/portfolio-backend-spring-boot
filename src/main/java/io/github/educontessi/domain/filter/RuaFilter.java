package io.github.educontessi.domain.filter;

import io.github.educontessi.domain.model.Rua;

/**
 * Classe usada para filtro de {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class RuaFilter {

	public static final String NOME = "nome";

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
