package io.github.educontessi.api.dto;

import io.github.educontessi.domain.model.ViaCepResposta;

/**
 * Data Transfer Object {@link ViaCepResposta}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class ViaCepV1Dto extends BaseDto {

	private String cep;
	private RuaV1Dto rua;
	private BairroV1Dto bairro;
	private CidadeV1Dto cidade;
	private EstadoV1Dto estado;

	public ViaCepV1Dto() {
		this.apiVersion = "V1";
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public RuaV1Dto getRua() {
		return rua;
	}

	public void setRua(RuaV1Dto rua) {
		this.rua = rua;
	}

	public BairroV1Dto getBairro() {
		return bairro;
	}

	public void setBairro(BairroV1Dto bairro) {
		this.bairro = bairro;
	}

	public CidadeV1Dto getCidade() {
		return cidade;
	}

	public void setCidade(CidadeV1Dto cidade) {
		this.cidade = cidade;
	}

	public EstadoV1Dto getEstado() {
		return estado;
	}

	public void setEstado(EstadoV1Dto estado) {
		this.estado = estado;
	}

}
