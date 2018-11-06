package com.wm.dialog.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.wm.dialog.modelo.Cliente;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente clienteSelecionado;

	private List<Cliente> clientes;

	@Inject
	private EntityManager manager;

	@PostConstruct
	public void init() {
		this.clientes = this.manager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	public void buscarClienteComTelefones() {
		System.out.println(">>>> PesquisaClienteBean.buscarClienteComTelefones");
		this.clienteSelecionado = (Cliente) this.manager
				.createQuery("from Cliente c inner join fetch c.telefones where c.codigo = :codigo")
				.setParameter("codigo", clienteSelecionado.getCodigo()).getSingleResult();
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

}
