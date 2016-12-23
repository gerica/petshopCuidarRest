package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.MedicamentoLote;
import br.com.modulo.produto.repository.MedicamentoLoteRepository;
import br.com.modulo.produto.repository.MedicamentoRepository;
import br.com.modulo.produto.service.MedicamentoLoteService;

@Service
public class MedicamentoLoteServiceImpl implements MedicamentoLoteService {

	private static final Logger logger = LoggerFactory.getLogger(MedicamentoLoteServiceImpl.class);

	@Autowired
	private MedicamentoLoteRepository MedicamentoLoteRepository;

	@Autowired
	private MedicamentoRepository MedicamentoRepository;

	@Override
	public void excluir(Long idMedicamentoLote) throws PetShopBusinessException {
		logger.info("MedicamentoLoteServiceImpl.excluir()");
		MedicamentoLoteRepository.delete(idMedicamentoLote);

	}

	@Override
	public List<MedicamentoLote> findAll() throws PetShopBusinessException {
		logger.info("MedicamentoLoteServiceImpl.findAll()");
		return (List<MedicamentoLote>) MedicamentoLoteRepository.findAll();
	}

	@Override
	public List<MedicamentoLote> findByIdMedicamento(Long idMedicamento) throws PetShopBusinessException {
		logger.info("MedicamentoLoteServiceImpl.findByIdMedicamento()");
		return MedicamentoLoteRepository.findByIdMedicamento(idMedicamento);
	}

	@Override
	public void gravar(MedicamentoLote lote, Long idMedicamento) throws PetShopBusinessException {
		Medicamento Medicamento = MedicamentoRepository.findOne(idMedicamento);
		lote.setMedicamento(Medicamento);
		MedicamentoLoteRepository.save(lote);
		logger.info("MedicamentoLoteServiceImpl.incluir()");
	}

}
