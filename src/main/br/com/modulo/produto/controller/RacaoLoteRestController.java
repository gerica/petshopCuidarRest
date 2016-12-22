package br.com.modulo.produto.controller;

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
import br.com.modulo.produto.controller.wrapper.RacaoWrapper;
import br.com.modulo.produto.entidade.RacaoLote;
import br.com.modulo.produto.service.RacaoLoteService;

@RestController
@RequestMapping(value = RacaoLoteRestController.URI_RACAO_LOTE)
public class RacaoLoteRestController {

	private static final Logger logger = LoggerFactory.getLogger(RacaoLoteRestController.class);

	public static final String URI_RACAO_LOTE = "racao/lote";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_EXCLUIR = "excluir";
	private static final String URI_RECUPERAR_TODAS = "recuperarTodas";
	private static final String URI_RECUPERAR_POR_RACAO_ID = "recuperarPorRacaoId/{idRacao}";

	@Autowired
	private RacaoLoteService racaoLoteService;

	@RequestMapping(method = RequestMethod.POST, value = URI_EXCLUIR)
	@ResponseBody
	public ResponseEntity<?> excluir(@RequestBody Long idRacaoLote) {
		logger.info("RacaoLoteRestController.excluir()");

		try {
			racaoLoteService.excluir(idRacaoLote);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("RacaoLoteRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody RacaoWrapper racaoWrapper) {
		logger.info("RacaoLoteRestController.gravar()");

		try {
			racaoLoteService.gravar(racaoWrapper.getRacaoLote(), racaoWrapper.getIdRacao());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_POR_RACAO_ID)
	@ResponseBody
	public ResponseEntity<?> recuperarPorRacaoId(@PathVariable(value = "idRacao") Long idRacao) {
		logger.info("RacaoLoteRestController.recuperarPorRacaoId()");

		List<RacaoLote> result = null;
		try {
			result = racaoLoteService.findByIdRacao(idRacao);
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
		logger.info("RacaoLoteRestController.recuperarTodas()");

		List<RacaoLote> result = null;
		try {
			result = racaoLoteService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
