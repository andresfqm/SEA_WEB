/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Origen;
import com.sea.backend.model.OrigenFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class OrigenController implements Serializable {

	@EJB
	private OrigenFacadeLocal origenEJB;

	private Origen origen;
	private List<Origen> listaOrigen;

	public List<Origen> getListaOrigen() {
		listaOrigen = origenEJB.findAll();
		return listaOrigen;
	}

	public void setListaOrigen(List<Origen> listaOrigen) {
		this.listaOrigen = listaOrigen;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	@PostConstruct
	public void init() {
		origen = new Origen();

	}

	public void registrar() {
		try {
			origenEJB.create(origen);

		} catch (Exception e) {

		}

	}

	public void Eliminar(Origen o) {
		try {
			origenEJB.remove(o);

		} catch (Exception e) {

		}

	}
}
