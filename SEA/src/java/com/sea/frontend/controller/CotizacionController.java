/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.frontend.controller;

import com.sea.backend.dto.ClienteDTO;
import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Cliente;
import com.sea.backend.entities.Cotizacion;
import com.sea.backend.entities.CotizacionProducto;
import com.sea.backend.entities.Descuento;
import com.sea.backend.entities.DescuentoVolumen;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Fabricante;
import com.sea.backend.entities.LugaresEntrega;
import com.sea.backend.entities.Material;
import com.sea.backend.entities.ModalidadDePago;
import com.sea.backend.entities.Producto;
import com.sea.backend.entities.PropuestaNoIncluye;
import com.sea.backend.entities.Telefono;
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
import com.sea.backend.util.AbrirCerrarDialogos;
import com.sea.backend.util.EnvioEmails;
import com.sea.backend.util.GenerarDocumentos;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
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
	private CotizacionProducto cot;
	private CotizacionProducto cotProducModific;

	@EJB
	private UsuarioFacadeLocal EJBUsuario;
	private Usuario usuario;

	//EJB cliente
	@EJB
	private ClienteFacadeLocal clienteEJB;
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private ClienteDTO datosCliente;
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
	private float precioDescuento;
	private float valorTotalDescuentoSinIva;
	private float precioUnitarioSinIvaConDescuento;
	private float totalIva = 0;

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
	private int idPropuestaNoIncluyeModificacion;

	//Ejb de la foranea TiempoEntrega
	@EJB
	private TiempoEntregaFacadeLocal tiempoEJB;
	private int idTiempoEntrega;
	private int idTiempoEntregaModificacion;
	private TiempoEntrega tiempoEntrega;
	private List<TiempoEntrega> listaTiempoEntrega;

	//Ejb de la foranea DescuentoVolen
	@EJB
	private DescuentoVolumenFacadeLocal descuentoVEJB;
	private int idDescuentoVolumen;
	private int idDescuentoVolumenModificacion;
	private DescuentoVolumen descuentoVolumen;
	private List<DescuentoVolumen> listaDescuentoVolumen;

	//EJB Modalidades de pago
	@EJB
	private ModalidadDePagoFacadeLocal modalidadPEJB;
	private int idModalidadDePago;
	private int idModalidadDePagoModificacion;
	private List<ModalidadDePago> listaModalidadDePago;
	private ModalidadDePago modalidadDePago;

	//EJB Lugares de entrega
	@EJB
	private LugaresEntregaFacadeLocal lugaresEEJB;
	private int idLugaresEntrega;
	private int idLugaresEntregaModificacion;
	private LugaresEntrega lugaresEntrega;
	private List<LugaresEntrega> listaLugaresEntrega;

	//Entidad producto
	@EJB
	private ProductoFacadeLocal productoEJB;
	private Producto producto;
	private List<Material> listaMateriales;
	private List<Fabricante> listaFabricante;
	private List<Producto> listaProducto;
	private double precioProducto;

	@EJB
	private DescuentoFacadeLocal descuentoEJB;
	private int idDescuento;
	private List<Descuento> lsDescuento;
	private Descuento descuento;
	private int formatoCotizacion;
	private String mensaje;
	private Date fechaEmision;
	private float descuentoTotal = 0;
	private float valorTotalSinIva = 0;
	private float total = 0;
	private float valorSinDescuento;
	private float totalDescuento;
	private int numeroRegistroArticulo = 0;
	private boolean enviarEmail = false;
	private String renderForm;
	private Cotizacion cotizacionModificacion;
	private List<CotizacionProducto> listaProductosModificacion;
	private List<CotizacionProducto> listaProductosModificacionEliminarBd;
	private String ciudadModificacion;
	private String departamentoModificacion;
	private ClienteDTO emailCotizacionModificacion;

	private String urlIndexSeguimiento = "/SEA/cotizaciones/seguimiento/index.xhtml";
	private String urlRegistrarCotización = "/SEA/cotizaciones/enviarCotizacion.xhtml";

	private int tipoOperacion = 0;

	@PostConstruct
	public void init() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map params = externalContext.getRequestParameterMap();
		//Integer categorySelected = new Integer((String) params.get("id"));
		String categorySelected = (String) params.get("numeroCotizacion");
		if (categorySelected != null) {
			tipoOperacion = 1;
			cotizacionModificacion = new Cotizacion();
			listaProductosModificacion = new ArrayList<>();
			emailCotizacionModificacion = new ClienteDTO();
			cotProducModific = new CotizacionProducto();
			listaProductosModificacionEliminarBd = new ArrayList<>();
			cotizacionModificacion = cotizacionEJB.find(categorySelected);
			listaProductosModificacion = cotizacionModificacion.getCotizacionProductoList();
			List<Direccion> ls = cotizacionModificacion.getTblClienteIdCliente().getDireccionList();
			idPropuestaNoIncluyeModificacion = cotizacionModificacion.getTblPropuestaNoIncluyeIdPropuestaNoIncluye().getIdPropuestaNoIncluye();
			idDescuentoVolumenModificacion = cotizacionModificacion.getTblDescuentoVolumenIdDescuentoVolumen().getIdDescuentoVolumen();
			idModalidadDePagoModificacion = cotizacionModificacion.getTblModalidadDePagoIdModalidadDePago().getIdModalidadDePago();
			idTiempoEntregaModificacion = cotizacionModificacion.getTblTiempoEntregaIdTiempoEntrega().getIdTiempoEntrega();
			idLugaresEntregaModificacion = cotizacionModificacion.getTblLugaresEntregaIdLugaresEntrega().getIdLugaresEntrega();

			for (Direccion dir : ls) {
				ciudadModificacion = dir.getTblCiudadIdCiudad().getNombre();
				departamentoModificacion = dir.getTblCiudadIdCiudad().getTblDepartamentoIdDepartamento().getNombre();
			}

			int count = 0;
			for (Email email : cotizacionModificacion.getTblClienteIdCliente().getEmailList()) {
				if (count < 1) {
					emailCotizacionModificacion.setEmail1(email.getEmail());
					count = count + 1;
				} else if (count == 1) {
					emailCotizacionModificacion.setEmail2(email.getEmail());

				}

			}

			int count2 = 0;

			for (Telefono tel : cotizacionModificacion.getTblClienteIdCliente().getTelefonoList()) {
				if (count2 < 1) {
					emailCotizacionModificacion.setTelefono1(tel.getNumeroTelefono());
					count2 = count2 + 1;
				} else if (count2 == 1) {
					emailCotizacionModificacion.setTelefono2(tel.getNumeroTelefono());
				}

			}

			int idCotProducAux = 0;
			List<CotizacionProducto> listCotProductAux = new ArrayList<>();

			for (CotizacionProducto cotProduc : listaProductosModificacion) {
				idCotProducAux = idCotProducAux + 1;
				cotProduc.setIdAuxiliar(idCotProducAux);
				listCotProductAux.add(cotProduc);
			}

			listaProductosModificacion = listCotProductAux;
		}

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
		cot = new CotizacionProducto();
		listaCotizacionP = new ArrayList<>();
		listaProducto = new ArrayList<>();
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
		descuento = new Descuento();
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
		if (producto.getIdProducto() == null) {
			snackbarData.put("message", "Se debe seleccionar una referencia");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

		} else if (cotizacionP.getCantidad() <= 0) {
			snackbarData.put("message", "Se debe ingresar la cantidad minima");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

		} else if (descuento.getIdDescuento() == null) {
			snackbarData.put("message", "Se debe seleccionar un descuento");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {
			cot.setTblProductoIdProducto(productoEJB.find(producto.getIdProducto()));
			cot.setCantidad(cotizacionP.getCantidad());
			cot.setPrecioParaCliente((float) valorTotalDescuentoSinIva);
			cot.setPrecioBase((float) precioUnitarioSinIvaConDescuento);
			//  ven.setTblProductoIdProducto(productoEJB.find(producto.getIdProducto()));

			numeroRegistroArticulo = numeroRegistroArticulo + 1;
			cot.setIdAuxiliar(numeroRegistroArticulo);
			listaCotizacionP.add(cot);

			System.out.println("Lo que tiene la variable total Descuento : " + "" + totalDescuento);
			descuentoTotal += totalDescuento;

			System.out.println("Lo que tiene la variable que totaliza el total descuento : " + "" + descuentoTotal);

			valorTotalSinIva += valorTotalDescuentoSinIva;

			totalIva = (float) (valorTotalSinIva * 0.19);

			total = totalIva + valorTotalSinIva;
			cotizacion.setTotalDescuento(descuentoTotal);
			cotizacion.setSubtotal(valorTotalSinIva);
			cotizacion.setTotalIva(totalIva);
			cotizacion.setValorTotal(total);

			snackbarData.put("message", "Se agregó el artículo con referencia '" + cot.getTblProductoIdProducto().getReferencia() + "'.");

			limpiarControlesDialogoAgregarArticulos();
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg2').hide();");

		}
	}

	public void agregarArticulo() {
		listaProducto = productoEJB.findAll();
		lsDescuento = descuentoEJB.findAll();
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlg2').show();");

	}

	public void agregarCotizacionProductoModificacion() {

		if (producto.getIdProducto() == null) {
			snackbarData.put("message", "Se debe seleccionar una referencia");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

		} else if (cotizacionP.getCantidad() <= 0) {
			snackbarData.put("message", "Se debe ingresar la cantidad minima");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

		} else if (descuento.getIdDescuento() == null) {
			snackbarData.put("message", "Se debe seleccionar un descuento");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {
			numeroRegistroArticulo = 1000;
			numeroRegistroArticulo = numeroRegistroArticulo + 1;
			cotProducModific.setTblProductoIdProducto(productoEJB.find(producto.getIdProducto()));
			cotProducModific.setCantidad(cotizacionP.getCantidad());
			cotProducModific.setPrecioParaCliente((float) valorTotalDescuentoSinIva);
			cotProducModific.setPrecioBase((float) precioUnitarioSinIvaConDescuento);

			cotProducModific.setIdAuxiliar(numeroRegistroArticulo);
			listaProductosModificacion.add(cotProducModific);
			snackbarData.put("message", "Se agregó el artículo con referencia '" + cotProducModific.getTblProductoIdProducto().getReferencia() + "'.");
			limpiarControlesDialogoAgregarArticulos();
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
			AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgCotizacionMod').hide();");
		}
	}

	public void agregarArticuloModificacion() {
		listaProducto = productoEJB.findAll();
		lsDescuento = descuentoEJB.findAll();
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgCotizacionMod').show();");
	}

	public void eliminarArticuloCotizacion(int idArticulo) {

		List<CotizacionProducto> lsAux = new ArrayList<>();
		for (CotizacionProducto cp : listaCotizacionP) {
			if (cp.getIdAuxiliar() != idArticulo) {
				lsAux.add(cp);
			} else {

				float precioTotalProducto = cp.getTblProductoIdProducto().getPrecio() * cp.getCantidad();
				float precioTotalProductoDesc = cp.getPrecioBase() * cp.getCantidad();

				float reclacularTotalDescuento = precioTotalProducto - precioTotalProductoDesc;

				float totalDescuentoProducto = cotizacion.getTotalDescuento() - reclacularTotalDescuento;
				cotizacion.setTotalDescuento(totalDescuentoProducto);

				descuentoTotal = totalDescuentoProducto;

				float recalcularSubtotal = cotizacion.getSubtotal() - cp.getPrecioParaCliente();
				cotizacion.setSubtotal(recalcularSubtotal);
				valorTotalSinIva = recalcularSubtotal;

				float recalcularTotalIva = (float) (cotizacion.getSubtotal() * 0.19);
				cotizacion.setTotalIva(recalcularTotalIva);

				float recalcularTotal = cotizacion.getSubtotal() + cotizacion.getTotalIva();
				cotizacion.setValorTotal(recalcularTotal);

			}

		}

		listaCotizacionP = new ArrayList<>();
		listaCotizacionP = lsAux;
	}

	public void eliminarArticuloCotizacionModificacion(int idArticulo) {

		List<CotizacionProducto> lsAux2 = new ArrayList<>();

		for (CotizacionProducto cp : listaProductosModificacion) {
			if (idArticulo < 1000 && cp.getIdAuxiliar() == idArticulo) {
				listaProductosModificacionEliminarBd.add(cp);
				System.out.println("Se debe eliminar en base de datos");
			} else if (cp.getIdAuxiliar() != idArticulo) {
				lsAux2.add(cp);
			}

		}
		listaProductosModificacion = new ArrayList<>();
		listaProductosModificacion = lsAux2;
	}

	public void limpiarControlesDialogoAgregarArticulos() {
		producto = new Producto();
		cotizacionP = new CotizacionProducto();
		listaMateriales = new ArrayList<>();
		listaFabricante = new ArrayList<>();
		listaProducto = new ArrayList<>();
		cot = new CotizacionProducto();
		cotProducModific = new CotizacionProducto();
		valorTotalDescuentoSinIva = 0;
		lsDescuento = new ArrayList<>();
		precioProducto = 0;
		precioUnitarioSinIvaConDescuento = 0;
		valorTotalDescuentoSinIva = 0;
		descuento = new Descuento();
		tipoOperacion = 0;
	}

	public void calcularPrecioSinIva() {
		double desAux = 0;
		if (cotizacionP.getCantidad() > 0) {
			if (descuento.getIdDescuento() != null) {

				for (Descuento de : lsDescuento) {
					if (de.getIdDescuento() == descuento.getIdDescuento()) {
						desAux = de.getDescuento();
						break;
					}
				}
				valorSinDescuento = (float) (precioProducto * cotizacionP.getCantidad());
				totalDescuento = (float) ((desAux * valorSinDescuento) / 100);
				valorTotalDescuentoSinIva = (float) (valorSinDescuento - totalDescuento);

				precioUnitarioSinIvaConDescuento = valorTotalDescuentoSinIva / cotizacionP.getCantidad();

			}
		} else {
			snackbarData.put("message", "Por favor ingresar la cantidad mínima");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		}

	}

	public void registrarCotización()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		if (idTiempoEntrega == 0) {
			snackbarData.put("message", "Se debe seleccionar Tiempo de entrega");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (idModalidadDePago == 0) {
			snackbarData.put("message", "Se debe seleccionar Forma de pago");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (idPropuestaNoIncluye == 0) {
			snackbarData.put("message", "Se debe seleccionar La oferta no incluye");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (idDescuentoVolumen == 0) {
			snackbarData.put("message", "Se debe seleccionar Descuento por volumen");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (idLugaresEntrega == 0) {
			snackbarData.put("message", "Se debe seleccionar Lugar de entrega");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {

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

				snackbarData.put("message", "Se registro la cotización de forma correcta");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");

				if (enviarEmail == true) {

					GenerarDocumentos generarDocumentos = new GenerarDocumentos();
					EnvioEmails email = new EnvioEmails();
					String fileName = "";
					String rutaArchivo = "";
					String emailC = datosCliente.getEmail1();
					String emailU = cotizacionEJB.correoUsuario(idUsuario());

					List<String> emails = new ArrayList<>();
					emails.add(datosCliente.getEmail1());
					emails.add(datosCliente.getEmail2());

					if (formatoCotizacion == 1) {
						generarDocumentos.generarArchivo(formatoCotizacion, cotizacion.getNumeroCotizacion());
						fileName = "cotizacion.pdf";
						rutaArchivo = "D:\\SEA\\Reportes\\PDF\\cotizacion_N_";
						email.enviarEmail(fileName, cotizacion.getNumeroCotizacion(), rutaArchivo, emails, emailU, mensaje);

					} else {
						generarDocumentos.generarArchivo(formatoCotizacion, cotizacion.getNumeroCotizacion());
						fileName = "cotizacion.xlsx";
						rutaArchivo = "D:\\SEA\\Reportes\\EXCEL\\cotizacion_N_";
						email.enviarEmail(fileName, cotizacion.getNumeroCotizacion(), rutaArchivo, emails, emailU, mensaje);

					}
				}

				limpiarControles();
				AbrirCerrarDialogos.abrirCerrarDialogos("PF('dlgConfirmacion').show();");

			} catch (Exception e) {
				dialogTittle = "Error no controlado";
				dialogContent = e.getMessage();
				RequestContext.getCurrentInstance().execute("mostrarDialogos(`" + dialogTittle + "`, `" + dialogContent + "`);");
			}
		}
	}

	public void modificarCotización() {
		try {
			if (listaProductosModificacionEliminarBd.size() > 0) {
				for (CotizacionProducto cotP : listaProductosModificacionEliminarBd) {
					cotizacionProductoEJB.remove(cotP);
				}
			}
			if (listaProductosModificacion.size() > 0) {
				for (CotizacionProducto itemVenta : listaProductosModificacion) {
					cotizacionP.setTblCotizacionNumeroCotizacion(cotizacionModificacion);
					cotizacionP.setTblProductoIdProducto(itemVenta.getTblProductoIdProducto());
					cotizacionP.setPrecioBase(itemVenta.getPrecioBase());
					if (itemVenta.getPrecioParaCliente() == null) {
						cotizacionP.setPrecioParaCliente(itemVenta.getTblProductoIdProducto().getPrecio());
					} else {
						cotizacionP.setPrecioParaCliente(itemVenta.getPrecioParaCliente());
					}
					cotizacionP.setCantidad(itemVenta.getCantidad());

					if (itemVenta.getIdAuxiliar() > 1000) {
						cotizacionProductoEJB.create(cotizacionP);
					}
				}
			}
			cotizacionEJB.edit(cotizacionModificacion);
			limpiarControlesModificacion();
		} catch (Exception e) {
		}

	}

	public String leerId(Cotizacion cotizacion) {
		this.cotizacion = cotizacionEJB.find(cotizacion.getNumeroCotizacion());
		return "actualizarCotizacion.xhtml";

	}

	public void agregarArticulosCotizacionModificacion() {
		System.out.println("Aqui la logica para agregar articulos a una modificacion");
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
		numeroRegistroArticulo = 0;
		descuentoTotal = 0;
		valorTotalSinIva = 0;
		total = 0;
		valorSinDescuento = 0;
		totalDescuento = 0;
		precioDescuento = 0;
		valorTotalDescuentoSinIva = 0;
		precioUnitarioSinIvaConDescuento = 0;
		totalIva = 0;
		mensaje = "";
	}

	public void limpiarControlesModificacion() {
		listaProductosModificacionEliminarBd = new ArrayList();
	}

	public void obtenerDescripcionReferencia() throws Exception {
		try {

			producto = productoEJB.productoDescripcion(producto.getIdProducto());
			listaMateriales = materialEJB.datosMaterial(producto.getIdProducto());
			listaFabricante = fabricanteEJB.descripcionFabricante(producto.getIdProducto());
			precioProducto = productoEJB.productoPrecio(producto.getIdProducto());
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
		int numero = 0;
		try {
			numeroCotizacionObtenida = cotizacionEJB.numeroCotizacionUsuario(u.getIdUsuario());
			numero = Integer.parseInt(numeroCotizacionObtenida);
			numero = numero + 1;
		} catch (Exception e) {
		}

		return u.getIdInterno() + " -" + numero;
	}

	// Metodo para realizar las redirecciones a otras paginas
	//Recibe como parametro la url a la que se va a redirigir
	public void redireccionar(String url) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect(url);
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

	public CotizacionProducto getCot() {
		return cot;
	}

	public void setCot(CotizacionProducto cot) {
		this.cot = cot;
	}

	public ClienteDTO getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(ClienteDTO datosCliente) {
		this.datosCliente = datosCliente;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public List<Descuento> getLsDescuento() {
		return lsDescuento;
	}

	public void setLsDescuento(List<Descuento> lsDescuento) {
		this.lsDescuento = lsDescuento;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public float getDescuentoTotal() {
		return descuentoTotal;
	}

	public void setDescuentoTotal(float descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	public float getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(float totalIva) {
		this.totalIva = totalIva;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getValorTotalSinIva() {
		return valorTotalSinIva;
	}

	public void setValorTotalSinIva(float valorTotalSinIva) {
		this.valorTotalSinIva = valorTotalSinIva;
	}

	public float getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(float totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public float getValorTotalDescuentoSinIva() {
		return valorTotalDescuentoSinIva;
	}

	public void setValorTotalDescuentoSinIva(float valorTotalDescuentoSinIva) {
		this.valorTotalDescuentoSinIva = valorTotalDescuentoSinIva;
	}

	public float getValorSinDescuento() {
		return valorSinDescuento;
	}

	public void setValorSinDescuento(float valorSinDescuento) {
		this.valorSinDescuento = valorSinDescuento;
	}

	public float getPrecioDescuento() {
		return precioDescuento;
	}

	public void setPrecioDescuento(float precioDescuento) {
		this.precioDescuento = precioDescuento;
	}

	public float getPrecioUnitarioSinIvaConDescuento() {
		return precioUnitarioSinIvaConDescuento;
	}

	public void setPrecioUnitarioSinIvaConDescuento(float precioUnitarioSinIvaConDescuento) {
		this.precioUnitarioSinIvaConDescuento = precioUnitarioSinIvaConDescuento;
	}

	public int getNumeroRegistroArticulo() {
		return numeroRegistroArticulo;
	}

	public void setNumeroRegistroArticulo(int numeroRegistroArticulo) {
		this.numeroRegistroArticulo = numeroRegistroArticulo;
	}

	public List<PropuestaNoIncluye> getListapropuestaNoIncluye() {
		return ListapropuestaNoIncluye;
	}

	public void setListapropuestaNoIncluye(List<PropuestaNoIncluye> ListapropuestaNoIncluye) {
		this.ListapropuestaNoIncluye = ListapropuestaNoIncluye;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public String getRenderForm() {
		return renderForm;
	}

	public void setRenderForm(String renderForm) {
		this.renderForm = renderForm;
	}

	public Cotizacion getCotizacionModificacion() {
		return cotizacionModificacion;
	}

	public void setCotizacionModificacion(Cotizacion cotizacionModificacion) {
		this.cotizacionModificacion = cotizacionModificacion;
	}

	public List<CotizacionProducto> getListaProductosModificacion() {
		return listaProductosModificacion;
	}

	public void setListaProductosModificacion(List<CotizacionProducto> listaProductosModificacion) {
		this.listaProductosModificacion = listaProductosModificacion;
	}

	public ClienteDTO getEmailCotizacionModificacion() {
		return emailCotizacionModificacion;
	}

	public void setEmailCotizacionModificacion(ClienteDTO emailCotizacionModificacion) {
		this.emailCotizacionModificacion = emailCotizacionModificacion;
	}

	public String getUrlIndexSeguimiento() {
		return urlIndexSeguimiento;
	}

	public void setUrlIndexSeguimiento(String urlIndexSeguimiento) {
		this.urlIndexSeguimiento = urlIndexSeguimiento;
	}

	public String getUrlRegistrarCotización() {
		return urlRegistrarCotización;
	}

	public void setUrlRegistrarCotización(String urlRegistrarCotización) {
		this.urlRegistrarCotización = urlRegistrarCotización;
	}

	public String getDepartamentoModificacion() {
		return departamentoModificacion;
	}

	public void setDepartamentoModificacion(String departamentoModificacion) {
		this.departamentoModificacion = departamentoModificacion;
	}

	public String getCiudadModificacion() {
		return ciudadModificacion;
	}

	public void setCiudadModificacion(String ciudadModificacion) {
		this.ciudadModificacion = ciudadModificacion;
	}

	public int getIdPropuestaNoIncluyeModificacion() {
		return idPropuestaNoIncluyeModificacion;
	}

	public void setIdPropuestaNoIncluyeModificacion(int idPropuestaNoIncluyeModificacion) {
		this.idPropuestaNoIncluyeModificacion = idPropuestaNoIncluyeModificacion;
	}

	public int getIdTiempoEntregaModificacion() {
		return idTiempoEntregaModificacion;
	}

	public void setIdTiempoEntregaModificacion(int idTiempoEntregaModificacion) {
		this.idTiempoEntregaModificacion = idTiempoEntregaModificacion;
	}

	public int getIdDescuentoVolumenModificacion() {
		return idDescuentoVolumenModificacion;
	}

	public void setIdDescuentoVolumenModificacion(int idDescuentoVolumenModificacion) {
		this.idDescuentoVolumenModificacion = idDescuentoVolumenModificacion;
	}

	public int getIdModalidadDePagoModificacion() {
		return idModalidadDePagoModificacion;
	}

	public void setIdModalidadDePagoModificacion(int idModalidadDePagoModificacion) {
		this.idModalidadDePagoModificacion = idModalidadDePagoModificacion;
	}

	public int getIdLugaresEntregaModificacion() {
		return idLugaresEntregaModificacion;
	}

	public void setIdLugaresEntregaModificacion(int idLugaresEntregaModificacion) {
		this.idLugaresEntregaModificacion = idLugaresEntregaModificacion;
	}

	public int getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public CotizacionProducto getCotProducModific() {
		return cotProducModific;
	}

	public void setCotProducModific(CotizacionProducto cotProducModific) {
		this.cotProducModific = cotProducModific;
	}

	public List<CotizacionProducto> getListaProductosModificacionEliminarBd() {
		return listaProductosModificacionEliminarBd;
	}

	public void setListaProductosModificacionEliminarBd(List<CotizacionProducto> listaProductosModificacionEliminarBd) {
		this.listaProductosModificacionEliminarBd = listaProductosModificacionEliminarBd;
	}

}
