package br.com.modulo.venda.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.entidade.Venda;

public interface VendaService {

	List<Venda> findAll() throws PetShopBusinessException;

	Venda findById(Long idVenda) throws PetShopBusinessException;

	void gravar(Long idOrcamento) throws PetShopBusinessException;

}
