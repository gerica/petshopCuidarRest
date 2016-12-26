package br.com.modulo.produto.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.MedicamentoLote;

public interface MedicamentoLoteService {

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void excluir(Long idMedicamentoLote) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	List<MedicamentoLote> findAll() throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	List<MedicamentoLote> findByIdMedicamento(Long idMedicamento) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" //
			+ RoleEnum.Constants.ROLE_ESTOQUE + //
			"','" //
			+ RoleEnum.Constants.ROLE_ADMIN + //
			"'})")
	void gravar(MedicamentoLote lote, Long idMedicamento) throws PetShopBusinessException;
}
