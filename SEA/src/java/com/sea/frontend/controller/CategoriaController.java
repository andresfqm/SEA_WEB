/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Categoria;
import com.sea.backend.model.CategoriaFacadeLocal;
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
public class CategoriaController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private CategoriaFacadeLocal categoriaEJB;

	private Categoria categoria;

	private List<Categoria> ListaCategoria;

	private String accion;
	private String categori = "";

	public List<Categoria> getListaCategoria() {
		return ListaCategoria;
	}

	public String getCategori() {
		return categori;
	}

	public void setCategori(String categori) {
		this.categori = categori;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setListaCategoria(List<Categoria> ListaCategoria) {
		this.ListaCategoria = ListaCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		ListaCategoria = categoriaEJB.findAll();
		categori = "";
	}

	public void registrar() {
		try {
			getAccion();
			categoriaEJB.create(categoria);
			snackbarData.put("message", "Se creó la categoría '" + categoria.getNombre() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void eliminar(Categoria c) {
		try {
			categoriaEJB.remove(c);
			snackbarData.put("message", "Se eliminó la categoría '" + categoria.getNombre() + "'");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void modificar() {
		try {
			getAccion();
			categoriaEJB.edit(categoria);
			snackbarData.put("message", "Se modificó la categoría '" + categoria.getNombre() + "'");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
		categoria.setNombre(categori);
	}

	public void limpiar() {
		categoria.setNombre(categori);
	}

	public void leerId(Categoria categoria) {
		this.categoria = categoria;
		setAccion("Modificar");
	}

}
