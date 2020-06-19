package io.github.educontessi.domain.filter;

import io.github.educontessi.domain.model.Cidade;

/**
 * Classe usada para filtro de {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class CidadeFilter {

	public static final String NOME = "nome";

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
