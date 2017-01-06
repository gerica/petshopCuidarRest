package br.com.modulo.venda.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.venda.entidade.ProdutoClienteVenda;

public interface ProdutoClienteVendaRepository extends CrudRepository<ProdutoClienteVenda, Long> {

}
