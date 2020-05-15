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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_talla_diseno_producto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TallaDisenoProducto.findAll", query = "SELECT t FROM TallaDisenoProducto t")
	, @NamedQuery(name = "TallaDisenoProducto.findByIdTallaDisenoProducto", query = "SELECT t FROM TallaDisenoProducto t WHERE t.idTallaDisenoProducto = :idTallaDisenoProducto")
	, @NamedQuery(name = "TallaDisenoProducto.findByCantidad", query = "SELECT t FROM TallaDisenoProducto t WHERE t.cantidad = :cantidad")})
public class TallaDisenoProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ID_TALLA_DISENO_PRODUCTO")
	private String idTallaDisenoProducto;
	@Size(max = 45)
    @Column(name = "CANTIDAD")
	private String cantidad;
	@JoinColumn(name = "TBL_DISENO_PRODUCTO_ID_DISENO_PRODUCTO", referencedColumnName = "ID_DISENO_PRODUCTO")
    @ManyToOne(optional = false)
	private DisenoProducto tblDisenoProductoIdDisenoProducto;
	@JoinColumn(name = "TBL_TALLA_ID_TALLA", referencedColumnName = "ID_TALLA")
    @ManyToOne(optional = false)
	private Talla tblTallaIdTalla;

	public TallaDisenoProducto() {
	}

	public TallaDisenoProducto(String idTallaDisenoProducto) {
		this.idTallaDisenoProducto = idTallaDisenoProducto;
	}

	public String getIdTallaDisenoProducto() {
		return idTallaDisenoProducto;
	}

	public void setIdTallaDisenoProducto(String idTallaDisenoProducto) {
		this.idTallaDisenoProducto = idTallaDisenoProducto;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public DisenoProducto getTblDisenoProductoIdDisenoProducto() {
		return tblDisenoProductoIdDisenoProducto;
	}

	public void setTblDisenoProductoIdDisenoProducto(DisenoProducto tblDisenoProductoIdDisenoProducto) {
		this.tblDisenoProductoIdDisenoProducto = tblDisenoProductoIdDisenoProducto;
	}

	public Talla getTblTallaIdTalla() {
		return tblTallaIdTalla;
	}

	public void setTblTallaIdTalla(Talla tblTallaIdTalla) {
		this.tblTallaIdTalla = tblTallaIdTalla;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTallaDisenoProducto != null ? idTallaDisenoProducto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TallaDisenoProducto)) {
			return false;
		}
		TallaDisenoProducto other = (TallaDisenoProducto) object;
		if ((this.idTallaDisenoProducto == null && other.idTallaDisenoProducto != null) || (this.idTallaDisenoProducto != null && !this.idTallaDisenoProducto.equals(other.idTallaDisenoProducto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.TallaDisenoProducto[ idTallaDisenoProducto=" + idTallaDisenoProducto + " ]";
	}
	
}
