package br.com.modulo.financeiro.entidade.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelatorioPeriodoTipoEnum {

	MENSAL("Mensal"), //
	BIMESTRAL("Bimestral"), //
	TRIMESTRAL("Trimestral"), //
	SIMESTRAL("Simestral"), //
	ANUAL("Anual"), //
	LIVRE("Livre");

	private static Map<Object, RelatorioPeriodoTipoEnum> FORMAT_MAP = Stream.of(RelatorioPeriodoTipoEnum.values())
			.collect(Collectors.toMap(s -> s.descricao, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static RelatorioPeriodoTipoEnum fromString(String string) {
		RelatorioPeriodoTipoEnum valor = FORMAT_MAP.get(string);
		if (valor == null) {
			throw new IllegalArgumentException(string + " has no corresponding value");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (RelatorioPeriodoTipoEnum cat : RelatorioPeriodoTipoEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;

	private RelatorioPeriodoTipoEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
