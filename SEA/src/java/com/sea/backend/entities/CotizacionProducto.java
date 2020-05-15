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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_cotizacion_producto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "CotizacionProducto.findAll", query = "SELECT c FROM CotizacionProducto c")
	, @NamedQuery(name = "CotizacionProducto.findByIdCotizacionProducto", query = "SELECT c FROM CotizacionProducto c WHERE c.idCotizacionProducto = :idCotizacionProducto")
	, @NamedQuery(name = "CotizacionProducto.findByPrecioParaCliente", query = "SELECT c FROM CotizacionProducto c WHERE c.precioParaCliente = :precioParaCliente")
	, @NamedQuery(name = "CotizacionProducto.findByPrecioBase", query = "SELECT c FROM CotizacionProducto c WHERE c.precioBase = :precioBase")
	, @NamedQuery(name = "CotizacionProducto.findByCantidad", query = "SELECT c FROM CotizacionProducto c WHERE c.cantidad = :cantidad")
	, @NamedQuery(name = "CotizacionProducto.findByCotizacion", query = "SELECT c FROM CotizacionProducto c WHERE c.tblCotizacionNumeroCotizacion = :tblCotizacionNumeroCotizacion")})
public class CotizacionProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COTIZACION_PRODUCTO")
	private Integer idCotizacionProducto;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "PRECIO_PARA_CLIENTE")
	private Float precioParaCliente;
	@Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_BASE")
	private float precioBase;
	@Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
	private int cantidad;
	@JoinColumn(name = "TBL_COTIZACION_NUMERO_COTIZACION", referencedColumnName = "NUMERO_COTIZACION")
    @ManyToOne(optional = false)
	private Cotizacion tblCotizacionNumeroCotizacion;
	@JoinColumn(name = "TBL_PRODUCTO_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
	private Producto tblProductoIdProducto;

	public CotizacionProducto() {
	}

	public CotizacionProducto(Integer idCotizacionProducto) {
		this.idCotizacionProducto = idCotizacionProducto;
	}

	public CotizacionProducto(Integer idCotizacionProducto, float precioBase, int cantidad) {
		this.idCotizacionProducto = idCotizacionProducto;
		this.precioBase = precioBase;
		this.cantidad = cantidad;
	}

	public Integer getIdCotizacionProducto() {
		return idCotizacionProducto;
	}

	public void setIdCotizacionProducto(Integer idCotizacionProducto) {
		this.idCotizacionProducto = idCotizacionProducto;
	}

	public Float getPrecioParaCliente() {
		return precioParaCliente;
	}

	public void setPrecioParaCliente(Float precioParaCliente) {
		this.precioParaCliente = precioParaCliente;
	}

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cotizacion getTblCotizacionNumeroCotizacion() {
		return tblCotizacionNumeroCotizacion;
	}

	public void setTblCotizacionNumeroCotizacion(Cotizacion tblCotizacionNumeroCotizacion) {
		this.tblCotizacionNumeroCotizacion = tblCotizacionNumeroCotizacion;
	}

	public Producto getTblProductoIdProducto() {
		return tblProductoIdProducto;
	}

	public void setTblProductoIdProducto(Producto tblProductoIdProducto) {
		this.tblProductoIdProducto = tblProductoIdProducto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCotizacionProducto != null ? idCotizacionProducto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CotizacionProducto)) {
			return false;
		}
		CotizacionProducto other = (CotizacionProducto) object;
		if ((this.idCotizacionProducto == null && other.idCotizacionProducto != null) || (this.idCotizacionProducto != null && !this.idCotizacionProducto.equals(other.idCotizacionProducto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.CotizacionProducto[ idCotizacionProducto=" + idCotizacionProducto + " ]";
	}
	
}
