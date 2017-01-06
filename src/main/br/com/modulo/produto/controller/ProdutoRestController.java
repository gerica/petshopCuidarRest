package br.com.modulo.produto.controller;

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
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.produto.service.ProdutoService;

@RestController
@RequestMapping(value = ProdutoRestController.URI_PRODUTO)
public class ProdutoRestController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoRestController.class);

	public static final String URI_PRODUTO = "produto/";

	private static final String URL_RECUPERAR_PRODUTO_POR_DESCRICAO = "recuperarProdutoPorDescricao/{descricao}";
	private static final String URL_RECUPERAR_LOTE_POR_ID_PRODUTO = "recuperarLotePorIdProduto/{idProduto}/{tipoProduto}";

	@Autowired
	private ProdutoService service;

	@Autowired
	private LoteService loteService;

	@RequestMapping(method = RequestMethod.GET, value = URL_RECUPERAR_LOTE_POR_ID_PRODUTO)
	@ResponseBody
	public ResponseEntity<?> recuperarLotePorIdProduto(@PathVariable(value = "idProduto") Long idProduto,
			@PathVariable(value = "tipoProduto") String tipoProduto) {
		logger.info("ProdutoRestController.recuperarLotePorIdProduto()");

		List<? extends Lote> result = null;
		try {
			result = loteService.findByIdProduto(idProduto, TipoProdutoEnum.fromString(tipoProduto));
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = URL_RECUPERAR_PRODUTO_POR_DESCRICAO)
	@ResponseBody
	public ResponseEntity<?> recuperarPorDescricao(@PathVariable(value = "descricao") String descricao) {
		logger.info("ProdutoRestController.recuperarPorDescricao()");

		List<Produto> result = null;
		try {
			result = service.findByDescricao(descricao);
		} catch (PetShopBusinessException e) {
			ErrorResponse error = new ErrorResponse(e.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessResponse success = new SuccessResponse("Operação realizada com sucesso", result);
		return new ResponseEntity<SuccessResponse>(success, HttpStatus.OK);

	}

}
