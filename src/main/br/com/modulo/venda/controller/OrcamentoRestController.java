package br.com.modulo.venda.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.controller.wrapper.OrcamentoWrapper;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.service.OrcamentoService;

@RestController
@RequestMapping(value = OrcamentoRestController.URI_ORCAMENTO)
public class OrcamentoRestController {

	private static final Logger logger = LoggerFactory.getLogger(OrcamentoRestController.class);

	public static final String URI_ORCAMENTO = "orcamento/";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_FECHAR = "fechar";
	private static final String URI_RECUPERAR_TODAS = "recuperarTodas";
	private static final String URI_RECUPERAR_POR_ORCAMENTO_ID = "recuperarPorOrcamentoId/{idOrcamento}";
	private static final String URI_RECUPERAR_QTD_ORCAMENTOS = "recuperarQuantidadeOrcamentos";

	@Autowired
	private OrcamentoService service;

	@RequestMapping(method = RequestMethod.POST, value = URI_FECHAR)
	@ResponseBody
	public ResponseEntity<?> fechar(@RequestBody Long idOrcamento) {
		logger.info("OrcamentoRestController.fechar()");

		try {
			service.fechar(idOrcamento);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("OrcamentoRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody OrcamentoWrapper wrapper) {
		logger.info("OrcamentoRestController.gravar()");
		Orcamento result = null;
		try {
			result = service.gravar(wrapper.getId(), wrapper.getPessoa(), wrapper.getItens());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse("Operação realizada com sucesso.", result),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_POR_ORCAMENTO_ID)
	@ResponseBody
	public ResponseEntity<?> recuperarPorOrcamentoId(@PathVariable(value = "idOrcamento") Long idOrcamento) {
		logger.info("OrcamentoRestController.recuperarPorOrcamentoId()");

		OrcamentoWrapper result = null;
		try {
			result = service.findById(idOrcamento);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com	 sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_QTD_ORCAMENTOS)
	@ResponseBody
	public ResponseEntity<?> recuperarQuantidade() {
		logger.info("OrcamentoRestController.recuperarQuantidade()");

		Long result = null;
		try {
			result = service.findCountAberto();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODAS)
	@ResponseBody
	public ResponseEntity<?> recuperarTodas() {
		logger.info("OrcamentoRestController.recuperarTodas()");

		List<OrcamentoWrapper> result = null;
		try {
			result = service.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}
}
