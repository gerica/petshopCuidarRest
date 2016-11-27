package br.com.compartilhado.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compartilhado.controller.model.AbstractResponse;
import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.entidade.permissao.Role;
import br.com.compartilhado.entidade.permissao.RoleEnum;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.RoleService;

@RestController
@RequestMapping(value = UriConstPetShop.URI_ROLE)
public class RoleRestController {
	private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	// @RolesAllowed({ RoleEnum.Constants.ROLE_ADMIN })
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("RoleRestController.getInfo()");
		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");
		msg.add("Recuperar todas as roles. Chame a URL: " + UriConstPetShop.URI_ROLE_RECUPERAR_TODOS);

		SuccessResponse success = new SuccessResponse("Rest de ROLE", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_ROLE_RECUPERAR_TODOS)
	@PreAuthorize("hasAnyRole('" + RoleEnum.Constants.ROLE_ADMIN + "','" + RoleEnum.Constants.ROLE_CONVIDADO + "')")
	@ResponseBody
	public ResponseEntity<? extends AbstractResponse> recuperarTodos() {
		logger.info("RoleRestController.recuperarTodos()");
		List<Role> result;

		try {
			result = roleService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

}
