package io.github.educontessi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static io.github.educontessi.domain.helpers.util.FuncoesString.removeMascaraDeNumeros;

/**
 * Entidade {@link ViaCepJson} para obter resposta da API de consulta de CEP
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class ViaCepJson {

	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String unidade;
	private String ibge;
	private String gia;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = removeMascaraDeNumeros(cep);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	@JsonIgnore
	public boolean isValid() {
		return getCep() != null && !getCep().isEmpty() && removeMascaraDeNumeros(getCep()).length() == 8;
	}

}
