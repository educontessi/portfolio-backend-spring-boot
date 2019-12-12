package io.github.educontessi.dto;

import io.github.educontessi.model.Pais;

/**
 * {@link Pais} DTO-Entity converter
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class PaisV1Dto extends BaseDto {

	private String nome;
	private String sigla;
	private String bacen;

	public PaisV1Dto() {
		this.apiVersion = "V1";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getBacen() {
		return bacen;
	}

	public void setBacen(String bacen) {
		this.bacen = bacen;
	}

}
