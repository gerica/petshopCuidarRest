package br.com.modulo.produto.entidade.enums;

public enum LinhaRacaoEnum {

	SUPER_PREMIUM("Super Premium"), //
	PREMIUM("Premium"), //
	PRESCRITA("Prescrita"), //
	STANDARD("Standard");

	private String descricao;

	private LinhaRacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
