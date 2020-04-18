package io.github.educontessi.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.github.educontessi.domain.model.Pessoa;

/**
 * Classe usada para filtro de {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class PessoaFilter {

	public static final String NOME_RAZAO = "nomeRazao";
	public static final String CPF_CNPJ = "cpfCnpj";
	public static final String DATA_NASCIMENTO = "dataNascimento";

	private String nomeRazao;
	private String cpfCnpj;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
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

}
