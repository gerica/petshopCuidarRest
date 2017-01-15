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
	
	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	private Orcamento orcamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_venda")
	private Date dtVenda;

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	@Column(name = "nr_valor_total")
	private Double valorTotal;

	@Column(name = "nr_quantidade")
	private Long quantidade;

	@Column(name = "nr_desconto")
	private Double desconto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = { CascadeType.ALL })
	private List<ProdutoClienteVenda> produtos;

	public Double getDesconto() {
		return desconto;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public Long getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public List<ProdutoClienteVenda> getProdutos() {
		return produtos;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setProdutos(List<ProdutoClienteVenda> produtos) {
		this.produtos = produtos;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
