package br.com.modulo.financeiro.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapperOut;
import br.com.modulo.financeiro.repository.RelatorioVendaRepository;

@Repository
public class RelatorioVendaRepositoryImpl implements RelatorioVendaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void consultarMensal(Integer ano, String periodoValor) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select venda.id as idVenda, nr_desconto as desconto, dt_venda as dtVenda,");
		sb.append(" nr_quantidade as quantidade, nr_valor_total as valorTotal, ");
		sb.append(" pessoa.ds_nome as nomePessoa, usuario.ds_nome as nomeUsuario, ");
		sb.append(" orcamento.dt_orcamento as dtOrcamento ");
		sb.append("  from venda.tb_venda venda ");
		sb.append(" join cliente.tb_pessoa pessoa on venda.id_pessoa = pessoa.id_pessoa ");
		sb.append(" join tb_usuario usuario on usuario.id_usuario = venda.id_usuario ");
		sb.append(" join venda.tb_orcamento orcamento on venda.id_orcamento = orcamento.id ");
		sb.append(" where to_char(dt_venda, 'YYYY-MM') = '");
		sb.append(ano);
		sb.append("-");
		sb.append(periodoValor);
		sb.append("'");

		List<RelatorioVendaWrapperOut> list = entityManager
				.createNativeQuery(sb.toString(), "RelatorioVendaWrapperOutMapping").getResultList();

		for (RelatorioVendaWrapperOut out : list) {
			System.out.println(out);
		}
		// List<RelatorioVendaWrapperOut> result = new ArrayList<>();
		// RelatorioVendaWrapperOut out = null;
		//
		// // for (Object object : result) {
		// for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		// Object[] objects = (Object[]) iterator.next();
		// out = new RelatorioVendaWrapperOut();
		// out.setIdVenda((Long) objects[0]);
		// out.setDesconto((Double) objects[1]);
		// out.setDtVenda((Date) objects[2]);
		// out.setQuantidade((Long) objects[3]);
		// out.setValorTotal((Double) objects[4]);
		// out.setNomePessoa((String) objects[5]);
		// out.setNomeUsuario((String) objects[6]);
		// out.setDtOrcamento((Date) objects[7]);
		// result.add(out);
		//
		// }
		//
		// for (RelatorioVendaWrapperOut out2 : result) {
		// System.out.println(out2);
		// }

	}

}
