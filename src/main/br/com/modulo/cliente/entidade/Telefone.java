package br.com.modulo.cliente.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_telefone", schema = "cliente")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_telefone")
	private Long id;

	@Column(name = "ds_tipo_telefone")
	private String tipoTelefone;

	@Column(name = "nr_numero")
	private String numero;

	@Column(name = "nr_ddd")
	private String ddd;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipoTelefone == null) {
			if (other.tipoTelefone != null)
				return false;
		} else if (!tipoTelefone.equals(other.tipoTelefone))
			return false;
		return true;
	}

	public String getDdd() {
		return ddd;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipoTelefone == null) ? 0 : tipoTelefone.hashCode());
		return result;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
