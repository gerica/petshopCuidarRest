package br.com.modulo.relacionamento.controller;

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
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.relacionamento.controller.wrapper.LembreteWrapper;
import br.com.modulo.relacionamento.entidade.Lembrete;
import br.com.modulo.relacionamento.service.LembreteService;

@RestController
@RequestMapping(value = LembreteRestController.URI_LEMBRETE)
public class LembreteRestController {

	private static final Logger logger = LoggerFactory.getLogger(LembreteRestController.class);

	public static final String URI_LEMBRETE = "lembrete/";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_EXCLUIR = "excluir";
	private static final String URI_RECUPERAR_ABERTO = "recuperarAberto";

	@Autowired
	private LembreteService service;

	@RequestMapping(method = RequestMethod.POST, value = URI_EXCLUIR)
	@ResponseBody
	public ResponseEntity<?> excluir(@RequestBody Long idLembrete) {
		logger.info("LembreteRestController.excluir()");

		try {
			service.excluir(idLembrete);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("LembreteRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody LembreteWrapper wrapper) {
		logger.info("LembreteRestController.gravar()");

		try {
			service.gravar(wrapper.getLembrete(), wrapper.getIdPessoa());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_ABERTO)
	@ResponseBody
	public ResponseEntity<?> recuperarAberto() {
		logger.info("LembreteRestController.recuperarAberto()");

		List<Lembrete> result = null;
		try {
			result = service.findAtivo();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
