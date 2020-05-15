/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Material;
import com.sea.backend.model.MaterialFacadeLocal;
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
public class MaterialController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private MaterialFacadeLocal materialEJB;

	private Material material;
	private List<Material> listaMateriales;
	private int idProducto;
	private String accion;
	private String subcat = "";

	String listString;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getListString() {
		return listString;
	}

	public void setListString(String listString) {
		this.listString = listString;
	}

	public List<Material> getListaMateriales() {
		listaMateriales = materialEJB.findAll();
		return listaMateriales;
	}

	public void setListaMateriales(List<Material> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	@PostConstruct
	public void init() {
		material = new Material();
	}

	public void registrar() {
		try {
			getAccion();
			materialEJB.create(material);
			snackbarData.put("message", "Se creó el material '" + material.getNombre() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void eliminar(Material material) {
		try {
			materialEJB.remove(material);
			snackbarData.put("message", "Se eliminó el material '" + material.getNombre() + "'");
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
			materialEJB.edit(material);
			snackbarData.put("message", "Se medificó el material '" + material.getNombre() + "'");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
		material.setNombre(subcat);
		material.setCodigo(subcat);
	}

	public void limpiar() {
		material.setCodigo(subcat);
		material.setNombre(subcat);
	}

	public void leerId(Material material) {
		this.material = material;
		setAccion("Modificar");
	}

	public void obtenerMateriales() throws Exception {
		try {

			listaMateriales = materialEJB.datosMaterial(idProducto);
			System.out.println(getListaMateriales());
			listString = "";
			for (Material s : listaMateriales) {
				listString += s.getNombre() + ", ";
				System.out.println(s.getNombre());
			}
		} catch (Exception e) {
			throw e;
		}

	}

}
