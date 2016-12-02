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
@Table(name = "tb_documento", schema = "cliente")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_documento")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_tipo_documento", nullable = false)
	private TipoDocumento tipoDocumento;

	@Column(name = "nr_numero", nullable = false)
	private String numero;

	@Column(name = "ds_complemento")
	private String complemento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_estado", nullable = true)
	private Estado estado;

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
		Documento other = (Documento) obj;
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
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return true;
	}

	public String getComplemento() {
		return complemento;
	}

	public Estado getEstado() {
		return estado;
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

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
