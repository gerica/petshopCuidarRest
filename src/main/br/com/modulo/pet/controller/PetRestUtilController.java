package br.com.modulo.pet.controller;

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
import br.com.modulo.pet.entidade.Raca;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.pet.service.RacaService;
import br.com.modulo.pet.service.TipoPetService;

@RestController
@RequestMapping(value = PetRestUtilController.URI_UTIL)
public class PetRestUtilController {

	private static final Logger logger = LoggerFactory.getLogger(PetRestUtilController.class);

	public static final String URI_UTIL = "pet/utils/";
	private static final String URI_RECUPERAR_TODOS_TIPO_PET = "recuperarTodosTipoPet";
	private static final String URI_RECUPERAR_RACA_POR_TIPO = "recuperarRacaTipo/{idTipoPet}";

	@Autowired
	private TipoPetService tipoPetService;
	
	@Autowired
	private RacaService racaService;

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODOS_TIPO_PET)
	@ResponseBody
	public ResponseEntity<?> recuperarTodosTipoPet() {
		logger.info("PetRestUtilController.recuperarTodosTipoPet()");

		List<TipoPet> result = null;
		try {
			result = tipoPetService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_RACA_POR_TIPO)
	@ResponseBody
	public ResponseEntity<?> recuperarRacaTipo(@PathVariable(value = "idTipoPet") Long idTipoPet) {
		logger.info("PetRestUtilController.recuperarRacaTipo()");

		List<Raca> result = null;
		try {
			result = racaService.findRacaByTipo(idTipoPet);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
