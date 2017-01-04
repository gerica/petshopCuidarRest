package br.com.modulo.venda.controller.wrapper;

public class ItemVenda {

	private Long idProdutoCliente;
	private Long idProduto;
	private Long quantidadeVenda;
	private Double valorVenda;
	private String marca;
	private String nome;
	private Double desconto;
	private Long quantidade;
	private Double valor;

	public Double getDesconto() {
		return desconto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Long getIdProdutoCliente() {
		return idProdutoCliente;
	}

	public String getMarca() {
		return marca;
	}

	public String getNome() {
		return nome;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Long getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public Double getValor() {
		return valor;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setIdProdutoCliente(Long idProdutoCliente) {
		this.idProdutoCliente = idProdutoCliente;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public void setQuantidadeVenda(Long quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

}
