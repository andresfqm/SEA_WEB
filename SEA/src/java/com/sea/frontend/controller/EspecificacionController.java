/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Especificacion;
import com.sea.backend.model.EspecificacionFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class EspecificacionController implements Serializable {

	private EspecificacionFacadeLocal especificacionEJB;

	private Especificacion especificacion;

	public Especificacion getEspecificacion() {
		return especificacion;
	}

	public void setEspecificacion(Especificacion especificacion) {
		this.especificacion = especificacion;
	}

	@PostConstruct
	public void init() {

		especificacion = new Especificacion();

	}

	public void registrar() {
		try {
			especificacionEJB.create(especificacion);
		} catch (Exception e) {

		}

	}
}
