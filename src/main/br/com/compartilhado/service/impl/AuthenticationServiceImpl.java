package br.com.compartilhado.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.compartilhado.service.UsuarioService;
import br.com.util.AppConstant;
import br.com.util.TokenUtils;

@Service(value = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired(required = true)
	private HttpServletRequest request;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public String authentication(String email, String password) throws AuthenticationException, PetShopBusinessException, LockedException {

		Usuario userBD = usuarioService.getUsuarioByEmailPassword(email, password);

		//
		// Perform the authentication
		try {
			Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBD.getUsername(), userBD.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (LockedException e) {
			throw new LockedException("Usuario: " + userBD.getUsername() + ", está com a conta bloqueada");
		}

		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userBD.getUsername());
		String token = this.tokenUtils.generateToken(userDetails);

		// Return the token
		return token;
	}

	public String authenticationRequest(String token) {
		String username = this.tokenUtils.getUsernameFromToken(token);
		Usuario user = (Usuario) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			return this.tokenUtils.refreshToken(token);

		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public boolean delete(long id) {
		sessionFactory.getCurrentSession().delete(get(id));
		return true;
	}

	@Transactional
	@Override
	public Usuario get() {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(AppConstant.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// sessionFactory.getCurrentSession().get(Usuario.class, username);

		return loadUserByUsername(username);
	}

	@Transactional
	@Override
	public Usuario get(long id) {
		return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
	}

	@Override
	@Transactional
	public Usuario loadUserByUsername(String username) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("username", username)).uniqueResult();
	}

	@Transactional
	@Override
	public Usuario patch(Usuario appUser) {
		sessionFactory.getCurrentSession().update(appUser);
		return get(appUser.getId());
	}

	@Transactional
	@Override
	public long post(Usuario appUser) {
		return (long) sessionFactory.getCurrentSession().save(appUser);
	}

}
