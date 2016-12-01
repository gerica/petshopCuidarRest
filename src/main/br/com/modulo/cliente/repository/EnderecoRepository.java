package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
