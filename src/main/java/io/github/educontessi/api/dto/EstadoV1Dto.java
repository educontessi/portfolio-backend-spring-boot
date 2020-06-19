package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Estado;

/**
 * Data Transfer Object {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class EstadoV1Dto extends BaseDto {

	private String nome;
	private String uf;
	private PaisV1Dto pais;

	public EstadoV1Dto() {
		this.apiVersion = "V1";
	}

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

	public PaisV1Dto getPais() {
		return pais;
	}

	public void setPais(PaisV1Dto pais) {
		this.pais = pais;
	}

}
