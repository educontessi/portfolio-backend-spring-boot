package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data Transfer Object {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@JsonIgnoreProperties(value = { "created", "changed" })
public class CidadeV1Dto extends BaseDto {

	private String nome;

	private Long estadoId;
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

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

}
