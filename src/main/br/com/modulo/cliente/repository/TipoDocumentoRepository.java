package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {

}
