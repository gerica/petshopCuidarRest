package br.com.compartilhado.service;

import java.util.Collection;

import br.com.compartilhado.entidade.permissao.UsuarioRole;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface UsuarioRoleService {

	void deleteAll(Collection<UsuarioRole> usuarioRoles) throws PetShopBusinessException;

}
