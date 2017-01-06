package br.com.modulo.venda.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.venda.entidade.Venda;

public interface VendaRepository extends CrudRepository<Venda, Long> {

}
