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
import javax.persistence.Lob;
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
@Table(name = "tbl_tiempo_entrega")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TiempoEntrega.findAll", query = "SELECT t FROM TiempoEntrega t")
	, @NamedQuery(name = "TiempoEntrega.findByIdTiempoEntrega", query = "SELECT t FROM TiempoEntrega t WHERE t.idTiempoEntrega = :idTiempoEntrega")
	, @NamedQuery(name = "TiempoEntrega.findByActivo", query = "SELECT t FROM TiempoEntrega t WHERE t.activo = :activo")})
public class TiempoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIEMPO_ENTREGA")
	private Integer idTiempoEntrega;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
	private boolean activo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblTiempoEntregaIdTiempoEntrega")
	private List<Cotizacion> cotizacionList;

	public TiempoEntrega() {
	}

	public TiempoEntrega(Integer idTiempoEntrega) {
		this.idTiempoEntrega = idTiempoEntrega;
	}

	public TiempoEntrega(Integer idTiempoEntrega, String descripcion, boolean activo) {
		this.idTiempoEntrega = idTiempoEntrega;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Integer getIdTiempoEntrega() {
		return idTiempoEntrega;
	}

	public void setIdTiempoEntrega(Integer idTiempoEntrega) {
		this.idTiempoEntrega = idTiempoEntrega;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@XmlTransient
	public List<Cotizacion> getCotizacionList() {
		return cotizacionList;
	}

	public void setCotizacionList(List<Cotizacion> cotizacionList) {
		this.cotizacionList = cotizacionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTiempoEntrega != null ? idTiempoEntrega.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TiempoEntrega)) {
			return false;
		}
		TiempoEntrega other = (TiempoEntrega) object;
		if ((this.idTiempoEntrega == null && other.idTiempoEntrega != null) || (this.idTiempoEntrega != null && !this.idTiempoEntrega.equals(other.idTiempoEntrega))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.TiempoEntrega[ idTiempoEntrega=" + idTiempoEntrega + " ]";
	}
	
}
