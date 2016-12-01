package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Estado;

public interface EstadoRepository extends CrudRepository<Estado, Long> {

}
