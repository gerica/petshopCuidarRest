package br.com.modulo.produto.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Medicamento;

public interface MedicamentoTipoPetService {

	void gravar(Medicamento medicamento, List<TipoPet> listaTipoPet) throws PetShopBusinessException;

}
