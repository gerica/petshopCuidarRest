package br.com.modulo.venda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.venda.service.CalculoVendaService;

@Service
public class CalculoVendaServiceImpl implements CalculoVendaService {

	@Autowired
	private LoteService loteSerivce;

	@Override
	public Long getQuatidadeProdutos(List<? extends Lote> lotes) throws PetShopBusinessException {
		Long qtd = new Long(0);
		for (Lote lote : lotes) {
			qtd += lote.getQuantidade();
		}

		return qtd;
	}

	@Override
	public Double getValorProduto(List<? extends Lote> lotes) throws PetShopBusinessException {
		Double valor = 0.0;
		int qtd = 0;
		for (Lote lote : lotes) {
			qtd++;
			valor += lote.getValor();
		}

		return valor / qtd;
	}

	@Override
	public Double getValorVenda(Long quantidade, List<? extends Lote> lotes) throws PetShopBusinessException {
		Double result = 0.0;
		for (Lote l : lotes) {
			if (l.getQuantidade() >= quantidade) {
				result += l.getValorVenda() * quantidade;
				loteSerivce.alterarQuantidadeLote(l, quantidade);
				return result;
			} else {
				result = l.getValorVenda() * l.getQuantidade();
				loteSerivce.alterarQuantidadeLote(l, l.getQuantidade());
				quantidade = quantidade - l.getQuantidade();
			}
		}
		throw new PetShopBusinessException("O produto não tem a quantidade necessária no estoque.");
	}

}
