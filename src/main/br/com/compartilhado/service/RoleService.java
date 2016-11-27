package br.com.compartilhado.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface RoleService {

	@PreAuthorize("hasAnyRole('" + RoleEnum.Constants.ROLE_ADMIN + "')")
	List<Role> findAll() throws PetShopBusinessException;

	Role findByNome(String nome) throws PetShopBusinessException;

}
