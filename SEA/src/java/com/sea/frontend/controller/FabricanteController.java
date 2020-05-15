/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Fabricante;
import com.sea.backend.model.FabricanteFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class FabricanteController implements Serializable {

	@Named
	private FabricanteFacadeLocal fabricanteEJB;

	private Fabricante fabricante;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@PostConstruct
	public void init() {

		fabricante = new Fabricante();

	}

	public void registrar() {
		try {
			fabricanteEJB.create(fabricante);

		} catch (Exception e) {

		}

	}

}
