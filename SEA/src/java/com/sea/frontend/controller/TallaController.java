/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Talla;
import com.sea.backend.model.TallaFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class TallaController implements Serializable {

	@EJB
	private TallaFacadeLocal tallaEJB;

	private Talla talla;

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public void init() {
		talla = new Talla();

	}

	public void registrar() {
		try {
			tallaEJB.create(talla);

		} catch (Exception e) {

		}

	}

}
