package br.com.modulo.produto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	List<Produto> findByNomeContainingIgnoreCase(String valor);

}
