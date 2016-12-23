package br.com.modulo.produto.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_medicamento_lote", schema = "produto")
public class MedicamentoLote extends Lote {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_medicamento")
	private Medicamento medicamento;

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

}
