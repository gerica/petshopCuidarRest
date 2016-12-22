package br.com.modulo.pet.entidade;

public enum FaixaIdadeEnum {

	FILHOTE("Filhote"), //
	ADULTO("Adulto"), //
	SENIOR("Sênior");

	private String descricao;

	private FaixaIdadeEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
