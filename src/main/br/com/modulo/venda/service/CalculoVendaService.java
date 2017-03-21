package br.com.modulo.venda.service;

import java.util.List;

import javax.persistence.RollbackException;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;

public interface CalculoVendaService {

	public Double getValorVenda(Long quantidade, List<? extends Lote> lotes, boolean altarEstoque)
			throws PetShopBusinessException, RollbackException;

	Long getQuatidadeProdutos(List<? extends Lote> lotes) throws PetShopBusinessException;

	Double getValorProduto(List<? extends Lote> lotes) throws PetShopBusinessException;

}
