package br.com.modulo.cliente.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Endereco;

public interface EnderecoService {

	void excluir(Long idEndereco) throws PetShopBusinessException;

	List<Endereco> findByIdPessoa(Long idPessoa) throws PetShopBusinessException;

	void gravar(Endereco endereco, Long idPessoa) throws PetShopBusinessException;

}
