package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;
import br.com.modulo.produto.repository.LoteRepository;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.produto.service.MedicamentoLoteService;
import br.com.modulo.produto.service.RacaoLoteService;

@Service
public class LoteServiceImpl implements LoteService {

	private static final Logger logger = LoggerFactory.getLogger(LoteServiceImpl.class);

	@Autowired
	private RacaoLoteService racaoLoteService;

	@Autowired
	private MedicamentoLoteService medicamentoLoteService;

	@Autowired
	private LoteRepository loteRepository;

	@Override
	public void alterarQuantidadeLote(Lote lote, Long quantidade) throws PetShopBusinessException {
		Lote loteDb = loteRepository.findOne(lote.getId());
		loteDb.setQuantidade(loteDb.getQuantidade() - quantidade);
		loteRepository.save(loteDb);

	}

	@Override
	public List<? extends Lote> findByIdProduto(Long idProduto, TipoProdutoEnum tipoProdutoEnum)
			throws PetShopBusinessException {
		logger.info("LoteServiceImpl.findByIdProduto()");
		List<? extends Lote> result = null;
		switch (tipoProdutoEnum) {
		case RACAO:
			result = racaoLoteService.findByIdRacao(idProduto);
			break;
		case MEDICAMENTO:
			result = medicamentoLoteService.findByIdMedicamento(idProduto);
			break;
		}
		return result;
	}

}
