package br.com.modulo.cliente.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Telefone;

public interface TelefoneService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void excluir(Long idTelefone) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Telefone> findByIdPessoa(Long idPessoa) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void gravar(Telefone telefone, Long idPessoa) throws PetShopBusinessException;

}
