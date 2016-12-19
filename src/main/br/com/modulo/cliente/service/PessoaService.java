package br.com.modulo.cliente.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;

public interface PessoaService {

	Iterable<Pessoa> findAll() throws PetShopBusinessException;

	Pessoa findById(Long idPessoa) throws PetShopBusinessException;

	Pessoa gravar(Pessoa pessoa) throws PetShopBusinessException;

}
