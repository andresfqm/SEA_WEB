/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Descuento;
import com.sea.backend.model.DescuentoFacadeLocal;
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
public class DescuentoController implements Serializable {

	@EJB
	private DescuentoFacadeLocal descuentoEJB;

	private Descuento descuento;

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	@PostConstruct
	public void init() {

		descuento = new Descuento();

	}

	public void registrar() {
		try {
			descuentoEJB.create(descuento);

		} catch (Exception e) {

		}

	}

}
