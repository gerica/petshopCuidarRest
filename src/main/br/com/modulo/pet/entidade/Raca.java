package br.com.modulo.pet.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tb_raca database table.
 * 
 */
@Entity
@Table(name = "tb_raca", schema = "pet")
public class Raca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_raca")
	private Long id;

	@Column(name = "ds_nome")
	private String dsNome;

	// bi-directional many-to-one association to TipoPet
	@ManyToOne
	@JoinColumn(name = "id_tipo_pet")
	private TipoPet tipoPet;

	public Raca() {
	}

	public String getDsNome() {
		return this.dsNome;
	}

	public TipoPet getTipoPet() {
		return tipoPet;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoPet(TipoPet tipoPet) {
		this.tipoPet = tipoPet;
	}

}