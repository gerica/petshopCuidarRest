package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;

public interface CalculoVendaService {

	Long getQuatidadeProdutos(List<? extends Lote> lotes) throws PetShopBusinessException;

	Double getValorProduto(List<? extends Lote> lotes) throws PetShopBusinessException;

	Double getValorVenda(Long quantidade, List<? extends Lote> lotes) throws PetShopBusinessException;	

}
