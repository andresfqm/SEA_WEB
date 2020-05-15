/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Cargo;
import com.sea.backend.entities.CargoPerfil;
import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Departamento;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Perfil;
import com.sea.backend.entities.Telefono;
import com.sea.backend.entities.TipoDireccion;
import com.sea.backend.entities.TipoDocumento;
import com.sea.backend.entities.TipoEmail;
import com.sea.backend.entities.TipoTelefono;
import com.sea.backend.entities.Usuario;
import com.sea.backend.model.CargoFacadeLocal;
import com.sea.backend.model.CargoPerfilFacadeLocal;
import com.sea.backend.model.CiudadFacadeLocal;
import com.sea.backend.model.DepartamentoFacadeLocal;
import com.sea.backend.model.DireccionFacadeLocal;
import com.sea.backend.model.EmailFacadeLocal;
import com.sea.backend.model.PerfilFacadeLocal;
import com.sea.backend.model.TelefonoFacadeLocal;
import com.sea.backend.model.TipoDireccionFacadeLocal;
import com.sea.backend.model.TipoDocumentoFacadeLocal;
import com.sea.backend.model.TipoEmailFacadeLocal;
import com.sea.backend.model.TipoTelefonoFacadeLocal;
import com.sea.backend.model.UsuarioFacadeLocal;
import com.sea.backend.util.AbrirCerrarDialogos;
import com.sea.backend.util.Encriptacion;
import com.sea.backend.util.Validaciones;
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
 * @author homero
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();
	// Variables locales
	private boolean renderizarEditar = false;
	private boolean renderizarCrear = false;
	private String tituloDialogo;
	private String contraseñaActual;
	private String confirmacionPass;

	@EJB
	private UsuarioFacadeLocal usuarioEJB;

	private Usuario usuario;
	private Usuario usuarioCambioPass;
	private List<Usuario> listaUsuarios;
	private List<Usuario> lista;
	private String accion;
	private String limpieza = "";

	private String ConfirmaciónPassword;

	@EJB
	private TelefonoFacadeLocal telefonoEJB;
	private Telefono telefono;

	@EJB
	private TipoTelefonoFacadeLocal tipoTelefonoEJB;
	private TipoTelefono tipoTelefono;

	@EJB
	private TipoEmailFacadeLocal tipoEmailEJB;
	private TipoEmail tipoEmail;

	@EJB
	private TipoDireccionFacadeLocal tipoDireccionEJB;
	private TipoDireccion tipoDireccion;

	@EJB
	private EmailFacadeLocal correoEJB;
	private Email correo;

	@EJB
	private CargoFacadeLocal CargoEJB;
	private Cargo cargo;
	private List<Cargo> listaCargos;

	@EJB
	private CargoPerfilFacadeLocal cargoPerfilEJB;
	private CargoPerfil cargoPerfil;

	@EJB
	private PerfilFacadeLocal perfilEJB;
	private Perfil perfilt;

	@EJB
	private DireccionFacadeLocal direccionEJB;
	private Direccion direccion;

	@EJB
	private DepartamentoFacadeLocal departamentoEJB;
	private Departamento departamento;

	@EJB
	private CiudadFacadeLocal ciudadEJB;
	private Ciudad ciudad;
	private List<Ciudad> ciudades;

	@EJB
	private TipoDocumentoFacadeLocal tipoDocumentoEJB;
	private TipoDocumento tipoDocumento;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarioCambioPass = new Usuario();
		telefono = new Telefono();
		correo = new Email();
		perfilt = new Perfil();
		lista = usuarioEJB.listaUsuario();
		direccion = new Direccion();
		tipoTelefono = new TipoTelefono();
		tipoDireccion = new TipoDireccion();
		tipoEmail = new TipoEmail();
		tipoDocumento = new TipoDocumento();
		departamento = new Departamento();
		ciudad = new Ciudad();
		ciudades = ciudadEJB.findAll();
		cargo = new Cargo();
		listaCargos = CargoEJB.findAll();
		cargoPerfil = new CargoPerfil();
	}

	public void registrar() {
		try {

			String validacionCodigo = usuarioEJB.buscarIdInterno(usuario.getIdInterno().trim());

			boolean validacionCorreo = Validaciones.validarEmail(correo.getEmail());

			if (validacionCodigo != null) {
				snackbarData.put("message", "El id Interno '" + validacionCodigo + " " + "Ya existe en el sistema");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
			} else if (!(ConfirmaciónPassword.equals(usuario.getContrasena()))) {
				snackbarData.put("message", "La contraseña y la confirmación no son iguales");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			} else if (validacionCorreo == false) {
				snackbarData.put("message", "El correo ingresado no es valido");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			} else {

				usuario.setTblTipoDocumentoIdTipoDocumento(tipoDocumentoEJB.find(tipoDocumento.getIdTipoDocumento()));
				usuario.setTblCargoIdCargo(cargo);
				String cadenaEncriptada = Encriptacion.Encriptar(usuario.getContrasena().trim());
				usuario.setContrasena(cadenaEncriptada);
				usuarioEJB.create(usuario);
				telefono.setTblTipoTelefonoIdTipoTelefono(tipoTelefonoEJB.find(tipoTelefono.getIdTipoTelefono()));
				telefono.setTblUsuarioIdUsuario(usuarioEJB.find(usuario.getIdUsuario()));
				telefonoEJB.create(telefono);
				correo.setTblTipoEmailIdTipoEmail(tipoEmailEJB.find(tipoEmail.getIdTipoEmail()));
				correo.setTblUsuarioIdUsuario(usuarioEJB.find(usuario.getIdUsuario()));
				correoEJB.create(correo);
				direccion.setTblTipoDireccionIdTipoDireccion(tipoDireccionEJB.find(tipoDireccion.getIdTipoDireccion()));
				direccion.setTblUsuarioIdUsuario(usuarioEJB.find(usuario.getIdUsuario()));
				direccion.setTblCiudadIdCiudad(ciudadEJB.listaCiudad(ciudad.getNombre()));
				direccionEJB.create(direccion);
				snackbarData.put("message", "Se registró al usuario '" + usuario.getNombre() + " " + usuario.getApellido() + "'.");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
				AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg1').hide();");
				usuario = new Usuario();
				telefono = new Telefono();
				correo = new Email();
				direccion = new Direccion();
			}
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
		lista = usuarioEJB.listaUsuario();
	}

	public void obtenerCiudad() {
		ciudades = ciudadEJB.listaCiudades(departamento);
	}

	public void modificar() {
		try {
			//String validacionCodigo = usuarioEJB.buscarIdInterno(usuario.getIdInterno().trim());

			boolean validacionCorreo = Validaciones.validarEmail(correo.getEmail());

			if (!(ConfirmaciónPassword.equals(usuario.getContrasena()))) {
				snackbarData.put("message", "La contraseña y la confirmación no son iguales");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			} else if (validacionCorreo == false) {
				snackbarData.put("message", "El correo ingresado no es valido");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			} else {

				getAccion();
				usuarioEJB.edit(usuario);
				snackbarData.put("message", "Se modificó al usuario '" + usuario.getNombre() + " " + usuario.getApellido() + "'.");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
				AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg1').hide();");
			}
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}

	}

	public void eliminar(Usuario usuario) {
		try {
			getAccion();
			usuarioEJB.remove(usuario);
			snackbarData.put("message", "Se eliminó al usuario '" + usuario.getNombre() + " " + usuario.getApellido() + "'.");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}

	}

	public void lista() {
		lista = usuarioEJB.listaUsuario();
	}

	public void leerID(int usuario) throws Exception {
		this.usuario = usuarioEJB.find(usuario);
		this.direccion = usuarioEJB.actualizarCiudad(usuarioEJB.find(usuario));
		setAccion("Modificar");
		renderizarEditar = true;
		renderizarCrear = false;
		tituloDialogo = "Modificar Usuario";
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg1').show();");
	}

	public void cambiarContraseña() {
		if (usuarioCambioPass.getContrasena().equals(confirmacionPass)) {
			String verificarContraseñaActual = Encriptacion.Encriptar(contraseñaActual);
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if (verificarContraseñaActual.equals(us.getContrasena())) {
				us.setContrasena(Encriptacion.Encriptar(confirmacionPass));
				usuarioEJB.edit(us);
				snackbarData.put("message", "Cambio de contraseña exitoso");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

				usuarioCambioPass = new Usuario();
				confirmacionPass = "";

			} else {
				snackbarData.put("message", "La contraseña actual no es correcta");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			}
		} else {
			snackbarData.put("message", "La nueva contraseña y la confirmación no son iguales");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		}
	}

	public void abrirDialogoCreacionUsuarios() {
		renderizarEditar = false;
		renderizarCrear = true;
		limpiar();
		tituloDialogo = "Registrar Usuario";
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg1').show();");
	}

	public void limpiar() {
		usuario = new Usuario();
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg1').hide();");
		usuario.setNumeroDocumento(limpieza);
		usuario.setNombre(limpieza);
		usuario.setApellido(limpieza);
		telefono.setNumeroTelefono(limpieza);
		direccion.setDireccion(limpieza);

	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<Cargo> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public CargoPerfil getCargoPerfil() {
		return cargoPerfil;
	}

	public void setCargoPerfil(CargoPerfil cargoPerfil) {
		this.cargoPerfil = cargoPerfil;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Email getCorreo() {
		return correo;
	}

	public void setCorreo(Email correo) {
		this.correo = correo;
	}

	public String getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(String limpieza) {
		this.limpieza = limpieza;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = usuarioEJB.findAll();
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoTelefono getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(TipoTelefono tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public TipoDireccion getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Perfil getPerfilt() {
		return perfilt;
	}

	public void setPerfilt(Perfil perfilt) {
		this.perfilt = perfilt;
	}

	public String getConfirmaciónPassword() {
		return ConfirmaciónPassword;
	}

	public void setConfirmaciónPassword(String ConfirmaciónPassword) {
		this.ConfirmaciónPassword = ConfirmaciónPassword;
	}

	public boolean isRenderizarEditar() {
		return renderizarEditar;
	}

	public void setRenderizarEditar(boolean renderizarEditar) {
		this.renderizarEditar = renderizarEditar;
	}

	public boolean isRenderizarCrear() {
		return renderizarCrear;
	}

	public void setRenderizarCrear(boolean renderizarCrear) {
		this.renderizarCrear = renderizarCrear;
	}

	public String getTituloDialogo() {
		return tituloDialogo;
	}

	public void setTituloDialogo(String tituloDialogo) {
		this.tituloDialogo = tituloDialogo;
	}

	public String getConfirmacionPass() {
		return confirmacionPass;
	}

	public void setConfirmacionPass(String confirmacionPass) {
		this.confirmacionPass = confirmacionPass;
	}

	public String getContraseñaActual() {
		return contraseñaActual;
	}

	public void setContraseñaActual(String contraseñaActual) {
		this.contraseñaActual = contraseñaActual;
	}

	public Usuario getUsuarioCambioPass() {
		return usuarioCambioPass;
	}

	public void setUsuarioCambioPass(Usuario usuarioCambioPass) {
		this.usuarioCambioPass = usuarioCambioPass;
	}

}
