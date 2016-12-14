package br.com.modulo.cliente.controller;

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

import br.com.compartilhado.controller.UriConstPetShop;
import br.com.compartilhado.controller.model.ErrorResponse;
import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.service.PessoaService;

@RestController
@RequestMapping(value = UriConstPetShop.URI_PESSOA)
public class ClienteRestConroller {

	private static final Logger logger = LoggerFactory.getLogger(ClienteRestConroller.class);

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("ClienteRestConroller.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest de CLIENTE", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = UriConstPetShop.URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody Pessoa pessoa) {
		logger.info("ClienteRestConroller.gravar()");
		Pessoa pessoaDB = null;
		try {
			pessoaDB = pessoaService.gravar(pessoa);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", pessoaDB);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_PESSOA_RECUPERAR_TODOS)
	@ResponseBody
	public ResponseEntity<?> recuperarTodos() {
		logger.info("ClienteRestConroller.recuperarTodos()");

		Iterable<Pessoa> result = null;
		try {
			result = pessoaService.findAll();
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = UriConstPetShop.URI_PESSOA_RECUPERAR_POR_ID)
	@ResponseBody
	public ResponseEntity<?> recuperarPorId(@PathVariable(value = "idPessoa") Long idPessoa) {
		logger.info("ClienteRestConroller.recuperarPorId()");

		Pessoa result = null;
		try {
			result = pessoaService.findById(idPessoa);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
