package br.com.modulo.produto.controller;

import java.util.ArrayList;
import java.util.EnumSet;
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
import br.com.modulo.pet.entidade.FaixaIdadeEnum;
import br.com.modulo.pet.entidade.PorteRacaEnum;
import br.com.modulo.produto.entidade.Racao;
import br.com.modulo.produto.entidade.enums.LinhaRacaoEnum;
import br.com.modulo.produto.service.RacaoService;

@RestController
@RequestMapping(value = RacaoRestController.URI_RACAO)
public class RacaoRestController {

	private static final Logger logger = LoggerFactory.getLogger(RacaoRestController.class);

	public static final String URI_RACAO = "racao/";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_EXCLUIR = "excluir";
	private static final String URI_RECUPERAR_TODAS = "recuperarTodas";
	private static final String URI_RECUPERAR_TODAS_LINHA = "recuperarTodasLinha";
	private static final String URI_RECUPERAR_TODAS_PORTE = "recuperarTodasPorte";
	private static final String URI_RECUPERAR_TODAS_FAIXA_IDADE = "recuperarTodasFaixaIdade";

	@Autowired
	private RacaoService racaoService;

	@RequestMapping(method = RequestMethod.POST, value = URI_EXCLUIR)
	@ResponseBody
	public ResponseEntity<?> excluir(@RequestBody Long idRacao) {
		logger.info("RacaoRestController.excluir()");

		try {
			racaoService.excluir(idRacao);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("RacaoRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody Racao racao) {
		logger.info("RacaoRestController.gravar()");

		try {
			racaoService.gravar(racao);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODAS)
	@ResponseBody
	public ResponseEntity<?> recuperarTodas() {
		logger.info("RacaoRestController.recuperarTodas()");

		List<Racao> result = null;
		try {
			result = racaoService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODAS_FAIXA_IDADE)
	@ResponseBody
	public ResponseEntity<?> recuperarTodasFaixaIdade() {
		logger.info("RacaoRestController.recuperarTodasFaixaIdade()");

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", FaixaIdadeEnum.getListaValores());
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODAS_LINHA)
	@ResponseBody
	public ResponseEntity<?> recuperarTodasLinha() {
		logger.info("RacaoRestController.recuperarTodasLinha()");

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", LinhaRacaoEnum.getListaValores());
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_TODAS_PORTE)
	@ResponseBody
	public ResponseEntity<?> recuperarTodasPorte() {
		logger.info("RacaoRestController.recuperarTodasPorte()");

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", PorteRacaEnum.getListaValores());
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
