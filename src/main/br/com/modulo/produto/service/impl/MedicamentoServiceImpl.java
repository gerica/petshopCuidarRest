package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.repository.MedicamentoRepository;
import br.com.modulo.produto.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	private static final Logger logger = LoggerFactory.getLogger(MedicamentoServiceImpl.class);

	@Autowired
	private MedicamentoRepository MedicamentoRepository;

	@Override
	public void excluir(Long idMedicamento) throws PetShopBusinessException {
		logger.info("MedicamentoServiceImpl.excluir()");
		MedicamentoRepository.delete(idMedicamento);

	}

	@Override
	public List<Medicamento> findAll() throws PetShopBusinessException {
		logger.info("MedicamentoServiceImpl.findAll()");
		return (List<Medicamento>) MedicamentoRepository.findAll();
	}

	@Override
	public void gravar(Medicamento Medicamento) throws PetShopBusinessException {
		MedicamentoRepository.save(Medicamento);
		logger.info("MedicamentoServiceImpl.incluir()");
	}

}
