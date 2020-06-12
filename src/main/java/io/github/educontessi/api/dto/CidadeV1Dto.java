package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Cidade;

/**
 * Data Transfer Object {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class CidadeV1Dto extends BaseDto {

	private String nome;
	private String uf;
	private EstadoV1Dto estado;
	private Integer ibge;

	public CidadeV1Dto() {
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

	public EstadoV1Dto getEstado() {
		return estado;
	}

	public void setEstado(EstadoV1Dto estado) {
		this.estado = estado;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}

}
