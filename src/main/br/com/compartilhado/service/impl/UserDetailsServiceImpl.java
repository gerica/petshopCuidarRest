package br.com.compartilhado.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.service.AuthenticationService;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	// @Autowired
	// private UsuarioService usuarioService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.authenticationService.loadUserByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Nenhum usuário com esse nome '%s'.", username));
		} else {
			// usuario.setPassword(usuario.getSenha());
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(usuario.getAuthoritiesBd()));
			return usuario;

		}
	}

}
