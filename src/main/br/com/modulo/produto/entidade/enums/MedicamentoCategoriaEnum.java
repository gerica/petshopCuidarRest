package br.com.modulo.produto.entidade.enums;

import java.util.ArrayList;
import java.util.List;

public enum MedicamentoCategoriaEnum {

	ANTIPULGAS_CARRAPTOS(1, "Antipulgas e carrapatos"), //
	HOMEOPATICOS_FLORAIS(2, "Homeopáticos e florais"), //
	CUIDADOS_POS_CIRURGICOS(3, "Cuidados pós-cirúrgicos"), //
	MEDICAMENTO(4, "Medicamentos"), //
	SHAMPOO_TERAPEUTICO(5, "Shampoos terapêuticos"), //
	VERMIFUGO(6, "Vermífugos"), //
	VITAMINA_SUPLEMENTO(7, "Vitaminas e suplementos");

	private String descricao;
	private Integer id;

	private MedicamentoCategoriaEnum(Integer id, String descricao) {
		this.descricao = descricao;
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getId() {
		return id;
	}
	
	public List<String> getListaValores(){
		List<String> result = new ArrayList<String>();
		for (MedicamentoCategoriaEnum  cat : MedicamentoCategoriaEnum.values()) {
			result.add(cat.getDescricao());
		}
		return result;
	}

}
