package br.com.compartilhado.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.entidade.permissao.UsuarioRole;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.repository.UsuarioRepository;
import br.com.compartilhado.service.RoleService;
import br.com.compartilhado.service.UsuarioRoleService;
import br.com.compartilhado.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioRoleService usuarioRole;

	@Override
	public void alterar(Usuario userParam, List<Role> roles) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.alterar()");
		validarEmail(userParam);
		Usuario usuario = verificarTrocaEmail(userParam);
		Collection<UsuarioRole> usuarioRoles = usuario.getAuthorities();

		if (CollectionUtils.isEmpty(roles)) {
			usuario.setAuthorities(getAuthorizeConvidado(usuario));
		} else {
			usuario.setAuthorities(getAuthorize(usuario, roles));
		}
		usuarioRole.deleteAll(usuarioRoles);
		
		usuario.setUsername(userParam.getUsername());
		usuario.setEmail(userParam.getEmail());
		
		usuarioRepository.save(usuario);

	}

	@Override
	public void ativarUsuario(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.ativarUsuario()");

		Usuario userBD = findByEmail(usuario.getEmail());
		if (userBD == null) {
			throw new PetShopBusinessException("Usuário não cadastrado com esse email: " + usuario.getEmail());
		}
		userBD.setEnabled(true);
		usuarioRepository.save(userBD);

	}

	@Override
	public Usuario findByEmail(String email) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.findByEmail()");

		if (email == null || "".equals(email)) {
			throw new PetShopBusinessException("O emial não pode ser nulo, nem vazio.");
		}
		return usuarioRepository.findByEmail(email.toUpperCase());
	}

	@Override
	public List<Usuario> findUsuariosAtivo() throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.findUsuariosAtivo()");
		return usuarioRepository.findByEnabled(true);
	}

	@Override
	public List<Usuario> findUsuariosInativo() throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.findUsuariosInativo()");
		return usuarioRepository.findByEnabled(false);
	}

	@Override
	public Usuario getUsuarioByEmailPassword(String email, String password) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.getUsuarioByEmailPassword()");

		Usuario userBD = findByEmail(email);
		if (userBD == null) {
			throw new PetShopBusinessException("Usuário não cadastrado com esse email: " + email);
		}
		String passwordEncode = getPasswordEnconding(password);
		if (!passwordEncode.equals(userBD.getPassword())) {
			throw new PetShopBusinessException("Senha incorreta.");
		}
		return userBD;

	}

	@Override
	public void inativarUsuario(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.inativarUsuario()");

		Usuario userBD = findByEmail(usuario.getEmail());
		if (userBD == null) {
			throw new PetShopBusinessException("Usuário não cadastrado com esse email: " + usuario.getEmail());
		}
		userBD.setEnabled(false);
		usuarioRepository.save(userBD);
	}

	@Override
	public Usuario incluirUsuario(Usuario usuario, List<Role> roles) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.incluirUsuario()");

		validarEmail(usuario);
		verificarDuplicidade(usuario);
		usuario.setUsername(usuario.getUsername().toUpperCase());
		usuario.setPassword(getPasswordRandom());
		usuario.setTempPassword(usuario.getPassword());

		if (CollectionUtils.isEmpty(roles)) {
			usuario.setAuthorities(getAuthorizeConvidado(usuario));
		} else {
			usuario.setAuthorities(getAuthorize(usuario, roles));
		}
		usuario.setLastPasswordReset(new Date());
		usuario.setPassword(getPasswordEnconding(usuario.getPassword()));
		usuario.setEmail(usuario.getEmail().toUpperCase());
		usuarioRepository.save(usuario);
		return usuario;

	}

	@Override
	public void primeiroLogin(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.primeiroLogin()");

		String passwordEnconding = getPasswordEnconding(usuario.getTempPassword());
		Usuario userBd = usuarioRepository.findByEmailAndPassword(usuario.getEmail().toUpperCase(), passwordEnconding);
		if (userBd == null) {
			throw new PetShopBusinessException("A 'senha antiga' informada está incorreta.");
		}
		validarSenha(userBd);
		userBd.setAccountLocked(false);
		userBd.setPassword(getPasswordEnconding(usuario.getPassword()));
		usuarioRepository.save(userBd);

	}

	@Override
	public void resetPassword(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.resetPassword()");

		Usuario userBD = findByEmail(usuario.getEmail());
		if (userBD == null) {
			throw new PetShopBusinessException("Usuário não cadastrado com esse email: " + usuario.getEmail());
		}
		userBD.setAccountLocked(true);
		userBD.setPassword(getPasswordEnconding(getPasswordRandom()));
		usuarioRepository.save(userBD);

	}

	private Collection<UsuarioRole> getAuthorize(Usuario usuario, List<Role> roles) {
		logger.info("UsuarioServiceImpl.getAuthorize()");

		Collection<UsuarioRole> authorities = new ArrayList<UsuarioRole>();
		Role role;
		UsuarioRole userRole;
		try {
			outerloop:
			for (Role r : roles) {
				for (UsuarioRole usuarioRole : usuario.getAuthorities()) {
					if (usuarioRole.getRole().equals(r)) {
						authorities.add(usuarioRole);
						continue outerloop;
					}
				}
				role = roleService.findByNome(r.getNome());
				userRole = new UsuarioRole();
				userRole.setRole(role);
				userRole.setUsuario(usuario);
				authorities.add(userRole);
			}
		} catch (PetShopBusinessException e) {
			e.printStackTrace();
			return null;
		}
		return authorities;
	}

	private Collection<UsuarioRole> getAuthorizeConvidado(Usuario usuario) {
		logger.info("UsuarioServiceImpl.getAuthorizeConvidado()");

		Collection<UsuarioRole> authorities = new ArrayList<UsuarioRole>();
		Role role;
		try {
			role = roleService.findByNome(RoleEnum.Constants.ROLE_CONVIDADO);
			UsuarioRole userRole = new UsuarioRole();
			userRole.setRole(role);
			userRole.setUsuario(usuario);
			authorities.add(userRole);
			return authorities;
		} catch (PetShopBusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getPasswordEnconding(String password) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.getPasswordEnconding()");

		MessageDigest algorithm = null;
		byte messageDigest[] = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			messageDigest = algorithm.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new PetShopBusinessException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new PetShopBusinessException(e.getMessage());
		}

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

	private String getPasswordRandom() {
		logger.info("UsuarioServiceImpl.getPasswordRandom()");
		// return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		return "cuidar"; // será a senha padrão
	}

	private void validarEmail(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.validarEmail()");
		String email = usuario.getEmail();

		if ((email == null) || (email.trim().length() == 0)) {
			throw new PetShopBusinessException("O email é obrigatório para registrar um usuário.");
		}

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new PetShopBusinessException("O email informado não está no formato correto. Utilize um email correto.");
		}

	}

	private void validarSenha(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.validarSenha()");

		if (usuario.getPassword() == null || usuario.getPassword().length() <= 4) {
			throw new PetShopBusinessException("Informe uma senha com no mínimo 5 caracteres.");
		}

	}

	private Usuario verificarDuplicidade(Usuario usuario) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.verificarDuplicidade()");
		Usuario userDb = findByEmail(usuario.getEmail());
		if (userDb != null) {
			throw new PetShopBusinessException("O email informado já existe cadastrado. Por favor informe outro email.");
		}
		return userDb;

	}

	private Usuario verificarTrocaEmail(Usuario userParam) throws PetShopBusinessException {
		logger.info("UsuarioServiceImpl.verificarTrocaEmail()");
		Usuario usuario = null;
		try {
			usuario = findByEmail(userParam.getEmail());

		} catch (PetShopBusinessException e) {
			throw new PetShopBusinessException("O e-mail informado já existe cadastrado. Informe outro e-mail.");
		}
		return usuario;

	}

}
