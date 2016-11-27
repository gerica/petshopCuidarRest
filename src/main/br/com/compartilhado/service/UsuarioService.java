package br.com.compartilhado.service;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface UsuarioService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	Usuario alterar(Usuario usuario) throws PetShopBusinessException;

	Usuario findByEmail(String email) throws PetShopBusinessException;

	String getPasswordEnconding(String password) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void registar(Usuario usuario) throws PetShopBusinessException;

}
