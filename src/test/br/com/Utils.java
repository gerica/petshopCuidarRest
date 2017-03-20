package br.com;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.compartilhado.entidade.Usuario;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.pet.entidade.FaixaIdadeEnum;
import br.com.modulo.pet.entidade.PorteRacaEnum;
import br.com.modulo.pet.entidade.TipoPet;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.MedicamentoLote;
import br.com.modulo.produto.entidade.MedicamentoTipoPet;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.entidade.enums.MedicamentoCategoriaEnum;

public class Utils {

	public static Produto criarMedicamento1() {
		Medicamento m = new Medicamento();
		m.setId(new Long(1));
		m.setCategoria(MedicamentoCategoriaEnum.VERMIFUGO);
		m.setFaixaIdade(FaixaIdadeEnum.ADULTO);
		m.setMarca("Marca medicamento teste 1");
		m.setNome("Vermifugo teste 1");
		m.setPorte(PorteRacaEnum.MEDIO);
		m.setMedicamentoTipoPet(criarMedicamentoTipoPet(m));

		return m;
	}

	public static Produto criarMedicamento2() {
		Medicamento m = new Medicamento();
		m.setId(new Long(2));
		m.setCategoria(MedicamentoCategoriaEnum.VERMIFUGO);
		m.setFaixaIdade(FaixaIdadeEnum.ADULTO);
		m.setMarca("Marca medicamento teste 2");
		m.setNome("Vermifugo teste 2");
		m.setPorte(PorteRacaEnum.MEDIO);
		m.setMedicamentoTipoPet(criarMedicamentoTipoPet(m));

		return m;
	}

	public static List<Lote> criarMedicamentoLote1(Medicamento medicamento) {
		List<Lote> result = new ArrayList<>();
		MedicamentoLote lote = new MedicamentoLote();
		lote.setId(new Long(1));
		lote.setDataLote(new Date());
		lote.setDataValidade(new Date());
		lote.setMedicamento(medicamento);
		lote.setNumero(new Long(123456789));
		lote.setQuantidade(new Long(10));
		lote.setValor(100.0);
		lote.setValorVenda(15.0);

		result.add(lote);

		return result;
	}

	public static List<Lote> criarMedicamentoLote2(Medicamento medicamento) {
		List<Lote> result = new ArrayList<>();
		MedicamentoLote lote = new MedicamentoLote();
		lote.setId(new Long(2));
		lote.setDataLote(new Date());
		lote.setDataValidade(new Date());
		lote.setMedicamento(medicamento);
		lote.setNumero(new Long(987654321));
		lote.setQuantidade(new Long(5));
		lote.setValor(50.0);
		lote.setValorVenda(20.0);

		result.add(lote);

		return result;
	}

	public static Pessoa criarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(new Long(1));
		pessoa.setNome("Pessoa de Teste");
		pessoa.setSexo("Homem");
		pessoa.setDtNascimento(getDataNascimento());
		pessoa.setTipoPessoa("Física");

		return pessoa;
	}

	public static Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(new Long(1));
		usuario.setEmail("usuario.teste@test.pet.shop");
		usuario.setUsername("Usuario de Teste");
		return usuario;
	}

	private static Set<MedicamentoTipoPet> criarMedicamentoTipoPet(Medicamento medicamento) {
		Set<MedicamentoTipoPet> result = new HashSet<>();
		MedicamentoTipoPet tipoPet = new MedicamentoTipoPet();
		tipoPet.setId(new Long(1));
		tipoPet.setMedicamento(medicamento);
		tipoPet.setTipoPet(criarTipoPet());
		result.add(tipoPet);

		return result;
	}

	private static TipoPet criarTipoPet() {
		TipoPet tipo = new TipoPet();
		tipo.setDsNome("Cachorro");
		tipo.setId(new Long(1));
		return tipo;
	}

	private static Date getDataNascimento() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2005);
		c.set(Calendar.MONTH, 01);
		c.set(Calendar.DAY_OF_MONTH, 10);
		return c.getTime();
	}

}
