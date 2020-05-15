/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Email;
import com.sea.backend.model.EmailFacadeLocal;
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
public class EmailController implements Serializable {

	@EJB
	private EmailFacadeLocal emailEJB;
	
	private List<Email> listaEmail;

	private Email email;

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	@PostConstruct
	public void init() {
		listaEmail = emailEJB.findAll();
		email = new Email();
	}

	public List<Email> getListaEmail() {
		return listaEmail;
	}

	public void setListaEmail(List<Email> listaEmail) {
		this.listaEmail = listaEmail;
	}

	public void registrar() {
		try {
			emailEJB.create(email);
		} catch (Exception e) {

		}

	}

}
