package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.MedicamentoTipoPet;
import br.com.modulo.produto.repository.MedicamentoTipoPetRepository;
import br.com.modulo.produto.service.MedicamentoTipoPetService;
import br.com.util.UtilsEmpty;

@Service
public class MedicamentoTipoPetServiceImpl implements MedicamentoTipoPetService {
	private static final Logger logger = LoggerFactory.getLogger(MedicamentoTipoPetServiceImpl.class);

	@Autowired
	private MedicamentoTipoPetRepository repository;

	@Override
	public void gravar(Medicamento medicamento, List<TipoPet> listaTipoPet) throws PetShopBusinessException {

		apagarTodosPorMedicamento(medicamento);
		MedicamentoTipoPet medTipoPet = null;
		for (TipoPet tipoPet : listaTipoPet) {
			medTipoPet = new MedicamentoTipoPet();
			medTipoPet.setMedicamento(medicamento);
			medTipoPet.setTipoPet(tipoPet);
			repository.save(medTipoPet);
		}
		logger.info("MedicamentoTipoPetServiceImpl.gravar()");

	}

	private void apagarTodosPorMedicamento(Medicamento medicamento) {
		List<MedicamentoTipoPet> result = repository.findByMedicamento(medicamento);
		if (!UtilsEmpty.isEmpty(result)) {
			repository.delete(result);
		}

	}

}
