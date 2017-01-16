package br.com.modulo.financeiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapper;
import br.com.modulo.financeiro.repository.RelatorioVendaRepository;
import br.com.modulo.financeiro.service.RelatorioVendaService;

@Service
public class RelatorioVendaServiceImpl implements RelatorioVendaService {

	@Autowired
	private RelatorioVendaRepository repository;

	@Override
	public void consultar(RelatorioVendaWrapper wrapper) throws PetShopBusinessException {
		switch (wrapper.getPeriodo()) {
		case MENSAL:
			wrapper.getPeriodoValor();
			repository.consultarMensal(wrapper.getAno(), wrapper.getPeriodoValor());

			break;

		default:
			break;
		}

	}

}
