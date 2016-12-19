package br.com.compartilhado.controller;

public abstract class UriConstPetShop {
	// Funcionalidades GENERICAS
	public static final String URI_INCLUIR = "incluir";
	public static final String URI_ALTERAR = "alterar";
	public static final String URI_GRAVAR = "gravar";

	// Functionalidades para os AUTENTICAR
	public static final String URI_AUTH = "auth";
	public static final String URI_REFRESH = "refresh";

	// Functionalidades para USUÁRIOS
	public static final String URI_USUARIO = "usuario/";
	public static final String URI_RECUPERAR_USUARIOS_ATIVO = "recuperarUsuariosAtivo";
	public static final String URI_RECUPERAR_USUARIOS_INATIVO = "recuperarUsuariosInativo";
	public static final String URI_PRIMEIRO_LOGIN = "primeiroLogin";
	public static final String URI_INATIVAR_USUARIO = "inativarUsuario";
	public static final String URI_ATIVAR_USUARIO = "ativarUsuario";
	public static final String URI_RESET_PASSWORD = "resetPassword";

	// Funcionalidades de ROLE
	public static final String URI_ROLE = "role";
	public static final String URI_ROLE_RECUPERAR_TODOS = "recuperarTodos";

}
