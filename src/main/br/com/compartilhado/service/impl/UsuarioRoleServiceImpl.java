package br.com.compartilhado.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.compartilhado.entidade.permissao.UsuarioRole;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.repository.UsuarioRoleRepository;
import br.com.compartilhado.service.UsuarioRoleService;

@Service
public class UsuarioRoleServiceImpl implements UsuarioRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioRoleServiceImpl.class);

	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;

	@Override
	public void deleteAll(Collection<UsuarioRole> usuarioRoles) throws PetShopBusinessException {
		logger.info("UsuarioRoleServiceImpl.deleteAll()");
		
		if (CollectionUtils.isEmpty(usuarioRoles)) {
			// for (UsuarioRole usuarioRole : usuarioRoles) {
			usuarioRoleRepository.delete(usuarioRoles);
			// }

		}

	}

}
