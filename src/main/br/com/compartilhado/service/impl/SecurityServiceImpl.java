package br.com.compartilhado.service.impl;

import java.util.Collection;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.compartilhado.entidade.permissao.UsuarioRole;
import br.com.compartilhado.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public Boolean hasAnyRole(String[] role) {
		Boolean retorno = false;
		Collection<UsuarioRole> autorities = (Collection<UsuarioRole>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		for (UsuarioRole usuarioRole : autorities) {
			for (int i = 0; i < role.length; i++) {
				if (role[i].equals(usuarioRole.getRole().getNome())) {
					retorno = true;
					break;
				}
			}

		}

		return retorno;
	}

}
