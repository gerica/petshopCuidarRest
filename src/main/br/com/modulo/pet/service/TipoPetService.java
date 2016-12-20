package br.com.modulo.pet.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;

public interface TipoPetService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<TipoPet> findAll() throws PetShopBusinessException;

}
