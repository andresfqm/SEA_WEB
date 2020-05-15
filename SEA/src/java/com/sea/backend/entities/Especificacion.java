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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_especificacion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Especificacion.findAll", query = "SELECT e FROM Especificacion e")
	, @NamedQuery(name = "Especificacion.findByIdEspecificacion", query = "SELECT e FROM Especificacion e WHERE e.idEspecificacion = :idEspecificacion")})
public class Especificacion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESPECIFICACION")
	private Integer idEspecificacion;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@ManyToMany(mappedBy = "especificacionList")
	private List<Producto> productoList;

	public Especificacion() {
	}

	public Especificacion(Integer idEspecificacion) {
		this.idEspecificacion = idEspecificacion;
	}

	public Especificacion(Integer idEspecificacion, String descripcion) {
		this.idEspecificacion = idEspecificacion;
		this.descripcion = descripcion;
	}

	public Integer getIdEspecificacion() {
		return idEspecificacion;
	}

	public void setIdEspecificacion(Integer idEspecificacion) {
		this.idEspecificacion = idEspecificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlTransient
	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEspecificacion != null ? idEspecificacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Especificacion)) {
			return false;
		}
		Especificacion other = (Especificacion) object;
		if ((this.idEspecificacion == null && other.idEspecificacion != null) || (this.idEspecificacion != null && !this.idEspecificacion.equals(other.idEspecificacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Especificacion[ idEspecificacion=" + idEspecificacion + " ]";
	}
	
}
