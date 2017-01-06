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
@Table(name = "tb_produto_cliente_venda", schema = "venda")
public class ProdutoClienteVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_venda")
	@JsonIgnore
	private Venda venda;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	@JsonIgnore
	private Produto produto;

	@Column(name = "nr_quantidade")
	private Long quantidade;

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
