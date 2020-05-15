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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_tipo_direccion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TipoDireccion.findAll", query = "SELECT t FROM TipoDireccion t")
	, @NamedQuery(name = "TipoDireccion.findByIdTipoDireccion", query = "SELECT t FROM TipoDireccion t WHERE t.idTipoDireccion = :idTipoDireccion")
	, @NamedQuery(name = "TipoDireccion.findByTipo", query = "SELECT t FROM TipoDireccion t WHERE t.tipo = :tipo")})
public class TipoDireccion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_DIRECCION")
	private Integer idTipoDireccion;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "TIPO")
	private String tipo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblTipoDireccionIdTipoDireccion")
	private List<Direccion> direccionList;

	public TipoDireccion() {
	}

	public TipoDireccion(Integer idTipoDireccion) {
		this.idTipoDireccion = idTipoDireccion;
	}

	public TipoDireccion(Integer idTipoDireccion, String tipo) {
		this.idTipoDireccion = idTipoDireccion;
		this.tipo = tipo;
	}

	public Integer getIdTipoDireccion() {
		return idTipoDireccion;
	}

	public void setIdTipoDireccion(Integer idTipoDireccion) {
		this.idTipoDireccion = idTipoDireccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlTransient
	public List<Direccion> getDireccionList() {
		return direccionList;
	}

	public void setDireccionList(List<Direccion> direccionList) {
		this.direccionList = direccionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoDireccion != null ? idTipoDireccion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TipoDireccion)) {
			return false;
		}
		TipoDireccion other = (TipoDireccion) object;
		if ((this.idTipoDireccion == null && other.idTipoDireccion != null) || (this.idTipoDireccion != null && !this.idTipoDireccion.equals(other.idTipoDireccion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.TipoDireccion[ idTipoDireccion=" + idTipoDireccion + " ]";
	}
	
}
