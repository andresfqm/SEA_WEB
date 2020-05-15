/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.ComentarioCambioAsesor;
import com.sea.backend.model.ComentarioCambioAsesorFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class ComentarioCambioAsesorController implements Serializable {

	@EJB
	private ComentarioCambioAsesorFacadeLocal cambioAsesorEJB;

	private ComentarioCambioAsesor cambioA;

	public ComentarioCambioAsesor getCambioA() {
		return cambioA;
	}

	public void setCambioA(ComentarioCambioAsesor cambioA) {
		this.cambioA = cambioA;
	}

	@PostConstruct
	public void init() {
		cambioA = new ComentarioCambioAsesor();

	}

	public void registrar() {
		try {
			cambioAsesorEJB.create(cambioA);
		} catch (Exception e) {

		}

	}

}
