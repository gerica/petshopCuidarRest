package br.com.compartilhado.service;

import org.springframework.security.core.AuthenticationException;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;

public interface AuthenticationService {

	public String authentication(String username, String password) throws AuthenticationException, PetShopBusinessException;

	public String authenticationRequest(String token);

	boolean delete(long id);

	Usuario get();

	Usuario get(long id);
	
	Usuario loadUserByUsername(String username);

	Usuario patch(Usuario usuario);

	long post(Usuario usuario);

}
