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
@Table(name = "tb_venda", schema = "venda")
public class Venda implements Serializable {
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

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_venda")
	private Date dtVenda;

	@Column(name = "nr_valor_total")
	private Double valorTotal;

	@Column(name = "nr_quantidade")
	private Long quantidade;

	@Column(name = "nr_desconto")
	private Double desconto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = { CascadeType.ALL })
	private List<ProdutoClienteVenda> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public List<ProdutoClienteVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoClienteVenda> produtos) {
		this.produtos = produtos;
	}

}
