package br.com.compartilhado.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface UsuarioService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	Usuario alterar(Usuario usuario) throws PetShopBusinessException;

	Usuario findByEmail(String email) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Usuario> findUsuariosAtivo() throws PetShopBusinessException;

	String getPasswordEnconding(String password) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	Usuario registar(Usuario usuario) throws PetShopBusinessException;

}
