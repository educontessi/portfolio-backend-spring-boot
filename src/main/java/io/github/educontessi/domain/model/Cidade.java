package io.github.educontessi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.github.educontessi.domain.helpers.util.FuncoesString.formatarNome;

/**
 * Entidade {@link Cidade} para manipular tabela de cidades
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Entity
@Table(name = "cidade_view")
public class Cidade extends BaseEntity {

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Column(name = "estado_id")
	private Long estadoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_id", insertable = false, updatable = false)
	private Estado estado;

	@NotNull
	@Column(name = "ibge")
	private Integer ibge;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = formatarNome(nome);
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}

}
