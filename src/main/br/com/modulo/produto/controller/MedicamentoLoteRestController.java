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
import br.com.modulo.produto.controller.wrapper.MedicamentoWrapper;
import br.com.modulo.produto.entidade.MedicamentoLote;
import br.com.modulo.produto.service.MedicamentoLoteService;

@RestController
@RequestMapping(value = MedicamentoLoteRestController.URI_MEDICAMENTO_LOTE)
public class MedicamentoLoteRestController {

	private static final Logger logger = LoggerFactory.getLogger(MedicamentoLoteRestController.class);

	public static final String URI_MEDICAMENTO_LOTE = "medicamento/lote";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_EXCLUIR = "excluir";
	private static final String URI_RECUPERAR_TODAS = "recuperarTodas";
	private static final String URI_RECUPERAR_POR_MEDICAMENTO_ID = "recuperarPorMedicamentoId/{idMedicamento}";

	@Autowired
	private MedicamentoLoteService MedicamentoLoteService;

	@RequestMapping(method = RequestMethod.POST, value = URI_EXCLUIR)
	@ResponseBody
	public ResponseEntity<?> excluir(@RequestBody Long idMedicamentoLote) {
		logger.info("MedicamentoLoteRestController.excluir()");

		try {
			MedicamentoLoteService.excluir(idMedicamentoLote);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("MedicamentoLoteRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody MedicamentoWrapper MedicamentoWrapper) {
		logger.info("MedicamentoLoteRestController.gravar()");

		try {
			MedicamentoLoteService.gravar(MedicamentoWrapper.getLote(), MedicamentoWrapper.getIdMedicamento());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_POR_MEDICAMENTO_ID)
	@ResponseBody
	public ResponseEntity<?> recuperarPorMedicamentoId(@PathVariable(value = "idMedicamento") Long idMedicamento) {
		logger.info("MedicamentoLoteRestController.recuperarPorMedicamentoId()");

		List<MedicamentoLote> result = null;
		try {
			result = MedicamentoLoteService.findByIdMedicamento(idMedicamento);
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
		logger.info("MedicamentoLoteRestController.recuperarTodas()");

		List<MedicamentoLote> result = null;
		try {
			result = MedicamentoLoteService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
