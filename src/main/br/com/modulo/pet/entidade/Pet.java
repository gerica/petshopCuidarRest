package br.com.modulo.pet.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.compartilhado.entidade.Usuario;
import br.com.modulo.cliente.entidade.Pessoa;

/**
 * The persistent class for the tb_pet database table.
 * 
 */
@Entity
@Table(name = "tb_pet", schema = "pet")
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ds_nome")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nacimento")
	private Date dtNacimento;

	// bi-directional many-to-one association to Raca
	@ManyToOne
	@JoinColumn(name = "id_raca")
	private Raca raca;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Pet() {
	}

	public Date getDtNacimento() {
		return this.dtNacimento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Raca getRaca() {
		return raca;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setDtNacimento(Date dtNacimento) {
		this.dtNacimento = dtNacimento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}