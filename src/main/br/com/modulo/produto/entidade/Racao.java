package br.com.modulo.produto.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.modulo.pet.entidade.FaixaIdadeEnum;
import br.com.modulo.pet.entidade.PorteRacaEnum;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.enums.LinhaRacaoEnum;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;

@Entity
@Table(name = "tb_racao", schema = "produto")
public class Racao extends Produto {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_tipo_pet")
	private TipoPet tipoPet;

	@Column(name = "ds_linha")
	@Enumerated(EnumType.STRING)
	private LinhaRacaoEnum linha;

	@Column(name = "ds_porte")
	@Enumerated(EnumType.STRING)
	private PorteRacaEnum porte;

	@Column(name = "ds_faixa_idade")
	@Enumerated(EnumType.STRING)
	private FaixaIdadeEnum faixaIdade;

	@Transient
	private TipoProdutoEnum tipoProduto = TipoProdutoEnum.RACAO;

	public FaixaIdadeEnum getFaixaIdade() {
		return faixaIdade;
	}

	public LinhaRacaoEnum getLinha() {
		return linha;
	}

	public PorteRacaEnum getPorte() {
		return porte;
	}

	public TipoPet getTipoPet() {
		return tipoPet;
	}

	public TipoProdutoEnum getTipoProduto() {
		return tipoProduto;
	}

	public void setFaixaIdade(FaixaIdadeEnum faixaIdade) {
		this.faixaIdade = faixaIdade;
	}

	public void setLinha(LinhaRacaoEnum linha) {
		this.linha = linha;
	}

	public void setPorte(PorteRacaEnum porte) {
		this.porte = porte;
	}

	public void setTipoPet(TipoPet tipoPet) {
		this.tipoPet = tipoPet;
	}

}
