package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.service.impl.PetServiceImpl;
import br.com.modulo.produto.entidade.Racao;
import br.com.modulo.produto.repository.RacaoRepository;
import br.com.modulo.produto.service.RacaoService;

@Service
public class RacaoServiceImpl implements RacaoService {

	private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

	@Autowired
	private RacaoRepository racaoRepository;

	@Override
	public void excluir(Long idRacao) throws PetShopBusinessException {
		logger.info("PetServiceImpl.excluir()");
		racaoRepository.delete(idRacao);

	}

	@Override
	public List<Racao> findAll() throws PetShopBusinessException {
		logger.info("RacaoServiceImpl.findAll()");
		return (List<Racao>) racaoRepository.findAll();
	}

	@Override
	public void gravar(Racao racao) throws PetShopBusinessException {
		racaoRepository.save(racao);
		logger.info("PetServiceImpl.incluir()");
	}

}
