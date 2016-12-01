package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
