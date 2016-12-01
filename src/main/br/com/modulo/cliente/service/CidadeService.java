package br.com.modulo.cliente.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;

public interface CidadeService {

	Cidade findByEstado(Long idEstado) throws PetShopBusinessException;

}
