package br.com.compartilhado.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.entidade.permissao.UsuarioRole;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.repository.UsuarioRepository;
import br.com.compartilhado.service.RoleService;
import br.com.compartilhado.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleService roleService;

	@Override
	public Usuario alterar(Usuario userParam) throws PetShopBusinessException {
		validarEmail(userParam);
		validarSenha(userParam);
		Usuario usuario = verificarTrocaEmail(userParam);

		usuario.setUsername(userParam.getUsername());
		usuario.setPassword(getPasswordEnconding(userParam.getPassword()));
		usuarioRepository.save(usuario);
		return usuario;

	}

	@Override
	public Usuario findByEmail(String email) throws PetShopBusinessException {
		if (email == null || "".equals(email)) {
			throw new PetShopBusinessException("O emial não pode ser nulo, nem vazio.");
		}
		return usuarioRepository.findByEmail(email.toUpperCase());
	}

	public String getPasswordEnconding(String password) throws PetShopBusinessException {

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

	@Override
	public void registar(Usuario usuario) throws PetShopBusinessException {
		validarEmail(usuario);
		validarSenha(usuario);
		verificarDuplicidade(usuario);

		usuario.setAuthorities(getAuthorizeConvidado(usuario));
		usuario.setPassword(getPasswordEnconding(usuario.getPassword()));
		usuario.setEmail(usuario.getEmail().toUpperCase());
		usuarioRepository.save(usuario);

	}

	private Collection<UsuarioRole> getAuthorizeConvidado(Usuario usuario) {
		Collection<UsuarioRole> authorities = new ArrayList<UsuarioRole>();
		Role role;
		try {
			role = roleService.findByNome(RoleEnum.ROLE_CONVIDADO.name());
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

	private void validarEmail(Usuario usuario) throws PetShopBusinessException {
		String email = usuario.getEmail();

		if ((email == null) || (email.trim().length() == 0)) {
			throw new PetShopBusinessException("O email é obrigatório para registrar um usuário.");
		}

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new PetShopBusinessException(
					"O email informado não está no formato correto. Utilize um email correto.");
		}

	}

	private void validarSenha(Usuario usuario) throws PetShopBusinessException {
		if (usuario.getPassword() == null || usuario.getPassword().length() <= 4) {
			throw new PetShopBusinessException("Informe uma senha com no mínimo 5 caracteres.");
		}

	}

	private Usuario verificarDuplicidade(Usuario usuario) throws PetShopBusinessException {
		Usuario userDb = findByEmail(usuario.getEmail());
		if (userDb != null) {
			throw new PetShopBusinessException(
					"O email informado já existe cadastrado. Por favor informe outro email.");
		}
		return userDb;

	}

	private Usuario verificarTrocaEmail(Usuario userParam) throws PetShopBusinessException {
		Usuario usuario = findByEmail(userParam.getEmail());
		if (usuario == null) {
			throw new PetShopBusinessException(
					"Não foi possível recuperar o usuário com esse email. Contacte o administrador.");
		}
		if (!usuario.getEmail().equals(userParam.getEmail())) {
			throw new PetShopBusinessException("Não é possível alterar o email.");
		}
		return usuario;

	}

}
