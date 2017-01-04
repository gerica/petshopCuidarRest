package br.com.modulo.venda.controller.wrapper;

import java.util.Date;
import java.util.List;

import br.com.modulo.cliente.entidade.Pessoa;

public class OrcamentoWrapper {

	private Long id;

	private Pessoa pessoa;

	private Date dtOrcamento;

	private List<ItemVenda> itens;

	public Date getDtOrcamento() {
		return dtOrcamento;
	}

	public Long getId() {
		return id;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setDtOrcamento(Date dtOrcamento) {
		this.dtOrcamento = dtOrcamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
