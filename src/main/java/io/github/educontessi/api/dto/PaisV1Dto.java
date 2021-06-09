package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Pais;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

/**
 * Data Transfer Object {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@JsonIgnoreProperties(value = { "created", "changed" })
public class PaisV1Dto extends BaseDto {

	@ApiModelProperty(value = "Nome", required = true)
	private String nome;

	@ApiModelProperty(value = "Sigla", required = true)
	private String sigla;

	@ApiModelProperty(value = "Cógido BACEN", required = true)
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
