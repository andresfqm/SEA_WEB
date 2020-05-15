/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.DescuentoVolumen;
import com.sea.backend.model.DescuentoVolumenFacadeLocal;
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
public class DescuentoVolumenController implements Serializable {

	@EJB
	private DescuentoVolumenFacadeLocal descuentoVolumenEJB;

	private DescuentoVolumen descuentoV;

	private List<DescuentoVolumen> descuentoVolumen;

	public List<DescuentoVolumen> getDescuentoVolumen() {
		return descuentoVolumen;
	}

	public void setDescuentoVolumen(List<DescuentoVolumen> descuentoVolumen) {
		this.descuentoVolumen = descuentoVolumen;
	}

	public DescuentoVolumen getDescuentoV() {
		return descuentoV;
	}

	public void setDescuentoV(DescuentoVolumen descuentoV) {
		this.descuentoV = descuentoV;
	}

	@PostConstruct
	public void init() {
		descuentoV = new DescuentoVolumen();

		descuentoVolumen = descuentoVolumenEJB.findAll();
	}

	public void registrar() {
		try {
			descuentoVolumenEJB.create(descuentoV);

		} catch (Exception e) {

		}

	}

}
