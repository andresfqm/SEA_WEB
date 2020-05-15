/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Departamento;
import com.sea.backend.model.DepartamentoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class DepartamentoController implements Serializable {

	@EJB
	private DepartamentoFacadeLocal departamentoEJB;

	private Departamento departamento;
	
	private List<Departamento> listaDepartametos;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@PostConstruct
	public void init() {
		departamento = new Departamento();
		listaDepartametos = departamentoEJB.findAll();
	}

	public List<Departamento> getListaDepartametos() {
		return listaDepartametos;
	}

	public void setListaDepartametos(List<Departamento> listaDepartametos) {
		this.listaDepartametos = listaDepartametos;
	}

	public void registrar() {
		try {
			departamentoEJB.create(departamento);
		} catch (Exception e) {

		}

	}
}
