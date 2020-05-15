/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.LugaresEntrega;
import com.sea.backend.model.LugaresEntregaFacadeLocal;
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
public class LugaresEntregaController implements Serializable {

	@EJB
	private LugaresEntregaFacadeLocal lugaresEntregaEJB;

	private LugaresEntrega lugaresE;
	private List<LugaresEntrega> entregas;

	public List<LugaresEntrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<LugaresEntrega> entregas) {
		this.entregas = entregas;
	}

	public LugaresEntrega getLugaresE() {
		return lugaresE;
	}

	public void setLugaresE(LugaresEntrega lugaresE) {
		this.lugaresE = lugaresE;
	}

	@PostConstruct
	public void init() {
		lugaresE = new LugaresEntrega();
		entregas = lugaresEntregaEJB.findAll();

	}

	public void registrar() {
		try {

			lugaresEntregaEJB.create(lugaresE);
		} catch (Exception e) {

		}

	}

}
