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
@Table(name = "tbl_registro_seguimiento")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "RegistroSeguimiento.findAll", query = "SELECT r FROM RegistroSeguimiento r")
	, @NamedQuery(name = "RegistroSeguimiento.findByIdRegistroSeguimiento", query = "SELECT r FROM RegistroSeguimiento r WHERE r.idRegistroSeguimiento = :idRegistroSeguimiento")
	, @NamedQuery(name = "RegistroSeguimiento.findByFechaRegistro", query = "SELECT r FROM RegistroSeguimiento r WHERE r.fechaRegistro = :fechaRegistro")})
public class RegistroSeguimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_SEGUIMIENTO")
	private Integer idRegistroSeguimiento;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "OBSERVACIONES")
	private String observaciones;
	@JoinColumn(name = "TBL_COTIZACION_NUMERO_COTIZACION", referencedColumnName = "NUMERO_COTIZACION")
    @ManyToOne(optional = false)
	private Cotizacion tblCotizacionNumeroCotizacion;

	public RegistroSeguimiento() {
	}

	public RegistroSeguimiento(Integer idRegistroSeguimiento) {
		this.idRegistroSeguimiento = idRegistroSeguimiento;
	}

	public RegistroSeguimiento(Integer idRegistroSeguimiento, Date fechaRegistro, String observaciones) {
		this.idRegistroSeguimiento = idRegistroSeguimiento;
		this.fechaRegistro = fechaRegistro;
		this.observaciones = observaciones;
	}

	public Integer getIdRegistroSeguimiento() {
		return idRegistroSeguimiento;
	}

	public void setIdRegistroSeguimiento(Integer idRegistroSeguimiento) {
		this.idRegistroSeguimiento = idRegistroSeguimiento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cotizacion getTblCotizacionNumeroCotizacion() {
		return tblCotizacionNumeroCotizacion;
	}

	public void setTblCotizacionNumeroCotizacion(Cotizacion tblCotizacionNumeroCotizacion) {
		this.tblCotizacionNumeroCotizacion = tblCotizacionNumeroCotizacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idRegistroSeguimiento != null ? idRegistroSeguimiento.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RegistroSeguimiento)) {
			return false;
		}
		RegistroSeguimiento other = (RegistroSeguimiento) object;
		if ((this.idRegistroSeguimiento == null && other.idRegistroSeguimiento != null) || (this.idRegistroSeguimiento != null && !this.idRegistroSeguimiento.equals(other.idRegistroSeguimiento))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.RegistroSeguimiento[ idRegistroSeguimiento=" + idRegistroSeguimiento + " ]";
	}
	
}
