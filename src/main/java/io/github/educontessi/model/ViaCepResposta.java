package io.github.educontessi.model;

public class ViaCepResposta {

	private String cep;
	private Rua rua;
	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isValid() {
		return this != null && getCep() != null && !getCep().isEmpty();
	}
}
