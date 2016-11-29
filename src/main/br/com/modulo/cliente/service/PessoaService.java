package br.com.modulo.cliente.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;

public interface PessoaService {

	void incluir(Pessoa pessoa) throws PetShopBusinessException;

	void alterar(Pessoa pessoa) throws PetShopBusinessException;

	Iterable<Pessoa> findAll() throws PetShopBusinessException;

}
