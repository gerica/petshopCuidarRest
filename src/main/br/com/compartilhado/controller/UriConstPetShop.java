package br.com.compartilhado.controller;

public abstract class UriConstPetShop {
	// Funcionalidades GENERICAS
	public static final String URI_INCLUIR = "incluir";
	public static final String URI_ALTERAR = "alterar";

	// Functionalidades para os AUTENTICAR
	public static final String URI_AUTH = "auth/";
	public static final String URI_REFRESH = "refresh";

	// Functionalidades para USUÁRIOS
	public static final String URI_USUARIO = "usuario/";
	public static final String URI_RECUPERAR_USUARIOS_ATIVO = "recuperarUsuariosAtivo";

	// Funcionalidades de ROLE
	public static final String URI_ROLE = "role/";
	public static final String URI_ROLE_RECUPERAR_TODOS = "recuperarTodos";

	// Funcionalidades de PESSOA
	public static final String URI_PESSOA = "pessoa/";
	public static final String URI_PESSOA_UTIL = URI_PESSOA + "util/";
	public static final String URI_PESSOA_RECUPERAR_TODOS = "recuperarTodos";

	// Funcionalidades de ESTADO
	public static final String URI_ESTADO = "estado";
	public static final String URI_CIDADE_POR_ESTADO = "cidade/{idEstado}";

}
