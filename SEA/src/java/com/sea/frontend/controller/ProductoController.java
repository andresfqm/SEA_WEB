package com.sea.frontend.controller;

import com.sea.backend.dto.ProductoDTO;
import com.sea.backend.entities.Categoria;
import com.sea.backend.entities.Descuento;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Fabricante;
import com.sea.backend.entities.Material;
import com.sea.backend.entities.Producto;
import com.sea.backend.entities.RegistroCosto;
import com.sea.backend.entities.Subcategoria;
import com.sea.backend.entities.Sufijo;
import com.sea.backend.model.CategoriaFacadeLocal;
import com.sea.backend.model.DescuentoFacadeLocal;
import com.sea.backend.model.DireccionFacadeLocal;
import com.sea.backend.model.FabricanteFacadeLocal;
import com.sea.backend.model.MaterialFacadeLocal;
import com.sea.backend.model.ProductoFacadeLocal;
import com.sea.backend.model.RegistroCostoFacadeLocal;
import com.sea.backend.model.SubcategoriaFacadeLocal;
import com.sea.backend.model.SufijoFacadeLocal;
import com.sea.backend.util.AbrirCerrarDialogos;
import java.io.FileInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.json.JSONObject;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class ProductoController implements Serializable {

	@EJB
	private ProductoFacadeLocal productoEJB;
	private Producto prod;
	@EJB
	private FabricanteFacadeLocal fabricanteEJB;
	private List<Producto> producto;
	private List<ProductoDTO> listaProducto;
	private List<Material> listaMateriales;
	private List<Fabricante> listaFabricante;
	private double productoPrecio;
	private int idProducto;
	private int idFabricante;
	private int idMaterial;
	private Producto productoDescripcion;
	private int idCliente;
	String listString;
	private Fabricante descripcionFabricante;
	private Material datosMaterial;

	@EJB
	private CategoriaFacadeLocal categoriaEJB;
	private Categoria categoria;
	private List<Categoria> listaCategoria;

	@EJB
	private SubcategoriaFacadeLocal subcategoriaEJB;
	private Subcategoria subcategoria;
	private List<Subcategoria> listaSubcategoria;

	@EJB
	private MaterialFacadeLocal materialEJB;
	private Material material;
	private List<Material> listaMaterial;

	@EJB
	private SufijoFacadeLocal sufijoEJB;
	private Sufijo sufijo;
	private List<Sufijo> listaSufijo;

	@EJB
	private DescuentoFacadeLocal descuentoEJB;
	private Descuento descuento;
	private List<Descuento> listaDescuento;
	private List<Descuento> listaDescuentos;

	private String referencia;
	private int[] listaMaterialAux;
	private int[] listaDescuentoAux;
	private Date date;
	@EJB
	private RegistroCostoFacadeLocal registroCostoEJB;
	private RegistroCosto costo;

	private final static Logger log = Logger.getLogger(ProductoController.class);
	JSONObject snackbarData = new JSONObject();

	@PostConstruct
	public void init() {

		try {
			Properties props = new Properties();
			props.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(props);
			producto = productoEJB.findAll();
			prod = new Producto();
			categoria = new Categoria();
			subcategoria = new Subcategoria();
			material = new Material();
			sufijo = new Sufijo();
			listaDescuento = new ArrayList();
			descuento = new Descuento();
			costo = new RegistroCosto();
			listaProducto = productoEJB.listaProductos();

		} catch (Exception e) {
			log.error("Se presento el siguiente error en el bean de productos " + e.getMessage());
			e.printStackTrace();
		}

	}

	public String referencia() {
		referencia = subcategoria.getCodigo() + material.getCodigo() + sufijo.getCodigo();
		return referencia;
	}

	/*
    * @author Andres Quintana
	* Fecha creación 17/08/2020
	* Metodo encargado de registrar los articulos
	 */
	public void registrarArticulo() {

		log.info("Ingreso al proceso de registrar el articulo : " + prod.getReferencia());

		Material material = new Material();
		Descuento descuento = new Descuento();
		listaMateriales = new ArrayList();
		listaDescuentos = new ArrayList();

		if (prod.getReferencia().trim().isEmpty()) {
			snackbarData.put("message", "Se debe ingresar la referencia");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (prod.getDescripcion().trim().isEmpty()) {
			snackbarData.put("message", "Se debe ingresar la descripción del producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (categoria.getIdCategoria() == null) {
			snackbarData.put("message", "Se debe seleccionar la categoria del producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (subcategoria.getIdSubcategoria() == null) {
			snackbarData.put("message", "Se debe seleccionar la subcategoria de la categoria seleccionada");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (listaMaterialAux.length == 0) {
			snackbarData.put("message", "Se debe seleccionar los materiales del producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (sufijo.getIdSufijo() == null) {
			snackbarData.put("message", "Se debe seleccionar un sufijo");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (idFabricante == 0) {
			snackbarData.put("message", "Se debe seleccionar el fabricante");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (costo.getCosto() == 0.0) {
			snackbarData.put("message", "Se debe ingresar el costo del producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (prod.getPrecio() == 0.0) {
			snackbarData.put("message", "Se debe ingresar el precio del producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else if (listaDescuentoAux.length == 0) {
			snackbarData.put("message", "Se debe de seleccionar los descuentos que le aplicara al producto");
			RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
		} else {

			for (int i = 0; i < listaMaterialAux.length; i++) {
				for (Material mat : listaMaterial) {
					if (mat.getIdMaterial() == listaMaterialAux[i]) {
						listaMateriales.add(mat);
					}
				}

			}
			for (int i = 0; i < listaDescuentoAux.length; i++) {
				for (Descuento des : listaDescuento) {
					if (des.getIdDescuento() == listaDescuentoAux[i]) {
						listaDescuentos.add(des);
					}
				}

			}

			List<Sufijo> liSufijo = new ArrayList<>();

			for (Sufijo suf : listaSufijo) {
				if (suf.getIdSufijo() == sufijo.getIdSufijo()) {
					liSufijo.add(suf);
					break;
				}
			}
			Fabricante fb = new Fabricante();
			fb.setIdFabricante(idFabricante);
			prod.setSufijoList(liSufijo);
			prod.setTblFabricanteIdFabricante(fb);
			prod.setTblSubcategoriaIdSubcategoria(subcategoria);
			prod.setFechaActualizacion(date);
			try {

				productoEJB.create(prod);

				costo.setInicioVigencia(date);
				costo.setFinVigencia(date);
				costo.setTblProductoIdProducto(prod);
				registroCostoEJB.create(costo);

				for (Material prom : listaMateriales) {
					productoEJB.crearProductoMaterial(prod.getIdProducto(), prom.getIdMaterial());
				}
				for (Descuento desc : listaDescuentos) {
					productoEJB.crearProductoDescuento(desc.getIdDescuento(), prod.getIdProducto());
				}
				listaProducto = productoEJB.listaProductos();
				snackbarData.put("message", "Se creo el artículo de referencia " + prod.getReferencia() + " de forma correcta");
				RequestContext.getCurrentInstance().execute("mostrarSnackbar(" + snackbarData + ");");
				AbrirCerrarDialogos.abrirCerrarDialogos("PF('agrePro').hide();");
				limpiar();
			} catch (Exception e) {
				log.error("Se presento el siguinte error al registra el producto de referencia : " + prod.getReferencia());
				e.printStackTrace();
				limpiar();
			}

		}

	}

	public void obtenerSubcategoria() {
		log.info("Ingreso al proceso de obtener la subcategoria  de la Categoria " + categoria.getNombre());
		try {
			listaSubcategoria = subcategoriaEJB.listaSubcategorias(categoria);
		} catch (Exception e) {
			log.error("Se presento el siguiente error al obtener la subcategoria de la Categoria :" + categoria.getNombre() + " " + e.getMessage());
			e.printStackTrace();
		}

	}
//		public void obtenerCiudad() {
//		listaCiudad = ciudadEJB.listaCiudades(departamento);
//	}

	public void limpiar() {
		listaCategoria = new ArrayList<>();
		listaFabricante = new ArrayList<>();
		listaSufijo = new ArrayList<>();
		listaMaterial = new ArrayList<>();
		prod = new Producto();
		costo = new RegistroCosto();
		material = new Material();
		descuento = new Descuento();
		listaMateriales = new ArrayList();
		listaDescuentos = new ArrayList();
		sufijo = new Sufijo();
		categoria = new Categoria();
		subcategoria = new Subcategoria();
		idFabricante = 0;
		listaMaterialAux = null;
		listaDescuentoAux = null;
		//AbrirCerrarDialogos.abrirCerrarDialogos("PF('agrePro').hide();");
	}

	/*
    * @author Andres Quintana
	* Fecha modificación 18/08/2020
	* Metodo encargado de abrir el dialogo de crear articulos
	 */
	public void abrirDialogoCrearArticulo() {
		listaCategoria = categoriaEJB.findAll();
		listaFabricante = fabricanteEJB.findAll();
		listaSufijo = sufijoEJB.findAll();
		listaMaterial = materialEJB.findAll();
		listaDescuento = descuentoEJB.findAll();
		AbrirCerrarDialogos.abrirCerrarDialogos("PF('agrePro').show();");
	}

	public void onItemUnselect(UnselectEvent event) {
		System.out.println("Ingreso al metodo onItemUnselect" + date);
		FacesContext context = FacesContext.getCurrentInstance();

		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item unselected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		context.addMessage(null, msg);
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
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

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}

	public Producto getProductoDescripcion() {
		return productoDescripcion;
	}

	public void setProductoDescripcion(Producto productoDescripcion) {
		this.productoDescripcion = productoDescripcion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getListString() {
		return listString;
	}

	public void setListString(String listString) {
		this.listString = listString;
	}

	public Fabricante getDescripcionFabricante() {
		return descripcionFabricante;
	}

	public void setDescripcionFabricante(Fabricante descripcionFabricante) {
		this.descripcionFabricante = descripcionFabricante;
	}

	public Material getDatosMaterial() {
		return datosMaterial;
	}

	public void setDatosMaterial(Material datosMaterial) {
		this.datosMaterial = datosMaterial;
	}

	public Direccion getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(Direccion direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public void obtenerDescripcionReferencia() throws Exception {
		try {

			productoDescripcion = productoEJB.productoDescripcion(idProducto);
			listaMateriales = materialEJB.datosMaterial(idProducto);
			listaFabricante = fabricanteEJB.descripcionFabricante(idProducto);
			productoPrecio = productoEJB.productoPrecio(idProducto);
		} catch (Exception e) {
			throw e;
		}

	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	@EJB
	private DireccionFacadeLocal direccionEJB;
	private Direccion direccionCliente;

	public void obtenerDireccionCliente() throws Exception {
		try {
			direccionCliente = direccionEJB.direccionCliente(idCliente);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<ProductoDTO> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<ProductoDTO> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public List<Subcategoria> getListaSubcategoria() {
		return listaSubcategoria;
	}

	public void setListaSubcategoria(List<Subcategoria> listaSubcategoria) {
		this.listaSubcategoria = listaSubcategoria;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getListaMaterial() {
		return listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public Sufijo getSufijo() {
		return sufijo;
	}

	public void setSufijo(Sufijo sufijo) {
		this.sufijo = sufijo;
	}

	public List<Sufijo> getListaSufijo() {
		return listaSufijo;
	}

	public void setListaSufijo(List<Sufijo> listaSufijo) {
		this.listaSufijo = listaSufijo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public double getProductoPrecio() {
		return productoPrecio;
	}

	public Producto getProd() {
		return prod;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public List<Descuento> getListaDescuento() {
		return listaDescuento;
	}

	public void setListaDescuento(List<Descuento> listaDescuento) {
		this.listaDescuento = listaDescuento;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int[] getListaMaterialAux() {
		return listaMaterialAux;
	}

	public void setListaMaterialAux(int[] listaMaterialAux) {
		this.listaMaterialAux = listaMaterialAux;
	}

	public int[] getListaDescuentoAux() {
		return listaDescuentoAux;
	}

	public void setListaDescuentoAux(int[] listaDescuentoAux) {
		this.listaDescuentoAux = listaDescuentoAux;
	}

	public List<Descuento> getListaDescuentos() {
		return listaDescuentos;
	}

	public void setListaDescuentos(List<Descuento> listaDescuentos) {
		this.listaDescuentos = listaDescuentos;
	}

	public RegistroCosto getCosto() {
		return costo;
	}

	public void setCosto(RegistroCosto costo) {
		this.costo = costo;
	}

}
