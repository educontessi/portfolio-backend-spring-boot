package io.github.educontessi.api.dto;

import io.github.educontessi.domain.enums.Sexo;
import io.github.educontessi.domain.enums.StatusCadastro;
import io.github.educontessi.domain.enums.TipoPessoa;
import io.github.educontessi.domain.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDate;

/**
 * Data Transfer Object {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class PessoaV1Dto extends BaseDto {

	private StatusCadastro status;
	private TipoPessoa tipoPessoa;
	private String nomeRazao;
	private LocalDate dataCadastro;
	private String cpfCnpj;
	private LocalDate dataNascimento;
	private String rgIe;

	private Long cidadeId;
	private CidadeV1Dto cidade;

	private Long bairroId;
	private BairroV1Dto bairro;

	private Long ruaId;
	private RuaV1Dto rua;

	private String cep;
	private String numero;
	private String complemento;
	private String proximidade;
	private String numeroContatoPrincipal;
	private String obsContatoPrincipal;
	private String numeroContatoAlternativo;
	private String obsContatoAlternativo;
	private String emailPrincipal;
	private String obsEmailPrincipal;
	private String emailAlternativo;
	private String obsEmailAlternativo;
	private String observacao;
	private Sexo sexo;

	public PessoaV1Dto() {
		this.apiVersion = "V1";
	}

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
		this.nomeRazao = nomeRazao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public CidadeV1Dto getCidade() {
		return cidade;
	}

	public void setCidade(CidadeV1Dto cidade) {
		this.cidade = cidade;
	}

	public BairroV1Dto getBairro() {
		return bairro;
	}

	public void setBairro(BairroV1Dto bairro) {
		this.bairro = bairro;
	}

	public RuaV1Dto getRua() {
		return rua;
	}

	public void setRua(RuaV1Dto rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Long getBairroId() {
		return bairroId;
	}

	public void setBairroId(Long bairroId) {
		this.bairroId = bairroId;
	}

	public Long getRuaId() {
		return ruaId;
	}

	public void setRuaId(Long ruaId) {
		this.ruaId = ruaId;
	}

}
