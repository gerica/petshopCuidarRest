package br.com.modulo.cliente.controller;

import java.util.List;

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

import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Estado;
import br.com.modulo.cliente.entidade.TipoDocumento;
import br.com.modulo.cliente.service.CidadeService;
import br.com.modulo.cliente.service.EstadoService;
import br.com.modulo.cliente.service.TipoDocumentoService;

@RestController
@RequestMapping(value = ClienteRestUtilController.URI_UTIL)
public class ClienteRestUtilController {
	private static final Logger logger = LoggerFactory.getLogger(ClienteRestUtilController.class);

	public static final String URI_UTIL = "utils/";
	// Funcionalidades de ESTADO
	private static final String URI_ESTADO = "estado";
	private static final String URI_CIDADE_POR_ESTADO = "cidade/{idEstado}";
	private static final String URI_RECUPERAR_TODOS_TIPO_DOCUMENTO = "recuperarTodosTipoDocumento";

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@RequestMapping(method = RequestMethod.GET, value = URI_CIDADE_POR_ESTADO)
	@ResponseBody
	public ResponseEntity<?> recuperarCidadePorEstado(@PathVariable(value = "idEstado") Long idEstado) {
		logger.info("ClienteRestUtilController.recuperarUltimaCotacao()");

		List<Cidade> result = null;
		try {
			result = cidadeService.findByEstado(idEstado);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_ESTADO)
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
	

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODOS_TIPO_DOCUMENTO)
	@ResponseBody
	public ResponseEntity<?> recuperarTodosTipoDocumento() {
		logger.info("ClienteRestUtilController.recuperarTodosTipoDocumento()");

		Iterable<TipoDocumento> result = null;
		try {
			result = tipoDocumentoService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
