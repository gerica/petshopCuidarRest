package br.com.modulo.cliente.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compartilhado.controller.UriConstPetShop;
import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Estado;
import br.com.modulo.cliente.service.CidadeService;
import br.com.modulo.cliente.service.EstadoService;

@RestController
@RequestMapping(value = UriConstPetShop.URI_PESSOA_UTIL)
public class ClienteRestUtilController {
	private static final Logger logger = LoggerFactory.getLogger(ClienteRestUtilController.class);

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_ESTADO)
	@ResponseBody
	public ResponseEntity<?> recuperarEstados() {
		logger.info("ClienteRestUtilController.recuperarEstados()");

		Iterable<Estado> result = null;
		try {
			result = estadoService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_CIDADE_POR_ESTADO)
	@ResponseBody
	public ResponseEntity<?> recuperarCidadePorEstado(@PathVariable(value = "idEstado") Long idEstado) {
		logger.info("ClienteRestUtilController.recuperarUltimaCotacao()");

		Cidade cidade = null;
		try {
			cidade = cidadeService.findByEstado(idEstado);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", cidade);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}
}
