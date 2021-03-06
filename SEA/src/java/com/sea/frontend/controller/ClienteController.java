package com.sea.frontend.controller;

import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Cliente;
import com.sea.backend.entities.Departamento;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Origen;
import com.sea.backend.entities.Telefono;
import com.sea.backend.entities.TipoDireccion;
import com.sea.backend.entities.TipoDocumento;
import com.sea.backend.entities.TipoEmail;
import com.sea.backend.entities.TipoTelefono;
import com.sea.backend.entities.Usuario;
import com.sea.backend.model.CiudadFacadeLocal;
import com.sea.backend.model.ClienteFacadeLocal;
import com.sea.backend.model.DepartamentoFacadeLocal;
import com.sea.backend.model.DireccionFacadeLocal;
import com.sea.backend.model.EmailFacadeLocal;
import com.sea.backend.model.OrigenFacadeLocal;
import com.sea.backend.model.TelefonoFacadeLocal;
import com.sea.backend.model.TipoDireccionFacadeLocal;
import com.sea.backend.model.TipoDocumentoFacadeLocal;
import com.sea.backend.model.TipoEmailFacadeLocal;
import com.sea.backend.model.TipoTelefonoFacadeLocal;
import com.sea.backend.model.UsuarioFacadeLocal;
import com.sea.backend.util.AbrirCerrarDialogos;
import com.sea.backend.util.EnvioEmails;
import com.sea.backend.util.Validaciones;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();

	@EJB
	private ClienteFacadeLocal clienteEJB;
	private Cliente cliente;
	private List<Cliente> listaClientes;

	@EJB
	private UsuarioFacadeLocal usuarioEJB;
	private Usuario usuario;
	private List<Usuario> listaUsuario;

	@EJB
	private TipoTelefonoFacadeLocal tipoTelefonoEJB;
	private TipoTelefono tipoTelefono;
	private List<TipoTelefono> listaTipoTelefono;

	@EJB
	private TelefonoFacadeLocal telefonoEJB;
	private Telefono telefono;
	private List<Telefono> listaTelefono;
	private List<Telefono> addTelefono;
	private List<Telefono> modTelefono;

	@EJB
	private TipoDocumentoFacadeLocal tipoDocumentoEJB;
	private TipoDocumento tipoDocumento;
	private List<TipoDocumento> listaTipoDocumento;

	@EJB
	private TipoEmailFacadeLocal tipoEmailEJB;
	private TipoEmail tipoEmail;
	private List<TipoEmail> listaTipoEmail;
	private List<Email> addEmail;
	private List<Email> modEmail;

	@EJB
	private TipoDireccionFacadeLocal tipoDireccionEJB;
	private TipoDireccion tipoDireccion;
	private List<TipoDireccion> listaTipoDireccion;

	@EJB
	private EmailFacadeLocal emailEJB;
	private Email email;
	private List<Email> listaEmail;

	@EJB
	private DireccionFacadeLocal direccionEJB;
	private Direccion direccion;
	private List<Direccion> listaDireccion;

	@EJB
	private CiudadFacadeLocal ciudadEJB;
	private Ciudad ciudad;
	private List<Ciudad> listaCiudad;

	@EJB
	private DepartamentoFacadeLocal departamentoEJB;
	private Departamento departamento;
	private List<Departamento> listaDepartamento;

	@EJB
	private OrigenFacadeLocal origenEJB;
	private Origen origen;
	private List<Origen> listaOrigen;

	private final static Logger log = Logger.getLogger(ClienteController.class);

	private int numMaxNit = 9;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		listaClientes = clienteEJB.listaClientes();
		usuario = new Usuario();
		listaUsuario = usuarioEJB.findAll();
		tipoTelefono = new TipoTelefono();
		tipoDocumento = new TipoDocumento();
		listaTipoTelefono = new ArrayList<>();
		listaTipoDocumento = tipoDocumentoEJB.findAll();
		tipoEmail = new TipoEmail();
		listaTipoEmail = new ArrayList<>();
		direccion = new Direccion();
		listaDireccion = direccionEJB.findAll();
		ciudad = new Ciudad();
		listaCiudad = ciudadEJB.findAll();
		email = new Email();
		listaEmail = emailEJB.findAll();
		telefono = new Telefono();
		listaTelefono = telefonoEJB.findAll();
		origen = new Origen();
		listaOrigen = origenEJB.findAll();
		departamento = new Departamento();
		listaDepartamento = departamentoEJB.findAll();
		tipoDireccion = new TipoDireccion();
		listaTipoDireccion = tipoDireccionEJB.findAll();
		addTelefono = new ArrayList<>();
		addEmail = new ArrayList<>();
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirDialogoTelefono() {
		listaTipoTelefono = tipoTelefonoEJB.findAll();
		if (addTelefono.size() == 2) {
			snackbarData.put("message", "Solo se permite registrar 2 números de télefonos por cliente");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('agregarTelefono').show();");
		}

	}

	public void agregarTelefono() {

		telefono.setTblTipoTelefonoIdTipoTelefono(tipoTelefono);
		addTelefono.add(telefono);
		telefono = new Telefono();
		tipoTelefono = new TipoTelefono();
		listaTipoTelefono = new ArrayList<>();
		snackbarData.put("message", "Se agrego el número de teléfono");
		RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('agregarTelefono').hide();");
	}

	public void abrirDialogoEmail() {
		listaTipoEmail = tipoEmailEJB.findAll();
		if (addEmail.size() == 2) {
			snackbarData.put("message", "Solo se permite registrar 2 correos por cliente");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('agregarEmail').show();");
		}
	}

	public void agregarEmail() {

		boolean validacionEmail = Validaciones.validarEmail(email.getEmail());
		if (validacionEmail == true) {
			email.setTblTipoEmailIdTipoEmail(tipoEmail);
			addEmail.add(email);
			email = new Email();
			tipoEmail = new TipoEmail();
			listaTipoEmail = new ArrayList<>();
			snackbarData.put("message", "Se agrego email");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('agregarEmail').hide();");
		} else {
			snackbarData.put("message", "El correo ingresado no es valido");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		}

	}

	public void registrarCliente() {
		try {
			boolean validacionCliente = clienteEJB.consultarRegistroIdentificacion(cliente.getNumeroDocumento());

			if (validacionCliente == true) {
				snackbarData.put("message", "El número de documento ingresado ya se encuentra registrado'" + cliente.getNombreORazonSocial() + " " + cliente.getApellido() + "'.");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			} else {
				cliente.setTblTipoDocumentoIdTipoDocumento(tipoDocumentoEJB.find(tipoDocumento.getIdTipoDocumento()));
				cliente.setTblUsuarioIdUsuario(usuarioEJB.find(usuario.getIdUsuario()));
				cliente.setTblOrigenIdOrigen(origenEJB.find(origen.getIdOrigen()));
				cliente.setActivo(true);
				clienteEJB.create(cliente);
				direccion.setTblTipoDireccionIdTipoDireccion(tipoDireccion);
				direccion.setTblClienteIdCliente(cliente);
				direccion.setTblCiudadIdCiudad(ciudadEJB.listaCiudad(ciudad.getNombre()));
				direccionEJB.create(direccion);

				for (Telefono tel : addTelefono) {
					tel.setTblClienteIdCliente(cliente);
					telefonoEJB.create(tel);
				}
				addTelefono = new ArrayList<>();

				for (Email ema : addEmail) {
					ema.setTblClienteIdCliente(cliente);
					emailEJB.create(ema);
				}

				snackbarData.put("message", "Se creó al cliente'" + cliente.getNombreORazonSocial() + " " + cliente.getApellido() + "'.");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

				EnvioEmails envioEmails = new EnvioEmails();
				cliente.setEmailList(addEmail);
				String respuesta = envioEmails.enviarEmailRegistroClientes(cliente);

				if (respuesta.equalsIgnoreCase("FALLO")) {
					dialogTittle = "Error no controlado";
					dialogContent = "Se presento un error al enviar el correo de notificación de creación cliente, por favor valide con el administrador del sistema";
					RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");

				}
				listaClientes = clienteEJB.listaClientes();
				AbrirCerrarDialogos.abrirCerrarDialogos("PF('createClient').hide();");
			}
			limpiar();
		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void leerID(int idCliente) {
		log.info("Ingreso al proceso de cargar los datos del cliente a modificar");
		try {
			cliente = clienteEJB.find(idCliente);
			//clienteEJB.eliminarCliente(idCliente);

			tipoDocumento.setIdTipoDocumento(this.cliente.getTblTipoDocumentoIdTipoDocumento().getIdTipoDocumento());
			//RequestContext.getCurrentInstance().update(":addUser:ciudad");
			modTelefono = clienteEJB.listaTelefonosCliente(cliente);
			modEmail = clienteEJB.listaEmailsCliente(cliente);
			direccion = direccionEJB.direccionCliente(cliente.getIdCliente());
			departamento.setIdDepartamento(direccion.getTblCiudadIdCiudad().getTblDepartamentoIdDepartamento().getIdDepartamento());
			obtenerCiudad();
			ciudad.setIdCiudad(this.direccion.getTblCiudadIdCiudad().getIdCiudad());
			ciudad.setNombre(this.direccion.getTblCiudadIdCiudad().getNombre());
			tipoDireccion.setIdTipoDireccion(this.direccion.getTblTipoDireccionIdTipoDireccion().getIdTipoDireccion());
			usuario.setIdUsuario(cliente.getTblUsuarioIdUsuario().getIdUsuario());
			render();
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgModificarCliente').show();");

		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}

	}

	public void editarTelefono(CellEditEvent event) {
		String oldValue = (String) event.getOldValue();
		String newValue = (String) event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			System.out.println("Se edito un valor ");
		}
	}

	public void editarEmail(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			System.out.println("Se edito un valor ");
		}
	}

	public void modificarCliente() {
		log.info("Ingreso al proceso de modificar los datos del cliente : " + cliente.getNombreORazonSocial());
		try {
			cliente.setTblTipoDocumentoIdTipoDocumento(tipoDocumentoEJB.find(tipoDocumento.getIdTipoDocumento()));
			cliente.setTblUsuarioIdUsuario(usuarioEJB.find(usuario.getIdUsuario()));
			clienteEJB.edit(cliente);

			for (Telefono tel : modTelefono) {
				telefonoEJB.edit(tel);
			}
			addTelefono = new ArrayList<>();

			for (Email ema : modEmail) {
				emailEJB.edit(ema);
			}
			addEmail = new ArrayList<>();

			direccion.setTblTipoDireccionIdTipoDireccion(tipoDireccion);
			direccion.setTblClienteIdCliente(cliente);
			direccion.setTblCiudadIdCiudad(ciudadEJB.listaCiudad(ciudad.getNombre()));
			direccionEJB.edit(direccion);
			listaClientes = clienteEJB.listaClientes();
			snackbarData.put("message", "Se edito el cliente" + cliente.getNombreORazonSocial() + " " + cliente.getApellido() + "'.");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

			limpiar();

			AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgModificarCliente').hide();");
		} catch (Exception e) {
			log.error("Se presento el siguiente error al modificar el cliente : " + cliente.getNombreORazonSocial());
			e.printStackTrace();
		}

	}

	public void limpiar() {
		cliente = new Cliente();
		departamento = new Departamento();
		tipoDireccion = new TipoDireccion();
		ciudad = new Ciudad();
		email = new Email();
		telefono = new Telefono();
		tipoTelefono = new TipoTelefono();
		tipoEmail = new TipoEmail();
		addEmail = new ArrayList<>();
		usuario = new Usuario();
		direccion = new Direccion();
		tipoDocumento = new TipoDocumento();
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgModificarCliente').hide();");
	}

	public void obtenerCiudad() {
		listaCiudad = ciudadEJB.listaCiudades(departamento);
	}

	public void render() {
		tipoDocumento = tipoDocumentoEJB.find(tipoDocumento.getIdTipoDocumento());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public TipoTelefono getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(TipoTelefono tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public List<TipoTelefono> getListaTipoTelefono() {
		return listaTipoTelefono;
	}

	public void setListaTipoTelefono(List<TipoTelefono> listaTipoTelefono) {
		this.listaTipoTelefono = listaTipoTelefono;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public List<TipoEmail> getListaTipoEmail() {
		return listaTipoEmail;
	}

	public void setListaTipoEmail(List<TipoEmail> listaTipoEmail) {
		this.listaTipoEmail = listaTipoEmail;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Direccion> getListaDireccion() {
		return listaDireccion;
	}

	public void setListaDireccion(List<Direccion> listaDireccion) {
		this.listaDireccion = listaDireccion;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Ciudad> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<Ciudad> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public List<Telefono> getListaTelefono() {
		return listaTelefono;
	}

	public void setListaTelefono(List<Telefono> listaTelefono) {
		this.listaTelefono = listaTelefono;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public List<Email> getListaEmail() {
		return listaEmail;
	}

	public void setListaEmail(List<Email> listaEmail) {
		this.listaEmail = listaEmail;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public List<Origen> getListaOrigen() {
		return listaOrigen;
	}

	public void setListaOrigen(List<Origen> listaOrigen) {
		this.listaOrigen = listaOrigen;
	}

	public TipoDireccion getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public List<TipoDireccion> getListaTipoDireccion() {
		return listaTipoDireccion;
	}

	public void setListaTipoDireccion(List<TipoDireccion> listaTipoDireccion) {
		this.listaTipoDireccion = listaTipoDireccion;
	}

	public List<Telefono> getAddTelefono() {
		return addTelefono;
	}

	public void setAddTelefono(List<Telefono> addTelefono) {
		this.addTelefono = addTelefono;
	}

	public List<Email> getAddEmail() {
		return addEmail;
	}

	public void setAddEmail(List<Email> addEmail) {
		this.addEmail = addEmail;
	}

	public int getNumMaxNit() {
		return numMaxNit;
	}

	public void setNumMaxNit(int numMaxNit) {
		this.numMaxNit = numMaxNit;
	}

	public List<Telefono> getModTelefono() {
		return modTelefono;
	}

	public void setModTelefono(List<Telefono> modTelefono) {
		this.modTelefono = modTelefono;
	}

	public List<Email> getModEmail() {
		return modEmail;
	}

	public void setModEmail(List<Email> modEmail) {
		this.modEmail = modEmail;
	}

}
