package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;

public interface ProdutoClienteOrcamentoService {

	void excluir(Long idOrcamento) throws PetShopBusinessException;

	List<ProdutoClienteOrcamento> findByOrcamento(Orcamento orcamento)throws PetShopBusinessException;
	
	void gravar(Orcamento orcamento, List<ItemVenda> itens) throws PetShopBusinessException;

}
