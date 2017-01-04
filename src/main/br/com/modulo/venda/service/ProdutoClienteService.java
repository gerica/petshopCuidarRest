package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.entidade.Orcamento;

public interface ProdutoClienteService {

	void gravar(Orcamento orcamento, List<ItemVenda> itens) throws PetShopBusinessException;

}
