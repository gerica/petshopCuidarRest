package br.com.compartilhado.entidade.permissao;

public enum RoleEnum {

	ROLE_ADMIN(1, Constants.ROLE_ADMIN), //
	ROLE_CONVIDADO(2, Constants.ROLE_CONVIDADO), //
	ROLE_FINANCEIRO(3, Constants.ROLE_FINANCEIRO), //
	ROLE_ESTOQUE(4, Constants.ROLE_ESTOQUE), //
	ROLE_RELACIONAMENTO(5, Constants.ROLE_RELACIONAMENTO), //
	ROLE_VENDA(6, Constants.ROLE_VENDA),//
	;

	public static class Constants {
		public static final String ROLE_ADMIN = "ADMIN";
		public static final String ROLE_CONVIDADO = "CONVIDADO";
		public static final String ROLE_FINANCEIRO = "FINANCEIRO";
		public static final String ROLE_ESTOQUE = "ESTOQUE";
		public static final String ROLE_RELACIONAMENTO = "RELACIONAMENTO";
		public static final String ROLE_VENDA = "VENDA";
	}

	private RoleEnum(Integer id, String descricao) {
	}

}
