package br.com.modulo.pet.entidade;

public enum PorteRacaEnum {

	MINI("Mini"), //
	PEQUENO("Pequeno"), //
	MEDIO("Médio"), //
	GRANDE("Grande"), //
	GIGANTE("Gigante");

	private String descricao;

	private PorteRacaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
