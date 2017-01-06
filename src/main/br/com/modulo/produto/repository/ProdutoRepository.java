package br.com.modulo.produto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	Produto findById(Long id);

	List<Produto> findByNomeContainingIgnoreCaseOrMarcaContainingIgnoreCase(String valor1, String valor2);

	// List<Produto> findByMarca(String valor);

}
