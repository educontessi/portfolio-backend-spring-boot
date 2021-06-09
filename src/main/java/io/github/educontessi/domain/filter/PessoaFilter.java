package io.github.educontessi.domain.filter;

import io.github.educontessi.domain.model.Pessoa;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static io.github.educontessi.domain.helpers.util.FuncoesString.removeMascaraDeNumeros;

/**
 * Classe usada para filtro de {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class PessoaFilter {

	public static final String COLUNA_NOME_RAZAO = "nomeRazao";
	public static final String COLUNA_CPF_CNPJ = "cpfCnpj";
	public static final String COLUNA_DATA_NASCIMENTO = "dataNascimento";

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
		this.cpfCnpj = removeMascaraDeNumeros(cpfCnpj);
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
