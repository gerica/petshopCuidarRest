package br.com.modulo.produto.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Produto;

public interface ProdutoService {

	List<Produto> findByDescricao(String descricao) throws PetShopBusinessException;

}
