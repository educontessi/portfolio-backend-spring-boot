package io.github.educontessi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.github.educontessi.domain.helpers.util.FuncoesString.formatarNome;

/**
 * Entidade {@link Rua} para manipular tabela de ruas
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Entity
@Table(name = "rua_view")
public class Rua extends BaseEntity {

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Column(name = "cidade_id")
	private Long cidadeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_id", insertable = false, updatable = false)
	private Cidade cidade;

	public String getNome() {
		return nome;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public void setNome(String nome) {
		this.nome = formatarNome(nome);
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
