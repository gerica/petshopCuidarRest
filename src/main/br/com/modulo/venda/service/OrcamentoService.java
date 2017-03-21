package br.com.modulo.venda.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.controller.wrapper.OrcamentoWrapper;
import br.com.modulo.venda.entidade.Orcamento;

public interface OrcamentoService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void excluir(Long idOrcamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void fechar(Long idOrcamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	List<OrcamentoWrapper> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	OrcamentoWrapper findById(Long idOrcamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Long findCountAberto() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Orcamento findOrcamentoById(Long idOrcamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	Orcamento gravar(Long idOrcamento, Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','" //
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	void realizar(Long idOrcamento) throws PetShopBusinessException;

	public void validarOrcamento(Orcamento orcamento) throws PetShopBusinessException;

}
