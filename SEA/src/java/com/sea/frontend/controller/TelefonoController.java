/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Telefono;
import com.sea.backend.model.TelefonoFacadeLocal;
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
public class TelefonoController implements Serializable {

	@EJB
	private TelefonoFacadeLocal telefonoEJB;

	private Telefono telefono;

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@PostConstruct
	public void init() {
		telefono = new Telefono();
	}

	public void registrar() {
		try {
			telefonoEJB.create(telefono);

		} catch (Exception e) {

		}

	}

}
