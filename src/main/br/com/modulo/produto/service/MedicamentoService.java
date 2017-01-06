package br.com.modulo.produto.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Medicamento;

public interface MedicamentoService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void excluir(Long idMedicamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"','"//
			+ RoleEnum.Constants.ROLE_VENDA + //
			"'})")
	List<Medicamento> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void gravar(Medicamento medicamento, List<TipoPet> tiposPet) throws PetShopBusinessException;

}
