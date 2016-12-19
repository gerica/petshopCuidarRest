package br.com.modulo.cliente.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Documento;

public interface DocumentoService {

	void excluir(Long idDocumento) throws PetShopBusinessException;

	List<Documento> findByIdPessoa(Long idPessoa) throws PetShopBusinessException;

	void gravar(Documento documento, Long idPessoa) throws PetShopBusinessException;

}
