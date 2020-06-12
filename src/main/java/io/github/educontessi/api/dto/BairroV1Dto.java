package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Bairro;

/**
 * Data Transfer Object {@link Bairro}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class BairroV1Dto extends BaseDto {

	private String nome;
	private CidadeV1Dto cidade;

	public BairroV1Dto() {
		this.apiVersion = "V1";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CidadeV1Dto getCidade() {
		return cidade;
	}

	public void setCidade(CidadeV1Dto cidade) {
		this.cidade = cidade;
	}

}
