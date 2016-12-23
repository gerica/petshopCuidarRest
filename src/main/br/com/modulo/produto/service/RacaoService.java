package br.com.modulo.produto.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Racao;

public interface RacaoService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void excluir(Long idRacao) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	List<Racao> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void gravar(Racao racao) throws PetShopBusinessException;

}
