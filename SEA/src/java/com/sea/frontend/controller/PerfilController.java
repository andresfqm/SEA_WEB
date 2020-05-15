/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Perfil;
import com.sea.backend.model.PerfilFacadeLocal;
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
public class PerfilController implements Serializable {

	@EJB
	private PerfilFacadeLocal perfilEJB;
	private Perfil perfil;
	private List<Perfil> listaPerfiles;

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@PostConstruct
	public void init() {
		listaPerfiles = perfilEJB.findAll();
		perfil = new Perfil();
	}

	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

	public void registrar() {
		try {
			perfilEJB.create(perfil);

		} catch (Exception e) {

		}

	}

}
