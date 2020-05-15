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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_producto_especificacion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ProductoEspecificacion.findAll", query = "SELECT p FROM ProductoEspecificacion p")
	, @NamedQuery(name = "ProductoEspecificacion.findByIdProductoEspecificacion", query = "SELECT p FROM ProductoEspecificacion p WHERE p.idProductoEspecificacion = :idProductoEspecificacion")})
public class ProductoEspecificacion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO_ESPECIFICACION")
	private Integer idProductoEspecificacion;
	@Lob
    @Size(max = 65535)
    @Column(name = "OBSERVAQCIONES")
	private String observaqciones;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoEspecificacionIdProductoEspecificacion")
	private List<DisenoProducto> disenoProductoList;
	@JoinColumn(name = "TBL_ORDEN_PRODUCCION_ID_ORDEN_PRODUCCION", referencedColumnName = "ID_ORDEN_PRODUCCION")
    @ManyToOne(optional = false)
	private OrdenProduccion tblOrdenProduccionIdOrdenProduccion;
	@JoinColumn(name = "TBL_PRODUCTO_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
	private Producto tblProductoIdProducto;

	public ProductoEspecificacion() {
	}

	public ProductoEspecificacion(Integer idProductoEspecificacion) {
		this.idProductoEspecificacion = idProductoEspecificacion;
	}

	public Integer getIdProductoEspecificacion() {
		return idProductoEspecificacion;
	}

	public void setIdProductoEspecificacion(Integer idProductoEspecificacion) {
		this.idProductoEspecificacion = idProductoEspecificacion;
	}

	public String getObservaqciones() {
		return observaqciones;
	}

	public void setObservaqciones(String observaqciones) {
		this.observaqciones = observaqciones;
	}

	@XmlTransient
	public List<DisenoProducto> getDisenoProductoList() {
		return disenoProductoList;
	}

	public void setDisenoProductoList(List<DisenoProducto> disenoProductoList) {
		this.disenoProductoList = disenoProductoList;
	}

	public OrdenProduccion getTblOrdenProduccionIdOrdenProduccion() {
		return tblOrdenProduccionIdOrdenProduccion;
	}

	public void setTblOrdenProduccionIdOrdenProduccion(OrdenProduccion tblOrdenProduccionIdOrdenProduccion) {
		this.tblOrdenProduccionIdOrdenProduccion = tblOrdenProduccionIdOrdenProduccion;
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
		hash += (idProductoEspecificacion != null ? idProductoEspecificacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProductoEspecificacion)) {
			return false;
		}
		ProductoEspecificacion other = (ProductoEspecificacion) object;
		if ((this.idProductoEspecificacion == null && other.idProductoEspecificacion != null) || (this.idProductoEspecificacion != null && !this.idProductoEspecificacion.equals(other.idProductoEspecificacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.ProductoEspecificacion[ idProductoEspecificacion=" + idProductoEspecificacion + " ]";
	}
	
}
