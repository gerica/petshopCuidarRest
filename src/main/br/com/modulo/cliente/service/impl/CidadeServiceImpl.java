package br.com.modulo.cliente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Estado;
import br.com.modulo.cliente.repository.CidadeRepository;
import br.com.modulo.cliente.service.CidadeService;

public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public Cidade findByEstado(Long idEstado) throws PetShopBusinessException {
		Estado e = new Estado();
		e.setId(idEstado);
		return cidadeRepository.findByEstado(e);
	}

}
