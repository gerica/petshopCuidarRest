package br.com.compartilhado.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.controller.wrapper.UsuarioRoleWrapper;
import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.UsuarioService;

@RestController
@RequestMapping(value = UriConstPetShop.URI_USUARIO)
public class UsuarioRestController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("UsuarioRestController.getInfo()");
		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");
		msg.add("Incluir usuário, método POST, URL: " + UriConstPetShop.URL_INCLUIR_USUARIO);
		msg.add("Alterar usuário, método POST, URL: " + UriConstPetShop.URI_ALTERAR_USUARIO);
		msg.add("Recupar todos os usuários ativos, método GET, URL: " + UriConstPetShop.URI_RECUPERAR_USUARIOS_ATIVO);

		SuccessResponse success = new SuccessResponse("Rest de ROLE", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = UriConstPetShop.URI_ALTERAR_USUARIO)
	@ResponseBody
	public ResponseEntity<?> alterarUsuario(@RequestBody Usuario usuario) {
		logger.info("UsuarioRestController.alterarUsuario()");

		try {
			usuario = usuarioService.alterar(usuario);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", usuario);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = UriConstPetShop.URL_INCLUIR_USUARIO)
	@ResponseBody
	public ResponseEntity<?> incluirUsuario(@RequestBody UsuarioRoleWrapper usuarioRoleWrapper) {
		logger.info("UsuarioRestController.incluirUsuario()");
		Usuario result = null;
		try {
			result = usuarioService.incluirUsuario(usuarioRoleWrapper.getUsuario(), usuarioRoleWrapper.getRoles());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		String message = "Operação realizada com sucesso. Sua senha temporária é: " + result.getTempPassword()
				+ ". Utilize essa senha para realizar o login pela primeira vez.";
		SuccessResponse success = new SuccessResponse(message, result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_RECUPERAR_USUARIOS_ATIVO)
	@ResponseBody
	public ResponseEntity<?> recuperarUsuariosAtivo() {
		logger.info("UsuarioRestController.recuperarUsuariosAtivo()");

		List<Usuario> result = null;
		try {
			result = usuarioService.findUsuariosAtivo();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
