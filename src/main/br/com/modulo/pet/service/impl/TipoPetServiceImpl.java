package br.com.modulo.pet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.pet.repository.TipoPetRepository;
import br.com.modulo.pet.service.TipoPetService;

@Service
public class TipoPetServiceImpl implements TipoPetService {

	private static final Logger logger = LoggerFactory.getLogger(TipoPetServiceImpl.class);

	@Autowired
	private TipoPetRepository tipoPetRepository;

	@Override
	public List<TipoPet> findAll() throws PetShopBusinessException {
		logger.info("TipoPetServiceImpl.findAll()");
		return (List<TipoPet>) tipoPetRepository.findAll();
	}

}
