/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.RegistroSeguimiento;
import com.sea.backend.model.RegistroSeguimientoFacadeLocal;
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
public class RegistroSeguimientoController implements Serializable {

	@EJB
	private RegistroSeguimientoFacadeLocal registroSeguimientoEJB;

	private RegistroSeguimiento registroS;

	public RegistroSeguimiento getRegistroS() {
		return registroS;
	}

	public void setRegistroS(RegistroSeguimiento registroS) {
		this.registroS = registroS;
	}

	@PostConstruct
	public void init() {
		registroS = new RegistroSeguimiento();
	}

	public void registrar() {
		try {
			registroSeguimientoEJB.create(registroS);

		} catch (Exception e) {

		}

	}
}