package br.com.modulo.pet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.Raca;
import br.com.modulo.pet.repository.RacaRepository;
import br.com.modulo.pet.service.RacaService;

@Service
public class RacaServiceImpl implements RacaService {

	private static final Logger logger = LoggerFactory.getLogger(RacaServiceImpl.class);

	@Autowired
	private RacaRepository racaRepository;

	@Override
	public List<Raca> findAll() throws PetShopBusinessException {
		logger.info("RacaServiceImpl.findAll()");
		return (List<Raca>) racaRepository.findAll();
	}

	@Override
	public List<Raca> findRacaByTipo(Long idTipoPet) throws PetShopBusinessException {
		logger.info("RacaServiceImpl.findRacaByTipo()");
		return racaRepository.findAllByTipoPet(idTipoPet);
	}

}
