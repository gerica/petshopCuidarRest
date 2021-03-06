package br.com.compartilhado.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compartilhado.controller.model.AuthenticationResponse;
import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.controller.wrapper.UsuarioLockWrapper;
import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.compartilhado.service.UsuarioService;
import br.com.util.AppConstant;

@RestController
@PermitAll
@RequestMapping(value = UriConstPetShop.URI_AUTH)
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = UriConstPetShop.URI_REFRESH, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(AppConstant.tokenHeader);

		String refreshedToken = authenticationService.authenticationRequest(token);
		if (refreshedToken != null) {

			return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> authenticationRequest(@RequestBody Usuario usuario) throws AuthenticationException {

		String token = null;
		Usuario usuarioAuth = null;

		try {
			token = authenticationService.authentication(usuario.getEmail(), usuario.getPassword());
			usuarioAuth = usuarioService.findByEmail(usuario.getEmail());

		} catch (LockedException e) {

			UsuarioLockWrapper lockWrapper = new UsuarioLockWrapper(true, usuario.getEmail());
			ErrorResponse error = new ErrorResponse(e.getMessage(), lockWrapper);
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		// Return the token
		return ResponseEntity.ok(new AuthenticationResponse(token, usuarioAuth));
	}

	/**
	 * Apresenta as informações desse rest
	 * @return informações
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("AuthenticationController.getInfo()");
		List<String> msg = new ArrayList<String>();
		// StringBuilder msg = new StringBuilder("Serviços: autenticar, chamar
		// essa url com post.");
		msg.add("Serviços");
		msg.add("Autenticar, chamar essa url com post. Informando email e password.");
		msg.add("Refreh, após o usuário logado, chamar a url '/refresh' ");

		SuccessResponse success = new SuccessResponse("Rest de autencicação", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
