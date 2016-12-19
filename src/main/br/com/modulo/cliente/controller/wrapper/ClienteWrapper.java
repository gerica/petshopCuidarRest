package br.com.modulo.cliente.controller.wrapper;

import br.com.modulo.cliente.entidade.Documento;
import br.com.modulo.cliente.entidade.Endereco;
import br.com.modulo.cliente.entidade.Telefone;

public class ClienteWrapper {

	private Long idPessoa;
	private Endereco endereco;
	private Telefone telefone;
	private Documento documento;
	

	public Documento getDocumento() {
		return documento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
