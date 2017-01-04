package br.com.modulo.venda.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.compartilhado.entidade.Usuario;
import br.com.modulo.cliente.entidade.Pessoa;

@Entity
@Table(name = "tb_orcamento", schema = "venda")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orcamento", cascade = { CascadeType.ALL })
	private List<ProdutoCliente> produto;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_orcamento")
	private Date dtOrcamento;

	public Date getDtOrcamento() {
		return dtOrcamento;
	}

	public Long getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public List<ProdutoCliente> getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setDtOrcamento(Date dtOrcamento) {
		this.dtOrcamento = dtOrcamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setProduto(List<ProdutoCliente> produto) {
		this.produto = produto;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
