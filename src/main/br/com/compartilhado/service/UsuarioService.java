package br.com.compartilhado.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface UsuarioService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	Usuario alterar(Usuario usuario) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void ativarUsuario(Usuario usuario)throws PetShopBusinessException;

	Usuario findByEmail(String email) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Usuario> findUsuariosAtivo() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Usuario> findUsuariosInativo()throws PetShopBusinessException;

	Usuario getUsuarioByEmailPassword(String email, String password) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void inativarUsuario(Usuario usuario) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	Usuario incluirUsuario(Usuario usuario, List<Role> roles) throws PetShopBusinessException;

	void primeiroLogin(Usuario usuario) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void resetPassword(Usuario usuario) throws PetShopBusinessException;

}
