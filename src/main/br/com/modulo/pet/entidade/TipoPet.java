package br.com.modulo.pet.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the tb_tipo_pet database table.
 * 
 */
@Entity
@Table(name = "tb_tipo_pet", schema = "pet")
public class TipoPet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_pet")
	private Long id;

	@Column(name = "ds_nome")
	private String dsNome;

	public TipoPet() {
	}

	public String getDsNome() {
		return this.dsNome;
	}

	public Long getId() {
		return id;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public void setId(Long id) {
		this.id = id;
	}

}