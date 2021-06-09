package io.github.educontessi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.github.educontessi.domain.helpers.util.FuncoesString.formatarNome;

/**
 * Entidade {@link Estado} para manipular tabela de estados
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Entity
@Table(name = "estado_view")
public class Estado extends BaseEntity {

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Size(min = 2, max = 10)
	@Column(name = "uf")
	private String uf;

	@NotNull
	@Column(name = "pais_id")
	private Long paisId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id", insertable = false, updatable = false)
	private Pais pais;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = formatarNome(nome);
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
