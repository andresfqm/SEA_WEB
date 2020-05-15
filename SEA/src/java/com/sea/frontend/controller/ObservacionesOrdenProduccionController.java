/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.OrdenProduccion;
import com.sea.backend.entities.ObservacionesOrdenProduccion;
import com.sea.backend.model.OrdenProduccionFacadeLocal;
import com.sea.backend.model.ObservacionesOrdenProduccionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.json.JSONObject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Depurador
 */
@Named
@ViewScoped
public class ObservacionesOrdenProduccionController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private ObservacionesOrdenProduccionFacadeLocal observacionesOPEJB;
	private ObservacionesOrdenProduccion observacionesOP;
	private List<ObservacionesOrdenProduccion> observacionesRegistradas;
	private List<ObservacionesOrdenProduccion> nuevasObservaciones;
	private List<ObservacionesOrdenProduccion> observacionesPorEliminar;

	@EJB
	private OrdenProduccionFacadeLocal ordenProduccionEJB;
	private OrdenProduccion ordenProduccion;
	private int idOP;

	@PostConstruct
	public void init() {
		observacionesOP = new ObservacionesOrdenProduccion();
		ordenProduccion = new OrdenProduccion();
		obtenerDatosOP();
		System.out.println("Parámetro: " + idOP);
	}

	public void obtenerDatosOP(/*int op*/) {
		//System.out.println("Parámetro: "+getIdOP());
		ordenProduccion = ordenProduccionEJB.find(1);
		observacionesRegistradas = ordenProduccionEJB.observacionesOP(ordenProduccion);
	}

	public void agregarObservacion() {
		System.out.println("");
		System.out.println("agregar");
		observacionesRegistradas.add(observacionesOP);
		snackbarData.put("message", "proceso agregado.");
		RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		System.out.println("");
	}

	public void registrarObservacion() {
		try {
			for(ObservacionesOrdenProduccion itemRegistro : observacionesRegistradas){
				observacionesOPEJB.create(itemRegistro);
			}
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	/*public void eliminarObservacion() {
		try {
			for(ObservacionesOrdenProduccion itemEliminar : observacionesPorEliminar){
				observacionesOPEJB.remove(itemEliminar);
			}
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void actualizarObservacion() {
		try {
			for(ObservacionesOrdenProduccion itemRegistro : observacionesRegistradas){
				observacionesOPEJB.edit(itemRegistro);
			}
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}*/

	public void actualizarOrden() {
		try {
			System.out.println("Modificando OP");
			ordenProduccionEJB.edit(ordenProduccion);
			System.out.println("Agregando/modificando observaciones");
			registrarObservacion();
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("./");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	//Getters & Setters
	public ObservacionesOrdenProduccion getObservacionesOP() {
		return observacionesOP;
	}

	public void setObservacionesOP(ObservacionesOrdenProduccion observacionesOP) {
		this.observacionesOP = observacionesOP;
	}

	public List<ObservacionesOrdenProduccion> getObservacionesRegistradas() {
		return observacionesRegistradas;
	}

	public void setObservacionesRegistradas(List<ObservacionesOrdenProduccion> observacionesRegistradas) {
		this.observacionesRegistradas = observacionesRegistradas;
	}

	public OrdenProduccion getOrdenProduccion() {
		return ordenProduccion;
	}

	public void setOrdenProduccion(OrdenProduccion ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	public int getIdOP() {
		return idOP;
	}

	public void setIdOP(int idOP) {
		System.out.println("Seteando parámetro");
		this.idOP = idOP;
		System.out.println("Parámetro: " + getIdOP());
	}
}
