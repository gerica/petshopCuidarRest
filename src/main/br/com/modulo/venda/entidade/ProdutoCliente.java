package br.com.modulo.venda.entidade;

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

import br.com.modulo.produto.entidade.Produto;

@Entity
@Table(name = "tb_produto_cliente", schema = "venda")
public class ProdutoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	@JsonIgnore
	private Orcamento orcamento;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	@JsonIgnore
	private Produto produto;

	@Column(name = "nr_quantidade")
	private Long quantidade;

	@Column(name = "nr_desconto")
	private Double desconto;

	public Double getDesconto() {
		return desconto;
	}

	public Long getId() {
		return id;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
