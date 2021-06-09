package io.github.educontessi.domain.model;

import io.github.educontessi.domain.annotations.cpfcnpj.CPFCNPJ;
import io.github.educontessi.domain.enums.Sexo;
import io.github.educontessi.domain.enums.StatusCadastro;
import io.github.educontessi.domain.enums.TipoPessoa;
import io.github.educontessi.domain.helpers.util.TipoMascara;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static io.github.educontessi.domain.helpers.util.FuncoesString.*;

/**
 * Entidade {@link Pessoa} para manipular tabela de pessoas
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Entity
@Table(name = "pessoa_view")
public class Pessoa extends BaseEntity {

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusCadastro status;

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

	@Column(name = "cpf_cnpj")
	@CPFCNPJ
	private String cpfCnpj;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "rg_ie")
	private String rgIe;

	@Column(name = "cidade_id")
	private Long cidadeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_id", insertable = false, updatable = false)
	private Cidade cidade;

	@Column(name = "bairro_id")
	private Long bairroId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bairro_id", insertable = false, updatable = false)
	private Bairro bairro;

	@Column(name = "rua_id")
	private Long ruaId;

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

	@Column(name = "numero_contato_principal")
	private String numeroContatoPrincipal;

	@Column(name = "obs_contato_principal")
	private String obsContatoPrincipal;

	@Column(name = "numero_contato_alternativo")
	private String numeroContatoAlternativo;

	@Column(name = "obs_contato_alternativo")
	private String obsContatoAlternativo;

	@Email
	@Size(max = 100, message = "E-mail não pode exceder mais que 100 caracteres")
	@Column(name = "email_principal")
	private String emailPrincipal;

	@Column(name = "obs_email_principal")
	private String obsEmailPrincipal;

	@Email
	@Size(max = 100, message = "E-mail não pode exceder mais que 100 caracteres")
	@Column(name = "email_alternativo")
	private String emailAlternativo;

	@Column(name = "obs_email_alternativo")
	private String obsEmailAlternativo;

	@Column(name = "observacao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	public StatusCadastro getStatus() {
		return status;
	}

	public void setStatus(StatusCadastro status) {
		this.status = status;
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
		this.nomeRazao = formatarNome(nomeRazao);
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpfCnpj() {
		return adicionaMascara(getTipoPessoa(), cpfCnpj);
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = removeMascaraDeNumeros(cpfCnpj);
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRgIe() {
		return rgIe;
	}

	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Long getBairroId() {
		return bairroId;
	}

	public void setBairroId(Long bairroId) {
		this.bairroId = bairroId;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Long getRuaId() {
		return ruaId;
	}

	public void setRuaId(Long ruaId) {
		this.ruaId = ruaId;
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

	public String getNumeroContatoPrincipal() {
		return numeroContatoPrincipal;
	}

	public void setNumeroContatoPrincipal(String numeroContatoPrincipal) {
		this.numeroContatoPrincipal = numeroContatoPrincipal;
	}

	public String getObsContatoPrincipal() {
		return obsContatoPrincipal;
	}

	public void setObsContatoPrincipal(String obsContatoPrincipal) {
		this.obsContatoPrincipal = obsContatoPrincipal;
	}

	public String getNumeroContatoAlternativo() {
		return numeroContatoAlternativo;
	}

	public void setNumeroContatoAlternativo(String numeroContatoAlternativo) {
		this.numeroContatoAlternativo = numeroContatoAlternativo;
	}

	public String getObsContatoAlternativo() {
		return obsContatoAlternativo;
	}

	public void setObsContatoAlternativo(String obsContatoAlternativo) {
		this.obsContatoAlternativo = obsContatoAlternativo;
	}

	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getObsEmailPrincipal() {
		return obsEmailPrincipal;
	}

	public void setObsEmailPrincipal(String obsEmailPrincipal) {
		this.obsEmailPrincipal = obsEmailPrincipal;
	}

	public String getEmailAlternativo() {
		return emailAlternativo;
	}

	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}

	public String getObsEmailAlternativo() {
		return obsEmailAlternativo;
	}

	public void setObsEmailAlternativo(String obsEmailAlternativo) {
		this.obsEmailAlternativo = obsEmailAlternativo;
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

}
