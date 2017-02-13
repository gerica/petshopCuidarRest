package br.com.modulo.financeiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapper;
import br.com.modulo.financeiro.entidade.RelatorioVenda;
import br.com.modulo.financeiro.repository.RelatorioVendaRepository;
import br.com.modulo.financeiro.service.RelatorioVendaService;

@Service
public class RelatorioVendaServiceImpl implements RelatorioVendaService {

	@Autowired
	private RelatorioVendaRepository repository;

	@Override
	public List<RelatorioVenda> consultar(RelatorioVendaWrapper wrapper) throws PetShopBusinessException {
		List<RelatorioVenda> result = null;
		switch (wrapper.getPeriodo()) {
		case MENSAL:
			result = repository.consultarMensal(wrapper.getAno(), wrapper.getPeriodoValor());
			break;
		case BIMESTRAL:
			result = repository.consultarBimenstral(wrapper.getAno(), wrapper.getPeriodoValor());
			break;
		case TRIMESTRAL:
			result = repository.consultarTrimenstral(wrapper.getAno(), wrapper.getPeriodoValor());
			break;
		case SIMESTRAL:
			result = repository.consultarSimenstral(wrapper.getAno(), wrapper.getPeriodoValor());
			break;
		case ANUAL:
			result = repository.consultarAnual(wrapper.getAno());
			break;

		default:
			break;
		}

		return result;

	}

}
