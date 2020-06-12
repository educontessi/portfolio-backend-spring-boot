package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.Rua;

/**
 * Data Transfer Object {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class RuaV1Dto extends BaseDto {

	private String nome;
	private CidadeV1Dto cidade;

	public RuaV1Dto() {
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
