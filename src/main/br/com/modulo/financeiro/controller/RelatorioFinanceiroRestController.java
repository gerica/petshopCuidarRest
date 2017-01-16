package br.com.modulo.financeiro.controller;

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
import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapper;
import br.com.modulo.financeiro.entidade.RelatorioVenda;
import br.com.modulo.financeiro.service.RelatorioVendaService;

@RestController
@RequestMapping(value = RelatorioFinanceiroRestController.URI_FINANCEIRO)
public class RelatorioFinanceiroRestController {

	private static final Logger logger = LoggerFactory.getLogger(RelatorioFinanceiroRestController.class);

	public static final String URI_FINANCEIRO = "financeiroRelatorio/";
	public static final String URI_CONSULTAR_VENDA = "consultarVenda";

	@Autowired
	private RelatorioVendaService service;

	@RequestMapping(method = RequestMethod.POST, value = URI_CONSULTAR_VENDA)
	@ResponseBody
	public ResponseEntity<?> consultarVenda(@RequestBody RelatorioVendaWrapper wrapper) {
		logger.info("RelatorioFinanceiroRestController.consultarVenda()");

		List<RelatorioVenda> result = null;
		try {
			result = service.consultar(wrapper);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
