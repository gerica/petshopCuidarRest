package br.com.modulo.produto.entidade.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MedicamentoCategoriaEnum {

	ANTIPULGAS_CARRAPTOS("Antipulgas e carrapatos"), //
	HOMEOPATICOS_FLORAIS("Homeopáticos e florais"), //
	CUIDADOS_POS_CIRURGICOS("Cuidados pós-cirúrgicos"), //
	MEDICAMENTO("Medicamentos"), //
	SHAMPOO_TERAPEUTICO("Shampoos terapêuticos"), //
	VERMIFUGO("Vermífugos"), //
	VITAMINA_SUPLEMENTO("Vitaminas e suplementos");

	private static Map<Object, MedicamentoCategoriaEnum> FORMAT_MAP = Stream.of(MedicamentoCategoriaEnum.values())
			.collect(Collectors.toMap(s -> s.descricao, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static MedicamentoCategoriaEnum fromString(String string) {
		MedicamentoCategoriaEnum valor = FORMAT_MAP.get(string);
		if (valor == null) {
			throw new IllegalArgumentException(string + " has no corresponding value");
		}
		return valor;
	}

	public static List<String> getListaValores() {
		List<String> result = new ArrayList<String>();
		for (MedicamentoCategoriaEnum cat : MedicamentoCategoriaEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

	private String descricao;

	private MedicamentoCategoriaEnum(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}
