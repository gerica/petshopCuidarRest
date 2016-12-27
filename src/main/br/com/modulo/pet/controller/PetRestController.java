package br.com.modulo.pet.controller;

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
import br.com.modulo.pet.controller.wrapper.PetWrapper;
import br.com.modulo.pet.entidade.Pet;
import br.com.modulo.pet.service.PetService;

@RestController
@RequestMapping(value = PetRestController.URI_PET)
public class PetRestController {

	private static final Logger logger = LoggerFactory.getLogger(PetRestController.class);

	public static final String URI_PET = "pet/";
	private static final String URI_GRAVAR = "gravar";
	private static final String URI_EXCLUIR = "excluir";
	private static final String URI_RECUPERAR_POR_PESSOA_ID = "recuperarPorPessoaId/{idPessoa}";
	private static final String URI_RECUPERAR_PET_POR_NOME = "recuperarPetPorNome/{nomePet}";

	@Autowired
	private PetService petService;

	@RequestMapping(method = RequestMethod.POST, value = URI_EXCLUIR)
	@ResponseBody
	public ResponseEntity<?> excluir(@RequestBody Long idPet) {
		logger.info("PetRestController.excluir()");

		try {
			petService.excluir(idPet);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getInfo() {
		logger.info("PetRestController.getInfo()");

		List<String> msg = new ArrayList<String>();
		msg.add("Serviços");

		SuccessResponse success = new SuccessResponse("Rest de PET", msg);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = URI_GRAVAR)
	@ResponseBody
	public ResponseEntity<?> gravar(@RequestBody PetWrapper petWrapper) {
		logger.info("PetRestController.gravar()");

		try {
			petService.gravar(petWrapper.getPet(), petWrapper.getIdPessoa());
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SuccessResponse>(new SuccessResponse(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_PET_POR_NOME)
	@ResponseBody
	public ResponseEntity<?> recuperarPorNome(@PathVariable(value = "nomePet") String nomePet) {
		logger.info("PetRestConroller.recuperarPorNome()");
		
		List<Pet> result = null;
		try {
			result = petService.findByName(nomePet);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		
		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI_RECUPERAR_POR_PESSOA_ID)
	@ResponseBody
	public ResponseEntity<?> recuperarPorPessoaId(@PathVariable(value = "idPessoa") Long idPessoa) {
		logger.info("PetRestController.recuperarPorPessoaId()");

		List<Pet> result = null;
		try {
			result = petService.findPetByIdPessoa(idPessoa);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
