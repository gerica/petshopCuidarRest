package br.com.modulo.produto.entidade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.modulo.pet.entidade.FaixaIdadeEnum;
import br.com.modulo.pet.entidade.PorteRacaEnum;
import br.com.modulo.produto.entidade.enums.MedicamentoCategoriaEnum;

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
	private Collection<MedicamentoTipoPet> medicamentoTipoPet;

	public void addMedicamentoTipoPet(MedicamentoTipoPet tipo) {
		if (this.medicamentoTipoPet == null) {
			this.medicamentoTipoPet = new ArrayList<MedicamentoTipoPet>();
		}
		this.medicamentoTipoPet.add(tipo);
	}

	public MedicamentoCategoriaEnum getCategoria() {
		return categoria;
	}

	public FaixaIdadeEnum getFaixaIdade() {
		return faixaIdade;
	}

	public Collection<MedicamentoTipoPet> getMedicamentoTipoPet() {
		return medicamentoTipoPet;
	}

	public PorteRacaEnum getPorte() {
		return porte;
	}

	public void setCategoria(MedicamentoCategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public void setFaixaIdade(FaixaIdadeEnum faixaIdade) {
		this.faixaIdade = faixaIdade;
	}

	public void setMedicamentoTipoPet(Collection<MedicamentoTipoPet> medicamentoTipoPet) {
		this.medicamentoTipoPet = medicamentoTipoPet;
	}

	public void setPorte(PorteRacaEnum porte) {
		this.porte = porte;
	}

}
