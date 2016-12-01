package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

}
