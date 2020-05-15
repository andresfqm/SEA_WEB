/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Cliente;
import com.sea.backend.entities.Cotizacion;
import com.sea.backend.entities.CotizacionProducto;
import com.sea.backend.entities.DescuentoVolumen;
import com.sea.backend.entities.Fabricante;
import com.sea.backend.entities.LugaresEntrega;
import com.sea.backend.entities.Material;
import com.sea.backend.entities.ModalidadDePago;
import com.sea.backend.entities.Producto;
import com.sea.backend.entities.PropuestaNoIncluye;
import com.sea.backend.entities.TiempoEntrega;
import com.sea.backend.entities.Usuario;
import com.sea.backend.model.CiudadFacadeLocal;
import com.sea.backend.model.ClienteFacadeLocal;
import com.sea.backend.model.CotizacionFacadeLocal;
import com.sea.backend.model.CotizacionProductoFacadeLocal;
import com.sea.backend.model.DescuentoFacadeLocal;
import com.sea.backend.model.DescuentoVolumenFacadeLocal;
import com.sea.backend.model.FabricanteFacadeLocal;
import com.sea.backend.model.LugaresEntregaFacadeLocal;
import com.sea.backend.model.MaterialFacadeLocal;
import com.sea.backend.model.ModalidadDePagoFacadeLocal;
import com.sea.backend.model.ProductoFacadeLocal;
import com.sea.backend.model.PropuestaNoIncluyeFacadeLocal;
import com.sea.backend.model.TiempoEntregaFacadeLocal;
import com.sea.backend.model.UsuarioFacadeLocal;
import com.sea.backend.util.EnvioEmails;
import com.sea.backend.util.GenerarDocumentos;
import com.sea.frontend.servlet.PDF;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class CotizacionController implements Serializable {

	//Variables de los dialogos y snackbars
	String dialogTittle = null;
	String dialogContent = null;
	JSONObject snackbarData = new JSONObject();
	//EJB cotización
	@EJB
	private CotizacionFacadeLocal cotizacionEJB;
	private Cotizacion cotizacion;
	private Double descuentoCotizacion;
	private List<Cotizacion> listaSeguimientoCotizacions;

	@EJB
	private UsuarioFacadeLocal EJBUsuario;
	private Usuario usuario;

	//EJB cliente
	@EJB
	private ClienteFacadeLocal clienteEJB;
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private Object datosCliente;
	private int idCliente;
	private int idModalidad;
	private List<Cliente> clientes;

	//EJB CotizaciónProducto
	@EJB
	private CotizacionProductoFacadeLocal cotizacionProductoEJB;
	private CotizacionProducto cotizacionProducto;
	private List<CotizacionProducto> listaCotizacionP;
	private int cantidad;
	private Float precioParaCliente;
	private double precioDescuento;

	@EJB
	private CotizacionProductoFacadeLocal cotizacionpEJB;
	private CotizacionProducto cotizacionP;

	@EJB
	private MaterialFacadeLocal materialEJB;
	@EJB
	private FabricanteFacadeLocal fabricanteEJB;

	//Ejb de las foraneas, ejb de ciudadEmision
	@EJB
	private CiudadFacadeLocal ciudadEJB;
	private Ciudad ciudad;
	private List<Ciudad> ciudades;

	//EJB Propuesta no incluye
	@EJB
	private PropuestaNoIncluyeFacadeLocal propuestaEJB;
	//private int propuestaNoIncluye;
	private List<PropuestaNoIncluye> ListapropuestaNoIncluye;
	private int idPropuestaNoIncluye;
	private PropuestaNoIncluye propuestaNoIncluye;

	public List<PropuestaNoIncluye> getListapropuestaNoIncluye() {
		return ListapropuestaNoIncluye;
	}

	public void setListapropuestaNoIncluye(List<PropuestaNoIncluye> ListapropuestaNoIncluye) {
		this.ListapropuestaNoIncluye = ListapropuestaNoIncluye;
	}

	//Ejb de la foranea TiempoEntrega
	@EJB
	private TiempoEntregaFacadeLocal tiempoEJB;
	private int idTiempoEntrega;
	private TiempoEntrega tiempoEntrega;
	private List<TiempoEntrega> listaTiempoEntrega;

	//Ejb de la foranea DescuentoVolen
	@EJB
	private DescuentoVolumenFacadeLocal descuentoVEJB;
	private int idDescuentoVolumen;
	private DescuentoVolumen descuentoVolumen;
	private List<DescuentoVolumen> listaDescuentoVolumen;

	//EJB Modalidades de pago
	@EJB
	private ModalidadDePagoFacadeLocal modalidadPEJB;
	private int idModalidadDePago;
	private List<ModalidadDePago> listaModalidadDePago;
	private ModalidadDePago modalidadDePago;

	//EJB Lugares de entrega
	@EJB
	private LugaresEntregaFacadeLocal lugaresEEJB;
	private int idLugaresEntrega;
	private LugaresEntrega lugaresEntrega;
	private List<LugaresEntrega> listaLugaresEntrega;

	//Entidad producto
	@EJB
	private ProductoFacadeLocal productoEJB;
	private Producto producto;
	private int idProducto;
	private List<Material> listaMateriales;
	private List<Fabricante> listaFabricante;
	private List<Producto> listaProductoPrecio;
	private List<Producto> listaProducto;

	@EJB
	private DescuentoFacadeLocal descuentoEJB;
	private int idDescuento;

	private int formatoCotizacion;

	private String mensaje;

	private Date fechaEmision;

	@PostConstruct
	public void init() {
		cotizacion = new Cotizacion();
		cotizacion.setFechaEmision(new Date());
		fechaEmision = cotizacion.getFechaEmision();
		cotizacion.setValidezOferta(60);
		cotizacion.setDescuento(15);
		cotizacion.setIva(19);
		cotizacionP = new CotizacionProducto();
		clientes = clienteEJB.listaClienteCotizacion(setUsuarioLogueado());
		cliente = new Cliente();
		producto = new Producto();
		listaCotizacionP = new ArrayList<>();
		listaProducto = productoEJB.findAll();
		usuario = new Usuario();
		lugaresEntrega = new LugaresEntrega();
		tiempoEntrega = new TiempoEntrega();
		descuentoVolumen = new DescuentoVolumen();
		ListapropuestaNoIncluye = propuestaEJB.findAll();
		listaTiempoEntrega = tiempoEJB.findAll();
		listaLugaresEntrega = lugaresEEJB.findAll();
		listaDescuentoVolumen = descuentoVEJB.findAll();
		listaModalidadDePago = modalidadPEJB.findAll();
		listaSeguimientoCotizacions = cotizacionEJB.listaSeguimiento(idUsuario());
		propuestaNoIncluye = new PropuestaNoIncluye();
	}

	//Obteniendo todos los datos del cliente
	public void obtenerDatosCliente() throws Exception {
		try {

			datosCliente = clienteEJB.datosCliente(idCliente);
		} catch (Exception e) {
			throw e;
		}
	}

	public void agregarCotizacionProducto() {
		CotizacionProducto cot = new CotizacionProducto();
		cot.setTblProductoIdProducto(productoEJB.find(idProducto));
		cot.setCantidad(cotizacionP.getCantidad());
		cot.setPrecioParaCliente(cotizacionP.getPrecioParaCliente());
		//  ven.setTblProductoIdProducto(productoEJB.find(producto.getIdProducto()));
		listaCotizacionP.add(cot);
		snackbarData.put("message", "Se agregó el artículo con referencia '" + cot.getTblProductoIdProducto().getReferencia() + "'.");
		RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
	}

	//Metodo para calcular el precio del producto seleccionado
	public Double calcularPrecioProductoDescuento() {
		return this.listaProductoPrecio.get(0).getPrecio() - (this.listaProductoPrecio.get(0).getPrecio() * this.descuentoCotizacion);

	}

	public void registrarCotización()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		try {
			cotizacion.setNumeroCotizacion(generarIdCotizacion());
			cotizacion.setFechaEmision(cotizacion.getFechaEmision());
			cotizacion.setLugarEmision(cotizacion.getLugarEmision());
			cotizacion.setValidezOferta(cotizacion.getValidezOferta());
			cotizacion.setDescuento(cotizacion.getDescuento());
			cotizacion.setVisita(cotizacion.getVisita());
			cotizacion.setPrestamoMuestra(cotizacion.getPrestamoMuestra());
			cotizacion.setRelacionMuestra(cotizacion.getRelacionMuestra());
			cotizacion.setEstado("En seguimiento");
			//Se carga los objetos de las clases correspondientes a las llaves foraneas
			cotizacion.setTblClienteIdCliente(clienteEJB.find(idCliente));
			cotizacion.setTblUsuario(usuario);
			cotizacion.setTblModalidadDePagoIdModalidadDePago(modalidadPEJB.find(idModalidadDePago));
			cotizacion.setTblPropuestaNoIncluyeIdPropuestaNoIncluye(propuestaEJB.find(idPropuestaNoIncluye));
			cotizacion.setTblTiempoEntregaIdTiempoEntrega(tiempoEJB.find(idTiempoEntrega));
			cotizacion.setTblDescuentoVolumenIdDescuentoVolumen(descuentoVEJB.find(idDescuentoVolumen));
			cotizacion.setTblLugaresEntregaIdLugaresEntrega(lugaresEEJB.find(idLugaresEntrega));
			cotizacionEJB.create(cotizacion);
			for (CotizacionProducto itemVenta : listaCotizacionP) {
				cotizacionP.setTblCotizacionNumeroCotizacion(cotizacion);
				cotizacionP.setTblProductoIdProducto(itemVenta.getTblProductoIdProducto());
				cotizacionP.setPrecioBase(itemVenta.getPrecioBase());
				if (itemVenta.getPrecioParaCliente() == null) {
					cotizacionP.setPrecioParaCliente(itemVenta.getTblProductoIdProducto().getPrecio());
				} else {
					cotizacionP.setPrecioParaCliente(itemVenta.getPrecioParaCliente());
				}
				cotizacionP.setCantidad(itemVenta.getCantidad());
				cotizacionProductoEJB.create(cotizacionP);
			}

			GenerarDocumentos generarDocumentos = new GenerarDocumentos();
			EnvioEmails email = new EnvioEmails();
			String fileName = "";
			String rutaArchivo = "";
			String emailC = cotizacionEJB.correoCliente(idCliente);
			String emailU = cotizacionEJB.correoUsuario(idUsuario());

			if (formatoCotizacion == 1) {
				generarDocumentos.generarArchivo(formatoCotizacion, cotizacion.getNumeroCotizacion());
				fileName = "cotizacion.pdf";
				rutaArchivo = "D:\\SEA\\Reportes\\PDF\\cotizacion_N_";
				email.enviarEmail(fileName, cotizacion.getNumeroCotizacion(), rutaArchivo, emailC, emailU);

			} else {
				generarDocumentos.generarArchivo(formatoCotizacion, cotizacion.getNumeroCotizacion());
				fileName = "cotizacion.xlsx";
				rutaArchivo = "D:\\SEA\\Reportes\\EXCEL\\cotizacion_N_";
				email.enviarEmail(fileName, cotizacion.getNumeroCotizacion(), rutaArchivo, emailC, emailU);

			}

			//FacesContext.getCurrentInstance().responseComplete();
			limpiarControles();
			snackbarData.put("message", "Se registro la cotización de forma correcta");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

		} catch (Exception e) {
			dialogTittle = "Error no controlado";
			dialogContent = e.getMessage();
			RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
		}
	}

	public void modificarCotización() {
		try {
			cotizacionEJB.edit(cotizacion);
		} catch (Exception e) {
		}

	}

	public String leerId(Cotizacion cotizacion) {
		this.cotizacion = cotizacionEJB.find(cotizacion.getNumeroCotizacion());
		return "actualizarCotizacion.xhtml";

	}

	private void limpiarControles() {
		cotizacion = new Cotizacion();
		cotizacion.setFechaEmision(new Date());
		fechaEmision = cotizacion.getFechaEmision();
		cotizacion.setValidezOferta(60);
		cotizacion.setDescuento(15);
		cotizacion.setIva(19);
		cotizacionP = new CotizacionProducto();
		clientes = clienteEJB.listaClienteCotizacion(setUsuarioLogueado());
		cliente = new Cliente();
		producto = new Producto();
		listaCotizacionP = new ArrayList<>();
		listaProducto = productoEJB.findAll();
		usuario = new Usuario();
		lugaresEntrega = new LugaresEntrega();
		tiempoEntrega = new TiempoEntrega();
		descuentoVolumen = new DescuentoVolumen();
		ListapropuestaNoIncluye = propuestaEJB.findAll();
		listaTiempoEntrega = tiempoEJB.findAll();
		listaLugaresEntrega = lugaresEEJB.findAll();
		listaDescuentoVolumen = descuentoVEJB.findAll();
		listaModalidadDePago = modalidadPEJB.findAll();
		listaSeguimientoCotizacions = cotizacionEJB.listaSeguimiento(idUsuario());
		propuestaNoIncluye = new PropuestaNoIncluye();
	}
	
	public void obtenerDescripcionReferencia() throws Exception {
		try {

			producto = productoEJB.productoDescripcion(idProducto);
			listaMateriales = materialEJB.datosMaterial(idProducto);
			listaFabricante = fabricanteEJB.descripcionFabricante(idProducto);
			listaProductoPrecio = productoEJB.productoPrecio(idProducto);
		} catch (Exception e) {
			throw e;
		}

	}

	// Metodo para obtener las cotizaciones registradas por un asesor
	public void obtenerCotizacionesRegistradas() throws Exception {
		try {
			listaSeguimientoCotizacions = cotizacionEJB.listaSeguimiento(idUsuario());
		} catch (Exception e) {
		}
	}

	public int idUsuario() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		return u.getIdUsuario();
	}

	//Forma de generar el id de la cotización
	public String generarIdCotizacion() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		this.usuario = u;
		String numeroCotizacionObtenida = "";
		try {
			numeroCotizacionObtenida = cotizacionEJB.numeroCotizacionUsuario(u.getIdUsuario());
		} catch (Exception e) {
		}

		char numeroCotizacion = numeroCotizacionObtenida.charAt(numeroCotizacionObtenida.length() - 1);
		String numeroCotizacionentero = Character.toString(numeroCotizacion);
		int numero = Integer.parseInt(numeroCotizacionentero);
		numero = numero + 1;
		return u.getIdInterno() + " -" + numero;
	}


	public PropuestaNoIncluye getPropuestaNoIncluye() {
		return propuestaNoIncluye;
	}

	public void setPropuestaNoIncluye(PropuestaNoIncluye propuestaNoIncluye) {
		this.propuestaNoIncluye = propuestaNoIncluye;
	}

	public TiempoEntrega getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(TiempoEntrega tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public DescuentoVolumen getDescuentoVolumen() {
		return descuentoVolumen;
	}

	public void setDescuentoVolumen(DescuentoVolumen descuentoVolumen) {
		this.descuentoVolumen = descuentoVolumen;
	}

	public LugaresEntrega getLugaresEntrega() {
		return lugaresEntrega;
	}

	public void setLugaresEntrega(LugaresEntrega lugaresEntrega) {
		this.lugaresEntrega = lugaresEntrega;
	}

	public int getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;

	}

	public Object getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(Object datosCliente) {
		this.datosCliente = datosCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<TiempoEntrega> getListaTiempoEntrega() {
		return listaTiempoEntrega;
	}

	public void setListaTiempoEntrega(List<TiempoEntrega> listaTiempoEntrega) {
		this.listaTiempoEntrega = listaTiempoEntrega;
	}

	public List<DescuentoVolumen> getListaDescuentoVolumen() {
		return listaDescuentoVolumen;
	}

	public void setListaDescuentoVolumen(List<DescuentoVolumen> listaDescuentoVolumen) {
		this.listaDescuentoVolumen = listaDescuentoVolumen;
	}

	public List<ModalidadDePago> getListaModalidadDePago() {
		return listaModalidadDePago;
	}

	public void setListaModalidadDePago(List<ModalidadDePago> listaModalidadDePago) {
		this.listaModalidadDePago = listaModalidadDePago;
	}

	public List<LugaresEntrega> getListaLugaresEntrega() {
		return listaLugaresEntrega;
	}

	public void setListaLugaresEntrega(List<LugaresEntrega> listaLugaresEntrega) {
		this.listaLugaresEntrega = listaLugaresEntrega;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public CotizacionProducto getCotizacionProducto() {
		return cotizacionProducto;
	}

	public void setCotizacionProducto(CotizacionProducto cotizacionProducto) {
		this.cotizacionProducto = cotizacionProducto;
	}

	public List<CotizacionProducto> getListaCotizacionP() {
		return listaCotizacionP;
	}

	public void setListaCotizacionP(List<CotizacionProducto> listaCotizacionP) {
		this.listaCotizacionP = listaCotizacionP;
	}

	public CiudadFacadeLocal getCiudadEJB() {
		return ciudadEJB;
	}

	public void setCiudadEJB(CiudadFacadeLocal ciudadEJB) {
		this.ciudadEJB = ciudadEJB;
	}

	public int getIdPropuestaNoIncluye() {
		return idPropuestaNoIncluye;
	}

	public void setIdPropuestaNoIncluye(int idPropuestaNoIncluye) {
		this.idPropuestaNoIncluye = idPropuestaNoIncluye;
	}

	public int getIdTiempoEntrega() {
		return idTiempoEntrega;
	}

	public void setIdTiempoEntrega(int idTiempoEntrega) {
		this.idTiempoEntrega = idTiempoEntrega;
	}

	public int getIdDescuentoVolumen() {
		return idDescuentoVolumen;
	}

	public void setIdDescuentoVolumen(int idDescuentoVolumen) {
		this.idDescuentoVolumen = idDescuentoVolumen;
	}

	public int getIdModalidadDePago() {
		return idModalidadDePago;
	}

	public void setIdModalidadDePago(int idModalidadDePago) {
		this.idModalidadDePago = idModalidadDePago;
	}

	public ModalidadDePago getModalidadDePago() {
		return modalidadDePago;
	}

	public void setModalidadDePago(ModalidadDePago modalidadDePago) {
		this.modalidadDePago = modalidadDePago;
	}

	public int getIdLugaresEntrega() {
		return idLugaresEntrega;
	}

	public void setIdLugaresEntrega(int idLugaresEntrega) {
		this.idLugaresEntrega = idLugaresEntrega;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public Object getCliente() {
		return datosCliente;
	}

	public void setCliente(Object cliente) {
		this.datosCliente = cliente;
	}

	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario setUsuarioLogueado() {
		return EJBUsuario.find(idUsuario());
	}

	public Float getPrecioParaCliente() {
		return precioParaCliente;
	}

	public void setPrecioParaCliente(Float precioParaCliente) {
		this.precioParaCliente = precioParaCliente;
	}

	
	public List<Material> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<Material> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public List<Fabricante> getListaFabricante() {
		return listaFabricante;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

	public List<Producto> getListaProductoPrecio() {
		return listaProductoPrecio;
	}

	public void setListaProductoPrecio(List<Producto> listaProductoPrecio) {
		this.listaProductoPrecio = listaProductoPrecio;
	}

	public CotizacionProducto getCotizacionP() {
		return cotizacionP;
	}

	public void setCotizacionP(CotizacionProducto cotizacionP) {
		this.cotizacionP = cotizacionP;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public double getPrecioDescuento() {
		return precioDescuento;
	}

	public void setPrecioDescuento(double precioDescuento) {
		this.precioDescuento = calcularPrecioProductoDescuento();
	}

	public Double getDescuentoCotizacion() {
		return descuentoCotizacion;
	}

	public void setDescuentoCotizacion(Double descuentoCotizacion) {
		this.descuentoCotizacion = descuentoCotizacion;
	}

	public List<Cotizacion> getListaSeguimientoCotizacions() {
		return listaSeguimientoCotizacions;
	}

	public void setListaSeguimientoCotizacions(List<Cotizacion> listaSeguimientoCotizacions) {
		this.listaSeguimientoCotizacions = listaSeguimientoCotizacions;
	}

	public int getFormatoCotizacion() {
		return formatoCotizacion;
	}

	public void setFormatoCotizacion(int formatoCotizacion) {
		this.formatoCotizacion = formatoCotizacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

}
