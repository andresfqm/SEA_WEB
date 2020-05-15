/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Direccion;
import com.sea.backend.model.DireccionFacadeLocal;
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
public class DireccionController implements Serializable {

	@EJB
	private DireccionFacadeLocal direccionEJB;

	private Direccion direccion;
	private int idCliente;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Direccion getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(Direccion direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	private Direccion direccionCliente;

	public void obtenerDireccionCliente() throws Exception {
		try {
			direccionCliente = direccionEJB.direccionCliente(idCliente);
		} catch (Exception e) {
			throw e;
		}

	}

	@PostConstruct
	public void init() {

		direccion = new Direccion();

	}

	public void registrar() {

		try {
			direccionEJB.create(direccion);
		} catch (Exception e) {

		}

	}

}
