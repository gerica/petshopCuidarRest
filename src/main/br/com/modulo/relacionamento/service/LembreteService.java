package br.com.modulo.relacionamento.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.relacionamento.entidade.Lembrete;

public interface LembreteService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void excluir(Long idLembrete) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void fechar(Long idLembrete) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	List<Lembrete> findAtivo() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Long findCountAberto() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Lembrete gravar(Lembrete lembrete, Long idPessoa) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void realizar(Long idLembrete) throws PetShopBusinessException;

}
