/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.ProductoEspecificacion;
import com.sea.backend.model.ProductoEspecificacionFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class ProductoEspecificacionController implements Serializable {

	@EJB
	private ProductoEspecificacionFacadeLocal productoEspecificacionEJB;
	private ProductoEspecificacion productoE;

	public ProductoEspecificacion getProductosE() {
		return productoE;
	}

	public void setProductosE(ProductoEspecificacion productosE) {
		this.productoE = productosE;
	}

	public void init() {
		productoE = new ProductoEspecificacion();

	}

	public void registrar() {
		try {
			productoEspecificacionEJB.create(productoE);

		} catch (Exception e) {

		}

	}

}
