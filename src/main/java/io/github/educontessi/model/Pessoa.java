package io.github.educontessi.model;

import static io.github.educontessi.helpers.util.FuncoesString.adicionaMascara;
import static io.github.educontessi.helpers.util.FuncoesString.removeMascaraDeNumeros;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.educontessi.helpers.util.TipoMascara;
import io.github.educontessi.model.enumeracoes.Sexo;
import io.github.educontessi.model.enumeracoes.TipoPessoa;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nome_razao")
	private String nomeRazao;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@NotNull
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Column(name = "data_nascimento_fundacao")
	private LocalDate dataNascimentoFundacao;

	@Column(name = "rg_ie")
	private String rgIe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_id", insertable = false, updatable = false)
	private Cidade cidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bairro_id", insertable = false, updatable = false)
	private Bairro bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rua_id", insertable = false, updatable = false)
	private Rua rua;

	@Column(name = "cep")
	private String cep;

	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "proximidade")
	private String proximidade;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "celular")
	private String celular;

	@Email
	@Size(max = 100, message = "E-mail não pode exceder mais que 100")
	@Column(name = "email")
	private String email;

	@Column(name = "observacao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpfCnpj() {
		return adicionaMascara(getTipoMascara(), cpfCnpj);
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = removeMascaraDeNumeros(cpfCnpj);
	}

	public LocalDate getDataNascimentoFundacao() {
		return dataNascimentoFundacao;
	}

	public void setDataNascimentoFundacao(LocalDate dataNascimentoFundacao) {
		this.dataNascimentoFundacao = dataNascimentoFundacao;
	}

	public String getRgIe() {
		return rgIe;
	}

	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public String getCep() {
		return adicionaMascara(TipoMascara.CEP, cep);
	}

	public void setCep(String cep) {
		this.cep = removeMascaraDeNumeros(cep);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getProximidade() {
		return proximidade;
	}

	public void setProximidade(String proximidade) {
		this.proximidade = proximidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@JsonIgnore
	private TipoMascara getTipoMascara() {
		return tipoPessoa.equals(TipoPessoa.F) ? TipoMascara.CPF : TipoMascara.CNPJ;
	}

}
