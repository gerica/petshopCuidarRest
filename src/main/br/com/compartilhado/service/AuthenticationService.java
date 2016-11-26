package br.com.compartilhado.service;

import org.springframework.security.core.AuthenticationException;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface AuthenticationService {

	public String authentication(String username, String password) throws AuthenticationException, PetShopBusinessException;

	public String authenticationRequest(String token);

	Usuario loadUserByUsername(String username);

	long post(Usuario usuario);

	Usuario get(long id);
	
	Usuario get();

	Usuario patch(Usuario usuario);

	boolean delete(long id);

}
