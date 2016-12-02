package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Documento;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

}
