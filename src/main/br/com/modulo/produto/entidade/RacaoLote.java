package br.com.modulo.produto.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_racao_lote", schema = "produto")
public class RacaoLote extends Lote {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_racao")
	private Racao racao;

	public Racao getRacao() {
		return racao;
	}

	public void setRacao(Racao racao) {
		this.racao = racao;
	}

}
