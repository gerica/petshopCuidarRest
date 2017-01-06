package br.com.modulo.venda.entidade.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusOrcamentoEnum {

	ABERTO("Aberto"), //
	FECHADO("Fechado"), //
	REALIZADO("Realizado");

	private static Map<Object, StatusOrcamentoEnum> FORMAT_MAP = Stream.of(StatusOrcamentoEnum.values())
			.collect(Collectors.toMap(s -> s.descricao, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static StatusOrcamentoEnum fromString(String string) {
		StatusOrcamentoEnum valor = FORMAT_MAP.get(string);
		if (valor == null) {
			throw new IllegalArgumentException(string + " has no corresponding value");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (StatusOrcamentoEnum cat : StatusOrcamentoEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;

	private StatusOrcamentoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
