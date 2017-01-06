package br.com.modulo.produto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.Lote;

public interface LoteRepository extends CrudRepository<Lote, Long> {

}
