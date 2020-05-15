/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Sufijo;
import com.sea.backend.model.SufijoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.json.JSONObject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class SufijoController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private SufijoFacadeLocal sufijoEJB;
	private Sufijo sufijo;
	private List<Sufijo> listaSufijos;
	private String accion;
	private String subcat="";

	public SufijoFacadeLocal getSufijoEJB() {
		return sufijoEJB;
	}

	public void setSufijoEJB(SufijoFacadeLocal sufijoEJB) {
		this.sufijoEJB = sufijoEJB;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<Sufijo> getListaSufijos() {
		listaSufijos = sufijoEJB.findAll();
		return listaSufijos;
	}

	public void setListaSufijos(List<Sufijo> listaSufijos) {
		this.listaSufijos = listaSufijos;
	}

	public Sufijo getSufijo() {
		return sufijo;
	}

	public void setSufijo(Sufijo sufijo) {
		this.sufijo = sufijo;
	}

	@PostConstruct
	public void init() {
		sufijo = new Sufijo();

	}

	public void registrar() {
		try {
			getAccion();
			sufijoEJB.create(sufijo);
			snackbarData.put("message", "Se creó la subcategoría '" + sufijo.getDescripcionFabricante()+ "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void eliminar(Sufijo sufijo) {
		try {
			sufijoEJB.remove(sufijo);
			snackbarData.put("message", "Se creó la subcategoría '" + sufijo.getDescripcionFabricante() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void modificar() {
		try {
			getAccion();
			sufijoEJB.edit(sufijo);
			snackbarData.put("message", "Se creó la subcategoría '" + sufijo.getDescripcionFabricante() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
		sufijo.setCodigo(subcat);
		sufijo.setDescripcionFabricante(subcat);
	}
	
	public void limpiar() {

		sufijo.setCodigo(subcat);
		sufijo.setDescripcionFabricante(subcat);
	}

	public void leerId(Sufijo sufijo) {
		this.sufijo = sufijo;
		setAccion("Modificar");
	}

}
