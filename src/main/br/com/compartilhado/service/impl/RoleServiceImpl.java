package br.com.compartilhado.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.repository.RoleRepository;
import br.com.compartilhado.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() throws PetShopBusinessException {
		logger.info("RoleServiceImpl.findAll()");
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role findByNome(String nome) throws PetShopBusinessException {
		logger.info("RoleServiceImpl.findByNome() " + nome);
		return roleRepository.findByNome(nome);
	}

}
