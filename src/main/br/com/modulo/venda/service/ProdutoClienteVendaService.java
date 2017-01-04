package br.com.modulo.venda.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.Venda;

public interface ProdutoClienteVendaService {

	void gravar(Venda venda, ProdutoClienteOrcamento itens) throws PetShopBusinessException;

}
