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
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_observaciones_orden_produccion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ObservacionesOrdenProduccion.findAll", query = "SELECT o FROM ObservacionesOrdenProduccion o")
	, @NamedQuery(name = "ObservacionesOrdenProduccion.findByIdObservaciones", query = "SELECT o FROM ObservacionesOrdenProduccion o WHERE o.idObservaciones = :idObservaciones")
	, @NamedQuery(name = "ObservacionesOrdenProduccion.findByFechaObservacion", query = "SELECT o FROM ObservacionesOrdenProduccion o WHERE o.fechaObservacion = :fechaObservacion")
	, @NamedQuery(name = "ObservacionesOrdenProduccion.findByIdOrdenProduccion", query = "SELECT o FROM ObservacionesOrdenProduccion o WHERE o.tblOrdenProduccionIdOrdenProduccion = :tblOrdenProduccionIdOrdenProduccion")})
public class ObservacionesOrdenProduccion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OBSERVACIONES")
	private Integer idObservaciones;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_OBSERVACION")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaObservacion;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@JoinColumn(name = "TBL_ORDEN_PRODUCCION_ID_ORDEN_PRODUCCION", referencedColumnName = "ID_ORDEN_PRODUCCION")
    @ManyToOne(optional = false)
	private OrdenProduccion tblOrdenProduccionIdOrdenProduccion;

	public ObservacionesOrdenProduccion() {
	}

	public ObservacionesOrdenProduccion(Integer idObservaciones) {
		this.idObservaciones = idObservaciones;
	}

	public ObservacionesOrdenProduccion(Integer idObservaciones, Date fechaObservacion, String descripcion) {
		this.idObservaciones = idObservaciones;
		this.fechaObservacion = fechaObservacion;
		this.descripcion = descripcion;
	}

	public Integer getIdObservaciones() {
		return idObservaciones;
	}

	public void setIdObservaciones(Integer idObservaciones) {
		this.idObservaciones = idObservaciones;
	}

	public Date getFechaObservacion() {
		return fechaObservacion;
	}

	public void setFechaObservacion(Date fechaObservacion) {
		this.fechaObservacion = fechaObservacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public OrdenProduccion getTblOrdenProduccionIdOrdenProduccion() {
		return tblOrdenProduccionIdOrdenProduccion;
	}

	public void setTblOrdenProduccionIdOrdenProduccion(OrdenProduccion tblOrdenProduccionIdOrdenProduccion) {
		this.tblOrdenProduccionIdOrdenProduccion = tblOrdenProduccionIdOrdenProduccion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idObservaciones != null ? idObservaciones.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ObservacionesOrdenProduccion)) {
			return false;
		}
		ObservacionesOrdenProduccion other = (ObservacionesOrdenProduccion) object;
		if ((this.idObservaciones == null && other.idObservaciones != null) || (this.idObservaciones != null && !this.idObservaciones.equals(other.idObservaciones))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.ObservacionesOrdenProduccion[ idObservaciones=" + idObservaciones + " ]";
	}
	
}
