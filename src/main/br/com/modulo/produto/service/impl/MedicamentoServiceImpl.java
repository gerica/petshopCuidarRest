package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.repository.MedicamentoRepository;
import br.com.modulo.produto.service.MedicamentoService;
import br.com.modulo.produto.service.MedicamentoTipoPetService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	private static final Logger logger = LoggerFactory.getLogger(MedicamentoServiceImpl.class);

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private MedicamentoTipoPetService medicamentoTipoPetService;

	@Override
	public void excluir(Long idMedicamento) throws PetShopBusinessException {
		logger.info("MedicamentoServiceImpl.excluir()");
		medicamentoRepository.delete(idMedicamento);

	}

	@Override
	public List<Medicamento> findAll() throws PetShopBusinessException {
		logger.info("MedicamentoServiceImpl.findAll()");
		return (List<Medicamento>) medicamentoRepository.findAll();
	}

	@Override
	public void gravar(Medicamento Medicamento, List<TipoPet> tiposPet) throws PetShopBusinessException {
		Medicamento medBD = medicamentoRepository.save(Medicamento);
		medicamentoTipoPetService.gravar(medBD, tiposPet);
		logger.info("MedicamentoServiceImpl.incluir()");
	}

}
