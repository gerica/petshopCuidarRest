package br.com.modulo.pet.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.pet.entidade.Pet;

public interface PetService {

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void excluir(Long idPet) throws PetShopBusinessException;;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Pet> findAll() throws PetShopBusinessException;;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Pet> findPetByDsNome(String valor) throws PetShopBusinessException;;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	List<Pet> findPetByIdPessoa(Long idPessoa) throws PetShopBusinessException;

	@PreAuthorize("@securityService.hasAnyRole({'" + RoleEnum.Constants.ROLE_VENDA + "','"
			+ RoleEnum.Constants.ROLE_RELACIONAMENTO + "','" + RoleEnum.Constants.ROLE_ADMIN + "'})")
	void gravar(Pet pet, Long idPessoa) throws PetShopBusinessException;

}
