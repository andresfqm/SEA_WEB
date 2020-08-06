/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.ViewIndexCotizacionesActivas;
import com.sea.backend.entities.ViewArticulosPorActualizar;
import com.sea.backend.entities.ViewIndexOpPorGenerar;
import com.sea.backend.entities.ViewOpEnSeguimiento;
import com.sea.backend.entities.ViewOpPorEstado;
import com.sea.backend.entities.Usuario;
import com.sea.backend.model.CotizacionFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Depurador
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;

	@EJB
	private CotizacionFacadeLocal cotizacionEJB;
	private List<ViewIndexCotizacionesActivas> listaSeguimientoCotizaciones;
	private List<ViewArticulosPorActualizar> listaArticulosPorActualizar;
	private List<ViewIndexOpPorGenerar> listaOpPorGenerar;
	private List<ViewIndexOpPorGenerar> listaOpPorGenerarCorregir;
	private List<ViewOpEnSeguimiento> listaOpEnSeguimiento;
	private List<ViewOpPorEstado> listaOpPorCorregir;
	private List<ViewOpPorEstado> listaOpPorAprobar;

	@PostConstruct
	public void init() {
		listaSeguimientoCotizaciones = cotizacionEJB.IndexSeguimientoCotizacion(idUsuario());
		listaArticulosPorActualizar = cotizacionEJB.IndexArticulosPorActualizar();
		listaOpPorGenerar = cotizacionEJB.IndexOpPorGenerar(idUsuario());
		
		listaOpPorGenerarCorregir = listaOpPorGenerar;
		List<ViewIndexOpPorGenerar> listaOpPorGenerarAux = new ArrayList<>();
		
		for (ViewIndexOpPorGenerar ls : listaOpPorGenerar) {
			if (ls.getEstado().equalsIgnoreCase("Cierre efectivo")) {
				listaOpPorGenerarAux.add(ls);
			}
			
		}
		
		listaOpPorGenerar = listaOpPorGenerarAux;
		
		listaOpEnSeguimiento = cotizacionEJB.IndexOpEnSeguimiento(idUsuario());
			listaOpPorCorregir = cotizacionEJB.IndexOpPorEstado(idUsuario(), "Necesita corrección");
	}

	public void obtenerCotizacionesRegistradas() throws Exception {
		try {
			listaSeguimientoCotizaciones = cotizacionEJB.IndexSeguimientoCotizacion(idUsuario());
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void obtenerArticulosPorActualizar() throws Exception {
		try {
			listaArticulosPorActualizar = cotizacionEJB.IndexArticulosPorActualizar();
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void obtenerOpPorGenerar() throws Exception {
		try {
			listaOpPorGenerar = cotizacionEJB.IndexOpPorGenerar(idUsuario());
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void obtenerOpEnSeguimiento() throws Exception {
		try {
			listaOpEnSeguimiento = cotizacionEJB.IndexOpEnSeguimiento(idUsuario());
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void obtenerOpPorCorregir() throws Exception {
		try {
			listaOpPorCorregir = cotizacionEJB.IndexOpPorEstado(idUsuario(), "Necesita corrección");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void obtenerOpPorAprobar() throws Exception {
		try {
			listaOpPorAprobar = cotizacionEJB.IndexOpPorEstado(idUsuario(), "Pendiente");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public int idUsuario() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		return u.getIdUsuario();
	}

	public List<ViewIndexCotizacionesActivas> getListaSeguimientoCotizaciones() {
		return listaSeguimientoCotizaciones;
	}

	public void setListaSeguimientoCotizaciones(List<ViewIndexCotizacionesActivas> listaSeguimientoCotizaciones) {
		this.listaSeguimientoCotizaciones = listaSeguimientoCotizaciones;
	}

	public List<ViewArticulosPorActualizar> getListaArticulosPorActualizar() {
		return listaArticulosPorActualizar;
	}

	public void setListaArticulosPorActualizar(List<ViewArticulosPorActualizar> listaArticulosPorActualizar) {
		this.listaArticulosPorActualizar = listaArticulosPorActualizar;
	}

	public List<ViewIndexOpPorGenerar> getListaOpPorGenerar() {
		return listaOpPorGenerar;
	}

	public void setListaOpPorGenerar(List<ViewIndexOpPorGenerar> listaOpPorGenerar) {
		this.listaOpPorGenerar = listaOpPorGenerar;
	}

	public List<ViewOpEnSeguimiento> getListaOpEnSeguimiento() {
		return listaOpEnSeguimiento;
	}

	public void setListaOpEnSeguimiento(List<ViewOpEnSeguimiento> listaOpEnSeguimiento) {
		this.listaOpEnSeguimiento = listaOpEnSeguimiento;
	}

	public List<ViewOpPorEstado> getListaOpPorCorregir() {
		return listaOpPorCorregir;
	}

	public void setListaOpPorCorregir(List<ViewOpPorEstado> listaOpPorCorregir) {
		this.listaOpPorCorregir = listaOpPorCorregir;
	}

	public List<ViewOpPorEstado> getListaOpPorAprobar() {
		return listaOpPorAprobar;
	}

	public void setListaOpPorAprobar(List<ViewOpPorEstado> listaOpPorAprobar) {
		this.listaOpPorAprobar = listaOpPorAprobar;
	}

	public List<ViewIndexOpPorGenerar> getListaOpPorGenerarCorregir() {
		return listaOpPorGenerarCorregir;
	}

	public void setListaOpPorGenerarCorregir(List<ViewIndexOpPorGenerar> listaOpPorGenerarCorregir) {
		this.listaOpPorGenerarCorregir = listaOpPorGenerarCorregir;
	}

}
