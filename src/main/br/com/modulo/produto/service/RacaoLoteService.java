package br.com.modulo.produto.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.RacaoLote;

public interface RacaoLoteService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void excluir(Long idRacaoLote) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	List<RacaoLote> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	List<RacaoLote> findByIdRacao(Long idRacao) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void gravar(RacaoLote lote, Long idRacao) throws PetShopBusinessException;

}
