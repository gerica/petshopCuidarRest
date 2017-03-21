package br.com.modulo.financeiro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.modulo.financeiro.entidade.RelatorioVenda;
import br.com.modulo.financeiro.repository.RelatorioVendaRepository;

@Repository
public class RelatorioVendaRepositoryImpl implements RelatorioVendaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RelatorioVenda> consultarAnual(Integer ano) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select venda.id as idVenda, nr_desconto as desconto, dt_venda as dtVenda,");
		sb.append(" nr_quantidade as quantidade, nr_valor_total as valorTotal, ");
		sb.append(" pessoa.ds_nome as nomePessoa, usuario.ds_nome as nomeUsuario, ");
		sb.append(" orcamento.dt_orcamento as dtOrcamento ");
		sb.append("  from venda.tb_venda venda ");
		sb.append(" join cliente.tb_pessoa pessoa on venda.id_pessoa = pessoa.id_pessoa ");
		sb.append(" join tb_usuario usuario on usuario.id_usuario = venda.id_usuario ");
		sb.append(" join venda.tb_orcamento orcamento on venda.id_orcamento = orcamento.id ");
		sb.append(" where to_char(dt_venda, 'YYYY') = ?");

		Query query = entityManager.createNativeQuery(sb.toString(), "RelatorioVendaMapping");
		query.setParameter(1, ano.toString());
		List<RelatorioVenda> result = query.getResultList();

		return result;
	}

	@Override
	public List<RelatorioVenda> consultarBimenstral(Integer ano, String periodoValor) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select venda.id as idVenda, nr_desconto as desconto, dt_venda as dtVenda,");
		sb.append(" nr_quantidade as quantidade, nr_valor_total as valorTotal, ");
		sb.append(" pessoa.ds_nome as nomePessoa, usuario.ds_nome as nomeUsuario, ");
		sb.append(" orcamento.dt_orcamento as dtOrcamento ");
		sb.append("  from venda.tb_venda venda ");
		sb.append(" join cliente.tb_pessoa pessoa on venda.id_pessoa = pessoa.id_pessoa ");
		sb.append(" join tb_usuario usuario on usuario.id_usuario = venda.id_usuario ");
		sb.append(" join venda.tb_orcamento orcamento on venda.id_orcamento = orcamento.id ");
		sb.append(" where (to_char(dt_venda, 'YYYY-MM') = ? or to_char(dt_venda, 'YYYY-MM') = ?) ");

		Query query = entityManager.createNativeQuery(sb.toString(), "RelatorioVendaMapping");
		switch (periodoValor) {
		case "01":
			query.setParameter(1, ano + "-01");
			query.setParameter(2, ano + "-02");
			break;
		case "02":
			query.setParameter(1, ano + "-03");
			query.setParameter(2, ano + "-04");
			break;
		case "03":
			query.setParameter(1, ano + "-05");
			query.setParameter(2, ano + "-06");
			break;
		case "04":
			query.setParameter(1, ano + "-07");
			query.setParameter(2, ano + "-08");
			break;
		case "05":
			query.setParameter(1, ano + "-09");
			query.setParameter(2, ano + "-10");
			break;
		case "06":
			query.setParameter(1, ano + "-11");
			query.setParameter(2, ano + "-12");
			break;
		default:
			break;
		}
		List<RelatorioVenda> result = query.getResultList();
		return result;
	}

	@Override
	public List<RelatorioVenda> consultarMensal(Integer ano, String periodoValor) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select venda.id as idVenda, nr_desconto as desconto, dt_venda as dtVenda,");
		sb.append(" nr_quantidade as quantidade, nr_valor_total as valorTotal, ");
		sb.append(" pessoa.ds_nome as nomePessoa, usuario.ds_nome as nomeUsuario, ");
		sb.append(" orcamento.dt_orcamento as dtOrcamento ");
		sb.append("  from venda.tb_venda venda ");
		sb.append(" join cliente.tb_pessoa pessoa on venda.id_pessoa = pessoa.id_pessoa ");
		sb.append(" join tb_usuario usuario on usuario.id_usuario = venda.id_usuario ");
		sb.append(" join venda.tb_orcamento orcamento on venda.id_orcamento = orcamento.id ");
		sb.append(" where to_char(dt_venda, 'YYYY-MM') = ?");

		Query query = entityManager.createNativeQuery(sb.toString(), "RelatorioVendaMapping");
		query.setParameter(1, ano + "-" + periodoValor);
		List<RelatorioVenda> result = query.getResultList();

		return result;

	}

	@Override
	public List<RelatorioVenda> consultarSimenstral(Integer ano, String periodoValor) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT venda.id               AS idVenda, ");
		sb.append("        nr_desconto            AS desconto, ");
		sb.append("        dt_venda               AS dtVenda, ");
		sb.append("        nr_quantidade          AS quantidade, ");
		sb.append("        nr_valor_total         AS valorTotal, ");
		sb.append("        pessoa.ds_nome         AS nomePessoa, ");
		sb.append("        usuario.ds_nome        AS nomeUsuario, ");
		sb.append("        orcamento.dt_orcamento AS dtOrcamento ");
		sb.append(" FROM   venda.tb_venda venda  ");
		sb.append("        JOIN cliente.tb_pessoa pessoa ");
		sb.append("          ON venda.id_pessoa = pessoa.id_pessoa ");
		sb.append("        JOIN tb_usuario usuario  ");
		sb.append("          ON usuario.id_usuario = venda.id_usuario ");
		sb.append("        JOIN venda.tb_orcamento orcamento  ");
		sb.append("          ON venda.id_orcamento = orcamento.id ");
		sb.append(" WHERE  ( To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ) ");

		Query query = entityManager.createNativeQuery(sb.toString(), "RelatorioVendaMapping");
		switch (periodoValor) {
		case "01":
			query.setParameter(1, ano + "-01");
			query.setParameter(2, ano + "-02");
			query.setParameter(3, ano + "-03");		
			query.setParameter(4, ano + "-04");
			query.setParameter(5, ano + "-05");
			query.setParameter(6, ano + "-06");
			break;
		case "02":
			query.setParameter(1, ano + "-07");
			query.setParameter(2, ano + "-08");
			query.setParameter(3, ano + "-09");		
			query.setParameter(4, ano + "-10");
			query.setParameter(5, ano + "-11");
			query.setParameter(6, ano + "-12");
			break;
		default:
			break;
		}
		List<RelatorioVenda> result = query.getResultList();
		return result;
	}

	@Override
	public List<RelatorioVenda> consultarTrimenstral(Integer ano, String periodoValor) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT venda.id               AS idVenda, ");
		sb.append("        nr_desconto            AS desconto, ");
		sb.append("        dt_venda               AS dtVenda, ");
		sb.append("        nr_quantidade          AS quantidade, ");
		sb.append("        nr_valor_total         AS valorTotal, ");
		sb.append("        pessoa.ds_nome         AS nomePessoa, ");
		sb.append("        usuario.ds_nome        AS nomeUsuario, ");
		sb.append("        orcamento.dt_orcamento AS dtOrcamento ");
		sb.append(" FROM   venda.tb_venda venda  ");
		sb.append("        JOIN cliente.tb_pessoa pessoa ");
		sb.append("          ON venda.id_pessoa = pessoa.id_pessoa ");
		sb.append("        JOIN tb_usuario usuario  ");
		sb.append("          ON usuario.id_usuario = venda.id_usuario ");
		sb.append("        JOIN venda.tb_orcamento orcamento  ");
		sb.append("          ON venda.id_orcamento = orcamento.id ");
		sb.append(" WHERE  ( To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ");
		sb.append("           OR To_char(dt_venda, 'YYYY-MM') = ? ) ");

		Query query = entityManager.createNativeQuery(sb.toString(), "RelatorioVendaMapping");
		switch (periodoValor) {
		case "01":
			query.setParameter(1, ano + "-01");
			query.setParameter(2, ano + "-02");
			query.setParameter(3, ano + "-03");
			break;
		case "02":
			query.setParameter(1, ano + "-04");
			query.setParameter(2, ano + "-05");
			query.setParameter(3, ano + "-06");
			break;
		case "03":
			query.setParameter(1, ano + "-07");
			query.setParameter(2, ano + "-08");
			query.setParameter(3, ano + "-09");
			break;
		case "04":
			query.setParameter(1, ano + "-10");
			query.setParameter(2, ano + "-11");
			query.setParameter(3, ano + "-12");
			break;
		default:
			break;
		}
		List<RelatorioVenda> result = query.getResultList();
		return result;
	}

}
