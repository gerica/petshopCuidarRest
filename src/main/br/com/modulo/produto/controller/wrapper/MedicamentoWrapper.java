package br.com.modulo.produto.controller.wrapper;

import br.com.modulo.produto.entidade.MedicamentoLote;

public class MedicamentoWrapper {

	private MedicamentoLote lote;
	private Long idMedicamento;

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public MedicamentoLote getLote() {
		return lote;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public void setLote(MedicamentoLote lote) {
		this.lote = lote;
	}

}
