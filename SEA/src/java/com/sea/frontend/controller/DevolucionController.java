/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Devolucion;
import com.sea.backend.model.DevolucionFacadeLocal;
import java.io.Serializable;
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
public class DevolucionController implements Serializable {

	@EJB
	private DevolucionFacadeLocal devolucionEJB;

	private Devolucion devolucion;

	public Devolucion getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}

	@PostConstruct
	public void init() {
		devolucion = new Devolucion();

	}

	public void registrar() {
		try {

			devolucionEJB.create(devolucion);
		} catch (Exception e) {

		}
	}

}
