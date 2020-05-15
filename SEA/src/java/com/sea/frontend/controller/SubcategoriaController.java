/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Categoria;
import com.sea.backend.entities.Subcategoria;
import com.sea.backend.model.SubcategoriaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.json.JSONObject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class SubcategoriaController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private SubcategoriaFacadeLocal SubcategoriaEJB;
	private Subcategoria subcategoria;
	private List<Subcategoria> listaSubcategoria;
	private String accion;
	private String subcat = "";
	private Categoria idCategoria;

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<Subcategoria> getListaSubcategoria() {
		listaSubcategoria = SubcategoriaEJB.findAll();
		return listaSubcategoria;
	}

	public void setListaSubcategoria(List<Subcategoria> listaSubcategoria) {
		this.listaSubcategoria = listaSubcategoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	@PostConstruct
	public void init() {
		subcategoria = new Subcategoria();
		listaSubcategoria = SubcategoriaEJB.findAll();
		idCategoria = new Categoria();
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getSubcat() {
		return subcat;
	}

	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}

	public void registrar() {
		try {
			getAccion();
			subcategoria.setTblCategoriaIdCategoria(idCategoria);
			SubcategoriaEJB.create(subcategoria);
			snackbarData.put("message", "Se creó la subcategoría '" + subcategoria.getNombre() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void eliminar(Subcategoria sub) {
		try {
			SubcategoriaEJB.remove(sub);
			snackbarData.put("message", "Se eliminó la subcategoría '" + subcategoria.getNombre() + "'");
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
			SubcategoriaEJB.edit(subcategoria);
			snackbarData.put("message", "Se modificó la subcategoría '" + subcategoria.getNombre() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
		subcategoria.setCodigo(subcat);
		subcategoria.setNombre(subcat);
	}

	public void limpiar() {
		subcategoria.setCodigo(subcat);
		subcategoria.setNombre(subcat);
	}

	public void leerId(Subcategoria sub) {
		this.subcategoria = sub;
		setAccion("Modificar");
	}

}
