package br.com.modulo.pet.entidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PorteRacaEnum {

	MINI("Mini"), //
	PEQUENO("Pequeno"), //
	MEDIO("Médio"), //
	GRANDE("Grande"), //
	GIGANTE("Gigante");

	private static Map<Object, PorteRacaEnum> FORMAT_MAP = Stream.of(PorteRacaEnum.values())
			.collect(Collectors.toMap(s -> s.descricao, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static PorteRacaEnum fromString(String string) {
		PorteRacaEnum valor = FORMAT_MAP.get(string);
		if (valor == null) {
			throw new IllegalArgumentException(string + " has no corresponding value");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (PorteRacaEnum cat : PorteRacaEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;

	private PorteRacaEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
