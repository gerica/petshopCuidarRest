package br.com.modulo.pet.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.Raca;

public interface RacaService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Raca> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Raca> findRacaByTipo(Long idTipoPet) throws PetShopBusinessException;

}
