package br.com.modulo.produto.entidade.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LinhaRacaoEnum {

	SUPER_PREMIUM("Super Premium"), //
	PREMIUM("Premium"), //
	PRESCRITA("Prescrita"), //
	STANDARD("Standard");

	private static Map<Object, LinhaRacaoEnum> FORMAT_MAP = Stream.of(LinhaRacaoEnum.values())
			.collect(Collectors.toMap(s -> s.descricao, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static LinhaRacaoEnum fromString(String string) {
		LinhaRacaoEnum valor = FORMAT_MAP.get(string);
		if (valor == null) {
			throw new IllegalArgumentException(string + " has no corresponding value");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (LinhaRacaoEnum cat : LinhaRacaoEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;

	private LinhaRacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
