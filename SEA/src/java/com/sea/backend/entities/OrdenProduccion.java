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
import javax.persistence.Lob;
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
@Table(name = "tbl_orden_produccion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "OrdenProduccion.findAll", query = "SELECT o FROM OrdenProduccion o")
	, @NamedQuery(name = "OrdenProduccion.findByIdOrdenProduccion", query = "SELECT o FROM OrdenProduccion o WHERE o.idOrdenProduccion = :idOrdenProduccion")
	, @NamedQuery(name = "OrdenProduccion.findByFechaExpedicion", query = "SELECT o FROM OrdenProduccion o WHERE o.fechaExpedicion = :fechaExpedicion")
	, @NamedQuery(name = "OrdenProduccion.findByCiudadExpedicion", query = "SELECT o FROM OrdenProduccion o WHERE o.ciudadExpedicion = :ciudadExpedicion")
	, @NamedQuery(name = "OrdenProduccion.findByTotalPrendas", query = "SELECT o FROM OrdenProduccion o WHERE o.totalPrendas = :totalPrendas")
	, @NamedQuery(name = "OrdenProduccion.findByEstado", query = "SELECT o FROM OrdenProduccion o WHERE o.estado = :estado")
	, @NamedQuery(name = "OrdenProduccion.findByFechaEntrega1", query = "SELECT o FROM OrdenProduccion o WHERE o.fechaEntrega1 = :fechaEntrega1")
	, @NamedQuery(name = "OrdenProduccion.findByFechaEntrega2", query = "SELECT o FROM OrdenProduccion o WHERE o.fechaEntrega2 = :fechaEntrega2")
	, @NamedQuery(name = "OrdenProduccion.findByFechaEntregaFinal", query = "SELECT o FROM OrdenProduccion o WHERE o.fechaEntregaFinal = :fechaEntregaFinal")})
public class OrdenProduccion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORDEN_PRODUCCION")
	private Integer idOrdenProduccion;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaExpedicion;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "CIUDAD_EXPEDICION")
	private String ciudadExpedicion;
	@Lob
    @Size(max = 65535)
    @Column(name = "OBSERVACIONES")
	private String observaciones;
	@Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PRENDAS")
	private int totalPrendas;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "ESTADO")
	private String estado;
	@Column(name = "FECHA_ENTREGA_1")
    @Temporal(TemporalType.DATE)
	private Date fechaEntrega1;
	@Column(name = "FECHA_ENTREGA_2")
    @Temporal(TemporalType.DATE)
	private Date fechaEntrega2;
	@Column(name = "FECHA_ENTREGA_FINAL")
    @Temporal(TemporalType.DATE)
	private Date fechaEntregaFinal;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrdenProduccionIdOrdenProduccion")
	private List<ProductoEspecificacion> productoEspecificacionList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrdenProduccionIdOrdenProduccion")
	private List<Devolucion> devolucionList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrdenProduccionIdOrdenProduccion")
	private List<ObservacionesOrdenProduccion> observacionesOrdenProduccionList;
	@JoinColumn(name = "TBL_COTIZACION_NUMERO_COTIZACION", referencedColumnName = "NUMERO_COTIZACION")
    @ManyToOne(optional = false)
	private Cotizacion tblCotizacionNumeroCotizacion;

	public OrdenProduccion() {
	}

	public OrdenProduccion(Integer idOrdenProduccion) {
		this.idOrdenProduccion = idOrdenProduccion;
	}

	public OrdenProduccion(Integer idOrdenProduccion, Date fechaExpedicion, String ciudadExpedicion, int totalPrendas, String estado) {
		this.idOrdenProduccion = idOrdenProduccion;
		this.fechaExpedicion = fechaExpedicion;
		this.ciudadExpedicion = ciudadExpedicion;
		this.totalPrendas = totalPrendas;
		this.estado = estado;
	}

	public Integer getIdOrdenProduccion() {
		return idOrdenProduccion;
	}

	public void setIdOrdenProduccion(Integer idOrdenProduccion) {
		this.idOrdenProduccion = idOrdenProduccion;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getCiudadExpedicion() {
		return ciudadExpedicion;
	}

	public void setCiudadExpedicion(String ciudadExpedicion) {
		this.ciudadExpedicion = ciudadExpedicion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getTotalPrendas() {
		return totalPrendas;
	}

	public void setTotalPrendas(int totalPrendas) {
		this.totalPrendas = totalPrendas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntrega1() {
		return fechaEntrega1;
	}

	public void setFechaEntrega1(Date fechaEntrega1) {
		this.fechaEntrega1 = fechaEntrega1;
	}

	public Date getFechaEntrega2() {
		return fechaEntrega2;
	}

	public void setFechaEntrega2(Date fechaEntrega2) {
		this.fechaEntrega2 = fechaEntrega2;
	}

	public Date getFechaEntregaFinal() {
		return fechaEntregaFinal;
	}

	public void setFechaEntregaFinal(Date fechaEntregaFinal) {
		this.fechaEntregaFinal = fechaEntregaFinal;
	}

	@XmlTransient
	public List<ProductoEspecificacion> getProductoEspecificacionList() {
		return productoEspecificacionList;
	}

	public void setProductoEspecificacionList(List<ProductoEspecificacion> productoEspecificacionList) {
		this.productoEspecificacionList = productoEspecificacionList;
	}

	@XmlTransient
	public List<Devolucion> getDevolucionList() {
		return devolucionList;
	}

	public void setDevolucionList(List<Devolucion> devolucionList) {
		this.devolucionList = devolucionList;
	}

	@XmlTransient
	public List<ObservacionesOrdenProduccion> getObservacionesOrdenProduccionList() {
		return observacionesOrdenProduccionList;
	}

	public void setObservacionesOrdenProduccionList(List<ObservacionesOrdenProduccion> observacionesOrdenProduccionList) {
		this.observacionesOrdenProduccionList = observacionesOrdenProduccionList;
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
		hash += (idOrdenProduccion != null ? idOrdenProduccion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof OrdenProduccion)) {
			return false;
		}
		OrdenProduccion other = (OrdenProduccion) object;
		if ((this.idOrdenProduccion == null && other.idOrdenProduccion != null) || (this.idOrdenProduccion != null && !this.idOrdenProduccion.equals(other.idOrdenProduccion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.OrdenProduccion[ idOrdenProduccion=" + idOrdenProduccion + " ]";
	}
	
}
