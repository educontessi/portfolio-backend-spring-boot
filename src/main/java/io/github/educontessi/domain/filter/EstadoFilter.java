package io.github.educontessi.domain.filter;

import io.github.educontessi.domain.model.Estado;

/**
 * Classe usada para filtro de {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class EstadoFilter {

	public static final String NOME = "nome";
	public static final String UF = "uf";

	private String nome;
	private String uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
