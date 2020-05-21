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
@Table(name = "tbl_cotizacion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
	, @NamedQuery(name = "Cotizacion.findByNumeroCotizacion", query = "SELECT c FROM Cotizacion c WHERE c.numeroCotizacion = :numeroCotizacion")
	, @NamedQuery(name = "Cotizacion.findByFechaEmision", query = "SELECT c FROM Cotizacion c WHERE c.fechaEmision = :fechaEmision")
	, @NamedQuery(name = "Cotizacion.findByLugarEmision", query = "SELECT c FROM Cotizacion c WHERE c.lugarEmision = :lugarEmision")
	, @NamedQuery(name = "Cotizacion.findByValidezOferta", query = "SELECT c FROM Cotizacion c WHERE c.validezOferta = :validezOferta")
	, @NamedQuery(name = "Cotizacion.findByDescuento", query = "SELECT c FROM Cotizacion c WHERE c.descuento = :descuento")
	, @NamedQuery(name = "Cotizacion.findByIva", query = "SELECT c FROM Cotizacion c WHERE c.iva = :iva")
	, @NamedQuery(name = "Cotizacion.findByFechaCierreEfectivo", query = "SELECT c FROM Cotizacion c WHERE c.fechaCierreEfectivo = :fechaCierreEfectivo")
	, @NamedQuery(name = "Cotizacion.findByCancelada", query = "SELECT c FROM Cotizacion c WHERE c.cancelada = :cancelada")
	, @NamedQuery(name = "Cotizacion.findByVisita", query = "SELECT c FROM Cotizacion c WHERE c.visita = :visita")
	, @NamedQuery(name = "Cotizacion.findByPrestamoMuestra", query = "SELECT c FROM Cotizacion c WHERE c.prestamoMuestra = :prestamoMuestra")
	, @NamedQuery(name = "Cotizacion.findByNumeroRemision", query = "SELECT c FROM Cotizacion c WHERE c.numeroRemision = :numeroRemision")
	, @NamedQuery(name = "Cotizacion.findByEstado", query = "SELECT c FROM Cotizacion c WHERE c.estado = :estado")
	, @NamedQuery(name = "Cotizacion.findByFechaFacturacion", query = "SELECT c FROM Cotizacion c WHERE c.fechaFacturacion = :fechaFacturacion")})
public class Cotizacion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "NUMERO_COTIZACION")
	private String numeroCotizacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "FECHA_EMISION")
	@Temporal(TemporalType.DATE)
	private Date fechaEmision;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "LUGAR_EMISION")
	private String lugarEmision;
	@Basic(optional = false)
	@NotNull
	@Column(name = "VALIDEZ_OFERTA")
	private int validezOferta;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DESCUENTO")
	private float descuento;
	@Basic(optional = false)
	@NotNull
	@Column(name = "IVA")
	private float iva;
	@Column(name = "FECHA_CIERRE_EFECTIVO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCierreEfectivo;
	@Column(name = "CANCELADA")
	private Boolean cancelada;
	@Column(name = "VISITA")
	private Boolean visita;
	@Column(name = "PRESTAMO_MUESTRA")
	private Boolean prestamoMuestra;
	@Lob
	@Size(max = 65535)
	@Column(name = "RELACION_MUESTRA")
	private String relacionMuestra;
	@Size(max = 16)
	@Column(name = "NUMERO_REMISION")
	private String numeroRemision;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 15)
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "FECHA_FACTURACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFacturacion;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TOTAL_DESCUENTO")
	private float totalDescuento;
	@Basic(optional = false)
	@NotNull
	@Column(name = "SUBTOTAL")
	private float subtotal;
	@Basic(optional = false)
	@NotNull
	@Column(name = "TOTAL_IVA")
	private float totalIva;
	@Basic(optional = false)
	@NotNull
	@Column(name = "VALOR_TOTAL")
	private float valorTotal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCotizacionNumeroCotizacion")
	private List<CotizacionProducto> cotizacionProductoList;
	@JoinColumn(name = "TBL_CLIENTE_ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	@ManyToOne(optional = false)
	private Cliente tblClienteIdCliente;
	@JoinColumn(name = "TBL_USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
	@ManyToOne(optional = false)
	private Usuario tblUsuario;
	@JoinColumn(name = "TBL_DESCUENTO_VOLUMEN_ID_DESCUENTO_VOLUMEN", referencedColumnName = "ID_DESCUENTO_VOLUMEN")
	@ManyToOne(optional = false)
	private DescuentoVolumen tblDescuentoVolumenIdDescuentoVolumen;
	@JoinColumn(name = "TBL_LUGARES_ENTREGA_ID_LUGARES_ENTREGA", referencedColumnName = "ID_LUGARES_ENTREGA")
	@ManyToOne(optional = false)
	private LugaresEntrega tblLugaresEntregaIdLugaresEntrega;
	@JoinColumn(name = "TBL_MODALIDAD_DE_PAGO_ID_MODALIDAD_DE_PAGO", referencedColumnName = "ID_MODALIDAD_DE_PAGO")
	@ManyToOne(optional = false)
	private ModalidadDePago tblModalidadDePagoIdModalidadDePago;
	@JoinColumn(name = "TBL_PROPUESTA_NO_INCLUYE_ID_PROPUESTA_NO_INCLUYE", referencedColumnName = "ID_PROPUESTA_NO_INCLUYE")
	@ManyToOne(optional = false)
	private PropuestaNoIncluye tblPropuestaNoIncluyeIdPropuestaNoIncluye;
	@JoinColumn(name = "TBL_TIEMPO_ENTREGA_ID_TIEMPO_ENTREGA", referencedColumnName = "ID_TIEMPO_ENTREGA")
	@ManyToOne(optional = false)
	private TiempoEntrega tblTiempoEntregaIdTiempoEntrega;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCotizacionNumeroCotizacion")
	private List<OrdenProduccion> ordenProduccionList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCotizacionNumeroCotizacion")
	private List<RegistroSeguimiento> registroSeguimientoList;

	public Cotizacion() {
	}

	public Cotizacion(String numeroCotizacion) {
		this.numeroCotizacion = numeroCotizacion;
	}

	public Cotizacion(String numeroCotizacion, Date fechaEmision, String lugarEmision, int validezOferta, float descuento, float iva, String estado) {
		this.numeroCotizacion = numeroCotizacion;
		this.fechaEmision = fechaEmision;
		this.lugarEmision = lugarEmision;
		this.validezOferta = validezOferta;
		this.descuento = descuento;
		this.iva = iva;
		this.estado = estado;
	}

	public String getNumeroCotizacion() {
		return numeroCotizacion;
	}

	public void setNumeroCotizacion(String numeroCotizacion) {
		this.numeroCotizacion = numeroCotizacion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getLugarEmision() {
		return lugarEmision;
	}

	public void setLugarEmision(String lugarEmision) {
		this.lugarEmision = lugarEmision;
	}

	public int getValidezOferta() {
		return validezOferta;
	}

	public void setValidezOferta(int validezOferta) {
		this.validezOferta = validezOferta;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public Date getFechaCierreEfectivo() {
		return fechaCierreEfectivo;
	}

	public void setFechaCierreEfectivo(Date fechaCierreEfectivo) {
		this.fechaCierreEfectivo = fechaCierreEfectivo;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Boolean getVisita() {
		return visita;
	}

	public void setVisita(Boolean visita) {
		this.visita = visita;
	}

	public Boolean getPrestamoMuestra() {
		return prestamoMuestra;
	}

	public void setPrestamoMuestra(Boolean prestamoMuestra) {
		this.prestamoMuestra = prestamoMuestra;
	}

	public String getRelacionMuestra() {
		return relacionMuestra;
	}

	public void setRelacionMuestra(String relacionMuestra) {
		this.relacionMuestra = relacionMuestra;
	}

	public String getNumeroRemision() {
		return numeroRemision;
	}

	public void setNumeroRemision(String numeroRemision) {
		this.numeroRemision = numeroRemision;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	@XmlTransient
	public List<CotizacionProducto> getCotizacionProductoList() {
		return cotizacionProductoList;
	}

	public void setCotizacionProductoList(List<CotizacionProducto> cotizacionProductoList) {
		this.cotizacionProductoList = cotizacionProductoList;
	}

	public Cliente getTblClienteIdCliente() {
		return tblClienteIdCliente;
	}

	public void setTblClienteIdCliente(Cliente tblClienteIdCliente) {
		this.tblClienteIdCliente = tblClienteIdCliente;
	}

	public DescuentoVolumen getTblDescuentoVolumenIdDescuentoVolumen() {
		return tblDescuentoVolumenIdDescuentoVolumen;
	}

	public void setTblDescuentoVolumenIdDescuentoVolumen(DescuentoVolumen tblDescuentoVolumenIdDescuentoVolumen) {
		this.tblDescuentoVolumenIdDescuentoVolumen = tblDescuentoVolumenIdDescuentoVolumen;
	}

	public LugaresEntrega getTblLugaresEntregaIdLugaresEntrega() {
		return tblLugaresEntregaIdLugaresEntrega;
	}

	public void setTblLugaresEntregaIdLugaresEntrega(LugaresEntrega tblLugaresEntregaIdLugaresEntrega) {
		this.tblLugaresEntregaIdLugaresEntrega = tblLugaresEntregaIdLugaresEntrega;
	}

	public ModalidadDePago getTblModalidadDePagoIdModalidadDePago() {
		return tblModalidadDePagoIdModalidadDePago;
	}

	public void setTblModalidadDePagoIdModalidadDePago(ModalidadDePago tblModalidadDePagoIdModalidadDePago) {
		this.tblModalidadDePagoIdModalidadDePago = tblModalidadDePagoIdModalidadDePago;
	}

	public PropuestaNoIncluye getTblPropuestaNoIncluyeIdPropuestaNoIncluye() {
		return tblPropuestaNoIncluyeIdPropuestaNoIncluye;
	}

	public void setTblPropuestaNoIncluyeIdPropuestaNoIncluye(PropuestaNoIncluye tblPropuestaNoIncluyeIdPropuestaNoIncluye) {
		this.tblPropuestaNoIncluyeIdPropuestaNoIncluye = tblPropuestaNoIncluyeIdPropuestaNoIncluye;
	}

	public TiempoEntrega getTblTiempoEntregaIdTiempoEntrega() {
		return tblTiempoEntregaIdTiempoEntrega;
	}

	public void setTblTiempoEntregaIdTiempoEntrega(TiempoEntrega tblTiempoEntregaIdTiempoEntrega) {
		this.tblTiempoEntregaIdTiempoEntrega = tblTiempoEntregaIdTiempoEntrega;
	}

	@XmlTransient
	public List<OrdenProduccion> getOrdenProduccionList() {
		return ordenProduccionList;
	}

	public void setOrdenProduccionList(List<OrdenProduccion> ordenProduccionList) {
		this.ordenProduccionList = ordenProduccionList;
	}

	@XmlTransient
	public List<RegistroSeguimiento> getRegistroSeguimientoList() {
		return registroSeguimientoList;
	}

	public void setRegistroSeguimientoList(List<RegistroSeguimiento> registroSeguimientoList) {
		this.registroSeguimientoList = registroSeguimientoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (numeroCotizacion != null ? numeroCotizacion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Cotizacion)) {
			return false;
		}
		Cotizacion other = (Cotizacion) object;
		if ((this.numeroCotizacion == null && other.numeroCotizacion != null) || (this.numeroCotizacion != null && !this.numeroCotizacion.equals(other.numeroCotizacion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Cotizacion[ numeroCotizacion=" + numeroCotizacion + " ]";
	}

	public Usuario getTblUsuario() {
		return tblUsuario;
	}

	public void setTblUsuario(Usuario tblUsuario) {
		this.tblUsuario = tblUsuario;
	}

	public float getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(float totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(float totalIva) {
		this.totalIva = totalIva;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
