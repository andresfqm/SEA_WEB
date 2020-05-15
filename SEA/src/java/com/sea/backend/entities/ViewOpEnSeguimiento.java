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
import javax.persistence.Id;
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
@Table(name = "view_op_en_seguimiento")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewOpEnSeguimiento.findAll", query = "SELECT v FROM ViewOpEnSeguimiento v")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByUsuario", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.usuario = :usuario")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByOrdenProduccion", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.ordenProduccion = :ordenProduccion")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByCliente", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.cliente = :cliente")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByDocumento", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.documento = :documento")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByFechaExpedicion", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.fechaExpedicion = :fechaExpedicion")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByFechaEntrega1", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.fechaEntrega1 = :fechaEntrega1")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByFechaEntrega2", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.fechaEntrega2 = :fechaEntrega2")
	, @NamedQuery(name = "ViewOpEnSeguimiento.findByFechaEntregaFinal", query = "SELECT v FROM ViewOpEnSeguimiento v WHERE v.fechaEntregaFinal = :fechaEntregaFinal")})
public class ViewOpEnSeguimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
	private int usuario;
	@Basic(optional = false)
    @NotNull
    @Column(name = "orden_produccion")
	@Id
	private int ordenProduccion;
	@Size(max = 129)
    @Column(name = "cliente")
	private String cliente;
	@Size(max = 41)
    @Column(name = "documento")
	private String documento;
	@Basic(optional = false)
    @NotNull
    @Column(name = "fecha_expedicion")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaExpedicion;
	@Column(name = "fecha_entrega_1")
    @Temporal(TemporalType.DATE)
	private Date fechaEntrega1;
	@Column(name = "fecha_entrega_2")
    @Temporal(TemporalType.DATE)
	private Date fechaEntrega2;
	@Column(name = "fecha_entrega_final")
    @Temporal(TemporalType.DATE)
	private Date fechaEntregaFinal;

	public ViewOpEnSeguimiento() {
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getOrdenProduccion() {
		return ordenProduccion;
	}

	public void setOrdenProduccion(int ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
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
	
}
