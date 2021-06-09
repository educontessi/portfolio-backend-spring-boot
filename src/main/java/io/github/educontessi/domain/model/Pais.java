package io.github.educontessi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.github.educontessi.domain.helpers.util.FuncoesString.formatarNome;

/**
 * Entidade {@link Pais} para manipular tabela de países
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Entity
@Table(name = "pais_view")
public class Pais extends BaseEntity {

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Size(min = 2, max = 10)
	@Column(name = "sigla")
	private String sigla;

	@NotNull
	@Size(min = 2, max = 10)
	@Column(name = "bacen")
	private String bacen;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = formatarNome(nome);
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getBacen() {
		return bacen;
	}

	public void setBacen(String bacen) {
		this.bacen = bacen;
	}

}
