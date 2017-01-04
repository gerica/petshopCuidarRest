package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.controller.wrapper.OrcamentoWrapper;
import br.com.modulo.venda.entidade.Orcamento;

public interface OrcamentoService {

	List<OrcamentoWrapper> findAll() throws PetShopBusinessException;

	OrcamentoWrapper findById(Long idOrcamento) throws PetShopBusinessException;

	Long findCount() throws PetShopBusinessException;

	Orcamento gravar(Long idOrcamento, Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException;

}
