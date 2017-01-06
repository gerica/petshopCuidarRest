package br.com.modulo.produto.entidade.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoProdutoEnum {

	RACAO(1, "Ração"), //
	MEDICAMENTO(2, "Medicamento"); //

	private static Map<Object, TipoProdutoEnum> FORMAT_MAP = Stream.of(TipoProdutoEnum.values())
			.collect(Collectors.toMap(s -> s.getDescricao(), Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static TipoProdutoEnum fromString(String descricao) {
		TipoProdutoEnum valor = FORMAT_MAP.get(descricao);
		if (valor == null) {
			throw new IllegalArgumentException(descricao + " não existe enum com esse id");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (TipoProdutoEnum cat : TipoProdutoEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;
	private Integer id;

	private TipoProdutoEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

//	@JsonValue
	public Integer getId() {
		return id;
	}

}
