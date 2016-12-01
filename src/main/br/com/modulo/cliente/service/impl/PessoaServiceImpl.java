package br.com.modulo.cliente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.repository.PessoaRepository;
import br.com.modulo.cliente.service.PessoaService;

public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void incluir(Pessoa pessoa) throws PetShopBusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(Pessoa pessoa) throws PetShopBusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

}
