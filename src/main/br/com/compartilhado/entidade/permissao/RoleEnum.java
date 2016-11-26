package br.com.compartilhado.entidade.permissao;

public enum RoleEnum {

	ROLE_ADMIN(1, Constants.ROLE_ADMIN), //
	ROLE_CONVIDADOO(2, Constants.ROLE_CONVIDADO), //
	ROLE_FINANCEIRO(2, Constants.ROLE_FINANCEIRO), //
	ROLE_ESTOQUE(2, Constants.ROLE_ESTOQUE), //
	ROLE_RELACIONAMENTO(2, Constants.ROLE_RELACIONAMENTO), //
	ROLE_VENDA(2, Constants.ROLE_VENDA),//
	;

	private RoleEnum(Integer id, String descricao) {
	}

	public static class Constants {
		public static final String ROLE_ADMIN = "ROLE_ADMIN";
		public static final String ROLE_CONVIDADO = "ROLE_CONVIDADO";
		public static final String ROLE_FINANCEIRO = "ROLE_FINANCEIRO";
		public static final String ROLE_ESTOQUE = "ROLE_ESTOQUE";
		public static final String ROLE_RELACIONAMENTO = "ROLE_RELACIONAMENTO";
		public static final String ROLE_VENDA = "ROLE_VENDA";
	}

}
