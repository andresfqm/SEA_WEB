/*
 * The MIT License
 *
 * Copyright 2017 Depurador.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sea.backend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_producto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
	, @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
	, @NamedQuery(name = "Producto.findByReferencia", query = "SELECT p FROM Producto p WHERE p.referencia = :referencia")
	, @NamedQuery(name = "Producto.findByFechaActualizacion", query = "SELECT p FROM Producto p WHERE p.fechaActualizacion = :fechaActualizacion")
	, @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
	, @NamedQuery(name = "Producto.findByPersonalizado", query = "SELECT p FROM Producto p WHERE p.personalizado = :personalizado")})
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
	private Integer idProducto;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "REFERENCIA")
	private String referencia;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
	private Date fechaActualizacion;
	@Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
	private float precio;
	@Column(name = "PERSONALIZADO")
	private Boolean personalizado;
	@JoinTable(name = "tbl_especificacion_producto", joinColumns = {
    	@JoinColumn(name = "TBL_PRODUCTO_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")}, inverseJoinColumns = {
    	@JoinColumn(name = "TBL_ESPECIFICACION_ID_ESPECIFICACION", referencedColumnName = "ID_ESPECIFICACION")})
    @ManyToMany
	private List<Especificacion> especificacionList;
	@JoinTable(name = "tbl_producto_sufijo", joinColumns = {
    	@JoinColumn(name = "TBL_PRODUCTO_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")}, inverseJoinColumns = {
    	@JoinColumn(name = "TBL_SUFIJO_ID_SUFIJO", referencedColumnName = "ID_SUFIJO")})
    @ManyToMany
	private List<Sufijo> sufijoList;
	@ManyToMany(mappedBy = "productoList")
	private List<Material> materialList;
	@ManyToMany(mappedBy = "productoList")
	private List<Descuento> descuentoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoIdProducto")
	private List<CotizacionProducto> cotizacionProductoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoIdProducto")
	private List<ProductoEspecificacion> productoEspecificacionList;
	@JoinColumn(name = "TBL_FABRICANTE_ID_FABRICANTE", referencedColumnName = "ID_FABRICANTE")
    @ManyToOne
	private Fabricante tblFabricanteIdFabricante;
	@JoinColumn(name = "TBL_SUBCATEGORIA_ID_SUBCATEGORIA", referencedColumnName = "ID_SUBCATEGORIA")
    @ManyToOne(optional = false)
	private Subcategoria tblSubcategoriaIdSubcategoria;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoIdProducto")
	private List<RegistroCosto> registroCostoList;

	public Producto() {
	}

	public Producto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Producto(Integer idProducto, String referencia, String descripcion, float precio) {
		this.idProducto = idProducto;
		this.referencia = referencia;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Boolean getPersonalizado() {
		return personalizado;
	}

	public void setPersonalizado(Boolean personalizado) {
		this.personalizado = personalizado;
	}

	@XmlTransient
	public List<Especificacion> getEspecificacionList() {
		return especificacionList;
	}

	public void setEspecificacionList(List<Especificacion> especificacionList) {
		this.especificacionList = especificacionList;
	}

	@XmlTransient
	public List<Sufijo> getSufijoList() {
		return sufijoList;
	}

	public void setSufijoList(List<Sufijo> sufijoList) {
		this.sufijoList = sufijoList;
	}

	@XmlTransient
	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	@XmlTransient
	public List<Descuento> getDescuentoList() {
		return descuentoList;
	}

	public void setDescuentoList(List<Descuento> descuentoList) {
		this.descuentoList = descuentoList;
	}

	@XmlTransient
	public List<CotizacionProducto> getCotizacionProductoList() {
		return cotizacionProductoList;
	}

	public void setCotizacionProductoList(List<CotizacionProducto> cotizacionProductoList) {
		this.cotizacionProductoList = cotizacionProductoList;
	}

	@XmlTransient
	public List<ProductoEspecificacion> getProductoEspecificacionList() {
		return productoEspecificacionList;
	}

	public void setProductoEspecificacionList(List<ProductoEspecificacion> productoEspecificacionList) {
		this.productoEspecificacionList = productoEspecificacionList;
	}

	public Fabricante getTblFabricanteIdFabricante() {
		return tblFabricanteIdFabricante;
	}

	public void setTblFabricanteIdFabricante(Fabricante tblFabricanteIdFabricante) {
		this.tblFabricanteIdFabricante = tblFabricanteIdFabricante;
	}

	public Subcategoria getTblSubcategoriaIdSubcategoria() {
		return tblSubcategoriaIdSubcategoria;
	}

	public void setTblSubcategoriaIdSubcategoria(Subcategoria tblSubcategoriaIdSubcategoria) {
		this.tblSubcategoriaIdSubcategoria = tblSubcategoriaIdSubcategoria;
	}

	@XmlTransient
	public List<RegistroCosto> getRegistroCostoList() {
		return registroCostoList;
	}

	public void setRegistroCostoList(List<RegistroCosto> registroCostoList) {
		this.registroCostoList = registroCostoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idProducto != null ? idProducto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Producto)) {
			return false;
		}
		Producto other = (Producto) object;
		if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Producto[ idProducto=" + idProducto + " ]";
	}
	
}
