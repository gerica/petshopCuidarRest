package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.Venda;

public interface ProdutoClienteVendaService {

	void gravar(Venda venda, List<ProdutoClienteOrcamento> itens) throws PetShopBusinessException;

}
