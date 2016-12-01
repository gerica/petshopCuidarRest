package br.com.modulo.cliente.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Estado;

public interface EstadoService {

	Iterable<Estado> findAll() throws PetShopBusinessException;

}
