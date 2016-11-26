package br.com.compartilhado.entidade.permissao;

public enum RoleEnum {

	ROLE_ADMIN(1, Constants.ROLE_ADMIN), //
	ROLE_CONVIDADO(2, Constants.ROLE_CONVIDADO), //
	ROLE_FINANCEIRO(2, Constants.ROLE_FINANCEIRO), //
	ROLE_ESTOQUE(2, Constants.ROLE_ESTOQUE), //
	ROLE_RELACIONAMENTO(2, Constants.ROLE_RELACIONAMENTO), //
	ROLE_VENDA(2, Constants.ROLE_VENDA),//
	;

	private RoleEnum(Integer id, String descricao) {
	}

	public static class Constants {
		public static final String ROLE_ADMIN = "ADMIN";
		public static final String ROLE_CONVIDADO = "CONVIDADO";
		public static final String ROLE_FINANCEIRO = "FINANCEIRO";
		public static final String ROLE_ESTOQUE = "ESTOQUE";
		public static final String ROLE_RELACIONAMENTO = "RELACIONAMENTO";
		public static final String ROLE_VENDA = "VENDA";
	}

}
