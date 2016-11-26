package br.com.compartilhado.service;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface UsuarioService {

	void registar(Usuario usuario) throws PetShopBusinessException;

	Usuario alterar(Usuario usuario) throws PetShopBusinessException;

	String getPasswordEnconding(String password) throws PetShopBusinessException;

	Usuario findByEmail(String email) throws PetShopBusinessException;

}
