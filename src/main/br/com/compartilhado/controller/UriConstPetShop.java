package br.com.compartilhado.controller;

public abstract class UriConstPetShop {
	// Functionalidades para os AUTENTICAR
	public static final String URI_AUTH = "auth";
	public static final String URI_REFRESH = "refresh";

	// Functionalidades para USUÁRIOS
	public static final String URI_REGISTRAR_USUARIO = "/registrarUsuario";
	public static final String URI_ALTERAR_USUARIO = "/alterarUsuario";
	
	// Funcionalidades de ROLE
	public static final String URI_ROLE = "role";
	public static final String URI_ROLE_RECUPERAR_TODOS = "recuperarTodos";

}