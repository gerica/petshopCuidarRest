package br.com.compartilhado.controller;

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

import br.com.compartilhado.controller.model.AbstractResponse;
import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.UsuarioService;

@RestController
public class UsuarioRestController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

	@Autowired
	private UsuarioService usuarioService;

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

	@RequestMapping(method = RequestMethod.POST, value = UriConstPetShop.URI_REGISTRAR_USUARIO)
	@ResponseBody
	public ResponseEntity<? extends AbstractResponse> registrarUsuario(@RequestBody Usuario usuario) {
		logger.info("OperacaoRestController.salvarOperacao()");

		try {
			usuarioService.registar(usuario);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso");
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
