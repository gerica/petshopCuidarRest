package br.com.modulo.cliente.controller.wrapper;

import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Endereco;

public class ClienteWrapper {

	private Long idPessoa;
	private Endereco endereco;
	private Cidade cidade;

	public Cidade getCidade() {
		return cidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

}
