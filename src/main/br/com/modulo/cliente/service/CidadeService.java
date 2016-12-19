package br.com.modulo.cliente.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;

public interface CidadeService {

	List<Cidade> findByEstado(Long idEstado) throws PetShopBusinessException;

}
