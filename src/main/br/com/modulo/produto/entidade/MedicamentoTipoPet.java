package br.com.modulo.produto.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.modulo.pet.entidade.TipoPet;

@Entity
@Table(name = "tb_medicamento_tipo_pet", schema = "produto")
public class MedicamentoTipoPet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_pet")
	private TipoPet tipoPet;

	@ManyToOne
	@JoinColumn(name = "id_medicamento")
	@JsonIgnore
	private Medicamento medicamento;

	public Long getId() {
		return id;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public TipoPet getTipoPet() {
		return tipoPet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public void setTipoPet(TipoPet tipoPet) {
		this.tipoPet = tipoPet;
	}

}
