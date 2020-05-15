/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Email;
import com.sea.backend.entities.Usuario;
import com.sea.backend.entities.ViewPaginasUsuario;
import com.sea.backend.model.PaginaFacadeLocal;
import com.sea.backend.model.UsuarioFacadeLocal;
import com.sea.backend.util.Encriptacion;
import com.sea.backend.util.EnvioEmails;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Depurador
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

	@EJB
	private PaginaFacadeLocal PaginaEJB;

	@EJB
	private UsuarioFacadeLocal EJBUsuario;
	private Usuario usuario;

	private List<ViewPaginasUsuario> paginasPermitidas;

	//Variable de los dialogos
	JSONObject dialogData = new JSONObject();

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public void iniciarSesion() {
		Usuario us;
		try {
			String encriptarContraseña = Encriptacion.Encriptar(usuario.getContrasena());
			usuario.setContrasena(encriptarContraseña);
			us = EJBUsuario.iniciarSesion(usuario);
			if (us != null) {
				if (us.getHabilitado()) {
					//Almacenar la sesión de JSF
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("/SEA/index.xhtml");
				} else {
					dialogData.put("titulo", "Error al iniciar sesión.");
					dialogData.put("mensaje", "Usted no se encuentra activo en el sistema.<br />Por favor, comuníquese con el administrador del sistema.");
					RequestContext.getCurrentInstance().execute("mostrarDialogos(" + dialogData + ");");
				}
			} else {
				dialogData.put("titulo", "Error al iniciar sesión");
				dialogData.put("mensaje", "El nombre de usuario y/o la contraseña son incorrectos.");
				RequestContext.getCurrentInstance().execute("mostrarDialogos(" + dialogData + ");");
			}
		} catch (Exception e) {
			dialogData.put("titulo", "Error no controlado");
			dialogData.put("mensaje", e.getMessage());
			RequestContext.getCurrentInstance().execute("mostrarDialogos(" + dialogData + ");");
		}
	}

	// Capturando el nombre del usuario que inicia sesión
	public String mostrarNombreUsuario() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		return u.getNombre() + " " + u.getApellido();
	}

	public String mostrarCargo() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		return u.getTblCargoIdCargo().getCargo();
	}

	public void verificarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if (us == null) {
				context.getExternalContext().redirect("/SEA/auth");
			}
		} catch (Exception e) {
			// log para guardar un registro de error
		}
	}

	public void verificarSesionLogin() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if (us != null) {
				context.getExternalContext().redirect("/SEA/index.xhtml");
			}
		} catch (Exception e) {
			// log para guardar un registro de error
		}

	}

	public void verificarPermisos() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if (us != null) {
				paginasPermitidas = PaginaEJB.obtenerPagianasPermitidas(us.getIdUsuario());
				String paginaActual = ((HttpServletRequest) FacesContext.getCurrentInstance()
						.getExternalContext().getRequest()).getRequestURI();
				System.out.println("Página actal: " + paginaActual);
				for (ViewPaginasUsuario item : paginasPermitidas) {
					/*if(paginaActual.in){
						
					}*/
				}
			}
		} catch (Exception e) {
			// log para guardar un registro de error
		}
	}

	public void cerrarSesion() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		context.getExternalContext().redirect("");
	}

	public void recuperarContraseña() {
		Usuario usuarioObtenido = new Usuario();
		try {
			usuarioObtenido = EJBUsuario.recuperarContraseña(usuario);
			if (usuarioObtenido != null) {
				for (Email email : usuarioObtenido.getEmailList()) {
					FacesContext context = FacesContext.getCurrentInstance();
					EnvioEmails enEmail = new EnvioEmails();
					String contraseñaGenerica = Encriptacion.Encriptar("Sea123*");
					enEmail.envioEmailRecuperacionPass(email.getEmail(), "Sea123*");
					usuarioObtenido.setContrasena(contraseñaGenerica);
					EJBUsuario.edit(usuarioObtenido);
					dialogData.put("titulo", "Recuperación de contraseña");
					dialogData.put("mensaje", "Se envio los datos de conexión al correo electronico registrado en el sistema");
					RequestContext.getCurrentInstance().execute("mostrarDialogos(" + dialogData + ");");
					context.getExternalContext().redirect("/SEA/index.xhtml");
					break;
				}
			} else {
				dialogData.put("titulo", "Recuperación de contraseña");
				dialogData.put("mensaje", "Los datos ingresados no se encuentran registrados en el sistema, por favo valide los datos");
				RequestContext.getCurrentInstance().execute("mostrarDialogos(" + dialogData + ");");
			}

		} catch (Exception e) {
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
