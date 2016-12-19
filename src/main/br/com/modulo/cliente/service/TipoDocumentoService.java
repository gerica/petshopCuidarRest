package br.com.modulo.cliente.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.TipoDocumento;

public interface TipoDocumentoService {

	Iterable<TipoDocumento> findAll() throws PetShopBusinessException;

}
