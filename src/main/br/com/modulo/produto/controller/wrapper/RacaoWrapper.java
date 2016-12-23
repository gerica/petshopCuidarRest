package br.com.modulo.produto.controller.wrapper;

import br.com.modulo.produto.entidade.RacaoLote;

public class RacaoWrapper {

	private RacaoLote racaoLote;
	private Long idRacao;

	public Long getIdRacao() {
		return idRacao;
	}

	public RacaoLote getRacaoLote() {
		return racaoLote;
	}

	public void setIdRacao(Long idRacao) {
		this.idRacao = idRacao;
	}

	public void setRacaoLote(RacaoLote racaoLote) {
		this.racaoLote = racaoLote;
	}
}
