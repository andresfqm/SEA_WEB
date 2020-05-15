package com.sea.frontend.controller;

import com.sea.backend.entities.Categoria;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Fabricante;
import com.sea.backend.entities.Material;
import com.sea.backend.entities.Producto;
import com.sea.backend.entities.Subcategoria;
import com.sea.backend.entities.Sufijo;
import com.sea.backend.model.CategoriaFacadeLocal;
import com.sea.backend.model.DireccionFacadeLocal;
import com.sea.backend.model.FabricanteFacadeLocal;
import com.sea.backend.model.MaterialFacadeLocal;
import com.sea.backend.model.ProductoFacadeLocal;
import com.sea.backend.model.SubcategoriaFacadeLocal;
import com.sea.backend.model.SufijoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author homero
 */
@Named
@ViewScoped
public class ProductoController implements Serializable {

	@EJB
	private ProductoFacadeLocal productoEJB;
	@EJB
	private FabricanteFacadeLocal fabricanteEJB;
	private List<Producto> producto;
	private List<Producto> listaProducto;
	private List<Material> listaMateriales;
	private List<Fabricante> listaFabricante;
	private List<Producto> listaProductoPrecio;
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
	
	private String referencia;
	
		@PostConstruct
	public void init() {

		producto = productoEJB.findAll();
		categoria = new Categoria();
		subcategoria = new Subcategoria();
		material = new Material();
		sufijo = new Sufijo();
		listaProducto =  productoEJB.listaProductos();
		listaCategoria = categoriaEJB.findAll();
		listaSubcategoria = subcategoriaEJB.findAll();
		listaMaterial = materialEJB.findAll();
		listaSufijo = sufijoEJB.findAll();
	}
	
	public String referencia(){
		referencia = subcategoria.getCodigo() + material.getCodigo() + sufijo.getCodigo();
		return  referencia;
	}

	public List<Producto> getListaProductoPrecio() {
		return listaProductoPrecio;

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
			listaProductoPrecio = productoEJB.productoPrecio(idProducto);
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

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
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
	
	

}