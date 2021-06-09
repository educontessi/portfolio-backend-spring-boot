package io.github.educontessi.domain.filter;

import io.github.educontessi.domain.model.Bairro;

/**
 * Classe usada para filtro de {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class BairroFilter {

	public static final String COLUNA_NOME = "nome";

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
