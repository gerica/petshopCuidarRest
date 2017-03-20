package br.com.modulo.produto.entidade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.modulo.pet.entidade.FaixaIdadeEnum;
import br.com.modulo.pet.entidade.PorteRacaEnum;
import br.com.modulo.produto.entidade.enums.MedicamentoCategoriaEnum;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;

@Entity
@Table(name = "tb_medicamento", schema = "produto")
public class Medicamento extends Produto {

	private static final long serialVersionUID = 1L;

	@Column(name = "ds_porte")
	@Enumerated(EnumType.STRING)
	private PorteRacaEnum porte;

	@Column(name = "ds_faixa_idade")
	@Enumerated(EnumType.STRING)
	private FaixaIdadeEnum faixaIdade;

	@Column(name = "ds_categoria")
	@Enumerated(EnumType.STRING)
	private MedicamentoCategoriaEnum categoria;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "medicamento", cascade = { CascadeType.ALL })
	private Set<MedicamentoTipoPet> medicamentoTipoPet;

	@Transient
	private TipoProdutoEnum tipoProduto = TipoProdutoEnum.MEDICAMENTO;

	public void addMedicamentoTipoPet(MedicamentoTipoPet tipo) {
		if (this.medicamentoTipoPet == null) {
			this.medicamentoTipoPet = new HashSet<>();
		}
		this.medicamentoTipoPet.add(tipo);
	}

	public MedicamentoCategoriaEnum getCategoria() {
		return categoria;
	}

	public FaixaIdadeEnum getFaixaIdade() {
		return faixaIdade;
	}

	public Set<MedicamentoTipoPet> getMedicamentoTipoPet() {
		return medicamentoTipoPet;
	}

	public PorteRacaEnum getPorte() {
		return porte;
	}

	public TipoProdutoEnum getTipoProduto() {
		return tipoProduto;
	}

	public void setCategoria(MedicamentoCategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public void setFaixaIdade(FaixaIdadeEnum faixaIdade) {
		this.faixaIdade = faixaIdade;
	}

	public void setMedicamentoTipoPet(Set<MedicamentoTipoPet> medicamentoTipoPet) {
		this.medicamentoTipoPet = medicamentoTipoPet;
	}

	public void setPorte(PorteRacaEnum porte) {
		this.porte = porte;
	}

}
