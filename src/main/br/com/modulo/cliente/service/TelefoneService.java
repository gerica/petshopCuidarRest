package br.com.modulo.cliente.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Telefone;

public interface TelefoneService {

	void excluir(Long idTelefone) throws PetShopBusinessException;

	List<Telefone> findByIdPessoa(Long idPessoa) throws PetShopBusinessException;

	void gravar(Telefone telefone, Long idPessoa) throws PetShopBusinessException;

}
