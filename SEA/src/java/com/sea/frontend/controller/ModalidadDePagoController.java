/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.ModalidadDePago;
import com.sea.backend.model.ModalidadDePagoFacadeLocal;
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
public class ModalidadDePagoController implements Serializable {

	@EJB
	private ModalidadDePagoFacadeLocal modalidadPagoEJB;

	private ModalidadDePago modalidadP;

	private List<ModalidadDePago> modalidad;

	public List<ModalidadDePago> getModalidad() {
		return modalidad;
	}

	public void setModalidad(List<ModalidadDePago> modalidad) {
		this.modalidad = modalidad;
	}

	public ModalidadDePago getModalidadP() {
		return modalidadP;
	}

	public void setModalidadP(ModalidadDePago modalidadP) {
		this.modalidadP = modalidadP;
	}

	@PostConstruct
	public void init() {
		modalidadP = new ModalidadDePago();
		modalidad = modalidadPagoEJB.findAll();
	}

	public void registrar() {
		try {
			modalidadPagoEJB.create(modalidadP);
		} catch (Exception e) {

		}

	}

}
