package br.com.modulo.pet.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;

public interface TipoPetService {

	List<TipoPet> findAll() throws PetShopBusinessException;

}
