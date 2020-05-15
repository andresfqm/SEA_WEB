/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.CondicionesGarantia;
import com.sea.backend.model.CondicionesGarantiaFacadeLocal;
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
public class CondicionesGarantiaController implements Serializable {

	@EJB
	private CondicionesGarantiaFacadeLocal condicionesGarantiaEJB;

	private CondicionesGarantia condicionesg;

	public CondicionesGarantia getCondicionesg() {
		return condicionesg;
	}

	public void setCondicionesg(CondicionesGarantia condicionesg) {
		this.condicionesg = condicionesg;
	}

	public void init() {

		condicionesg = new CondicionesGarantia();

	}

	@PostConstruct
	public void registrar() {
		try {
			condicionesGarantiaEJB.create(condicionesg);

		} catch (Exception e) {

		}

	}

}
