package br.com.modulo.pet.controller.wrapper;

import br.com.modulo.pet.entidade.Pet;

public class PetWrapper {

	private Long idPessoa;
	private Pet pet;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Pet getPet() {
		return pet;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
