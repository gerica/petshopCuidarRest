package br.com.modulo.produto.controller.wrapper;

import java.util.List;

import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.MedicamentoLote;

public class MedicamentoWrapper {

	private MedicamentoLote lote;
	private List<TipoPet> tiposPet;
	private Medicamento medicamento;
	private Long idMedicamento;

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public MedicamentoLote getLote() {
		return lote;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public void setLote(MedicamentoLote lote) {
		this.lote = lote;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<TipoPet> getTiposPet() {
		return tiposPet;
	}

	public void setTiposPet(List<TipoPet> tiposPet) {
		this.tiposPet = tiposPet;
	}

}
