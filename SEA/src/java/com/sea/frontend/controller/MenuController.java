/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.ViewMenusUsuario;
import com.sea.backend.entities.Usuario;
import com.sea.backend.model.MenuFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class MenuController implements Serializable {

	@EJB
	private MenuFacadeLocal menuEJB;
	private List<ViewMenusUsuario> listaMenuGeneral;

	@PostConstruct
	public void init() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		if (u != null) {
			try {
				listaMenuGeneral = menuEJB.obtenerMenusGenerales(obtenerIdUsuario());
			} catch (Exception ex) {
				Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	//Obteniendo todos los menús del usuario
	public void obtenerMenusGenerales() throws Exception {
		try {
			listaMenuGeneral = menuEJB.obtenerMenusGenerales(obtenerIdUsuario());
		} catch (Exception e) {
			throw e;
		}
	}

	public int obtenerIdUsuario() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		return u.getIdUsuario();
	}

	public List<ViewMenusUsuario> getListaMenuGeneral() {
		return listaMenuGeneral;
	}

	public void setListaMenuGeneral(List<ViewMenusUsuario> listaMenuGeneral) {
		this.listaMenuGeneral = listaMenuGeneral;
	}

}
