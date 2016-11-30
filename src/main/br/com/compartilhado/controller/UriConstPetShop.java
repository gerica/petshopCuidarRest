package br.com.compartilhado.controller;

public abstract class UriConstPetShop {
	// Functionalidades para os AUTENTICAR
	public static final String URI_AUTH = "auth";
	public static final String URI_REFRESH = "refresh";

	// Functionalidades para USUÁRIOS
	public static final String URI_USUARIO = "usuario";
	public static final String URL_INCLUIR_USUARIO = "incluirUsuario";
	public static final String URI_ALTERAR_USUARIO = "alterarUsuario";
	public static final String URI_RECUPERAR_USUARIOS_ATIVO = "recuperarUsuariosAtivo";
	public static final String URI_RECUPERAR_USUARIOS_INATIVO = "recuperarUsuariosInativo";
	public static final String URI_PRIMEIRO_LOGIN = "primeiroLogin";
	public static final String URI_INATIVAR_USUARIO = "inativarUsuario";

	// Funcionalidades de ROLE
	public static final String URI_ROLE = "role";
	public static final String URI_ROLE_RECUPERAR_TODOS = "recuperarTodos";

}
