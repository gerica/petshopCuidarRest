package br.com.modulo.produto.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;

public interface LoteService {

	List<? extends Lote> findByIdProduto(Long idProduto, TipoProdutoEnum tipoProdutoEnum) throws PetShopBusinessException;

}
