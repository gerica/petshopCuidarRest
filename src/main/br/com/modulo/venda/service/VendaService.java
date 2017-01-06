package br.com.modulo.venda.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.entidade.Venda;

public interface VendaService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	List<Venda> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Venda findById(Long idVenda) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Venda gravar(Long idOrcamento) throws PetShopBusinessException;

}
