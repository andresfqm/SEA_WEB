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
@Table(name = "view_op_por_estado")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewOpPorEstado.findAll", query = "SELECT v FROM ViewOpPorEstado v")
	, @NamedQuery(name = "ViewOpPorEstado.findByUsuario", query = "SELECT v FROM ViewOpPorEstado v WHERE v.usuario = :usuario")
	, @NamedQuery(name = "ViewOpPorEstado.findByOrdenProduccion", query = "SELECT v FROM ViewOpPorEstado v WHERE v.ordenProduccion = :ordenProduccion")
	, @NamedQuery(name = "ViewOpPorEstado.findByCliente", query = "SELECT v FROM ViewOpPorEstado v WHERE v.cliente = :cliente")
	, @NamedQuery(name = "ViewOpPorEstado.findByDocumento", query = "SELECT v FROM ViewOpPorEstado v WHERE v.documento = :documento")
	, @NamedQuery(name = "ViewOpPorEstado.findByFechaExpedicion", query = "SELECT v FROM ViewOpPorEstado v WHERE v.fechaExpedicion = :fechaExpedicion")
	, @NamedQuery(name = "ViewOpPorEstado.findByEstado", query = "SELECT v FROM ViewOpPorEstado v WHERE v.estado = :estado")
	, @NamedQuery(name = "ViewOpPorEstado.findByUsuarioAndEstado", query = "SELECT v FROM ViewOpPorEstado v WHERE v.usuario = :usuario and v.estado = :estado")})
public class ViewOpPorEstado implements Serializable {

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
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "estado")
	private String estado;

	public ViewOpPorEstado() {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
