package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

/**
 * Data Transfer Object {@link Estado}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@JsonIgnoreProperties(value = { "created", "changed" })
public class EstadoV1Dto extends BaseDto {

	@ApiModelProperty(value = "Nome", required = true)
	private String nome;

	@ApiModelProperty(value = "UF", required = true)
	private String uf;

	@ApiModelProperty(value = "Código do País", required = true)
	private Long paisId;

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

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

}
