/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.TiempoEntrega;
import com.sea.backend.model.TiempoEntregaFacadeLocal;
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
public class TiempoEntregaController implements Serializable {

	@EJB
	private TiempoEntregaFacadeLocal tiempoEntregaEJB;

	private TiempoEntrega tiempoE;

	private List<TiempoEntrega> tiempoEntregas;

	public List<TiempoEntrega> getTiempoEntregas() {
		return tiempoEntregas;
	}

	public void setTiempoEntregas(List<TiempoEntrega> tiempoEntregas) {
		this.tiempoEntregas = tiempoEntregas;
	}

	public TiempoEntrega getTiempoE() {
		return tiempoE;
	}

	public void setTiempoE(TiempoEntrega tiempoE) {
		this.tiempoE = tiempoE;
	}

	@PostConstruct
	public void init() {
		tiempoE = new TiempoEntrega();
		tiempoEntregas = tiempoEntregaEJB.findAll();

	}

	public void registrar() {
		try {
			tiempoEntregaEJB.create(tiempoE);

		} catch (Exception e) {

		}

	}

}
