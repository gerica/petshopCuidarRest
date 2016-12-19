package br.com.modulo.cliente.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;

public interface PessoaService {

	Iterable<Pessoa> findAll() throws PetShopBusinessException;

	Pessoa findById(Long idPessoa) throws PetShopBusinessException;

	List<Pessoa> findByName(String nomePessoa) throws PetShopBusinessException;

	Pessoa gravar(Pessoa pessoa) throws PetShopBusinessException;

}
