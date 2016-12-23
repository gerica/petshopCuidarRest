package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Racao;
import br.com.modulo.produto.entidade.RacaoLote;
import br.com.modulo.produto.repository.RacaoLoteRepository;
import br.com.modulo.produto.repository.RacaoRepository;
import br.com.modulo.produto.service.RacaoLoteService;

@Service
public class RacaoLoteServiceImpl implements RacaoLoteService {

	private static final Logger logger = LoggerFactory.getLogger(RacaoLoteServiceImpl.class);

	@Autowired
	private RacaoLoteRepository racaoLoteRepository;

	@Autowired
	private RacaoRepository racaoRepository;

	@Override
	public void excluir(Long idRacaoLote) throws PetShopBusinessException {
		logger.info("RacaoLoteServiceImpl.excluir()");
		racaoLoteRepository.delete(idRacaoLote);

	}

	@Override
	public List<RacaoLote> findAll() throws PetShopBusinessException {
		logger.info("RacaoLoteServiceImpl.findAll()");
		return (List<RacaoLote>) racaoLoteRepository.findAll();
	}

	@Override
	public List<RacaoLote> findByIdRacao(Long idRacao) throws PetShopBusinessException {
		logger.info("RacaoLoteServiceImpl.findByIdRacao()");
		return racaoLoteRepository.findByIdRacao(idRacao);
	}

	@Override
	public void gravar(RacaoLote lote, Long idRacao) throws PetShopBusinessException {
		Racao racao = racaoRepository.findOne(idRacao);
		lote.setRacao(racao);
		racaoLoteRepository.save(lote);
		logger.info("RacaoLoteServiceImpl.incluir()");
	}

}
