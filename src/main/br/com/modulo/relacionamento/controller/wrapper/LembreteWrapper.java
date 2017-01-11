package br.com.modulo.relacionamento.controller.wrapper;

import br.com.modulo.relacionamento.entidade.Lembrete;

public class LembreteWrapper {

	private Lembrete lembrete;
	private Long idPessoa;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}

}
