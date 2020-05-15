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
import javax.persistence.Lob;
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
@Table(name = "view_articulos_por_actualizar")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewArticulosPorActualizar.findAll", query = "SELECT v FROM ViewArticulosPorActualizar v")
	, @NamedQuery(name = "ViewArticulosPorActualizar.findByReferencia", query = "SELECT v FROM ViewArticulosPorActualizar v WHERE v.referencia = :referencia")
	, @NamedQuery(name = "ViewArticulosPorActualizar.findByFabricante", query = "SELECT v FROM ViewArticulosPorActualizar v WHERE v.fabricante = :fabricante")
	, @NamedQuery(name = "ViewArticulosPorActualizar.findByCosto", query = "SELECT v FROM ViewArticulosPorActualizar v WHERE v.costo = :costo")
	, @NamedQuery(name = "ViewArticulosPorActualizar.findByPrecio", query = "SELECT v FROM ViewArticulosPorActualizar v WHERE v.precio = :precio")
	, @NamedQuery(name = "ViewArticulosPorActualizar.findByFechaActualizacion", query = "SELECT v FROM ViewArticulosPorActualizar v WHERE v.fechaActualizacion = :fechaActualizacion")})
public class ViewArticulosPorActualizar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "referencia")
	@Id
	private String referencia;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
	private String descripcion;
	@Size(max = 64)
    @Column(name = "fabricante")
	private String fabricante;
	@Basic(optional = false)
    @NotNull
    @Column(name = "costo")
	private float costo;
	@Basic(optional = false)
    @NotNull
    @Column(name = "precio")
	private float precio;
	@Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
	private Date fechaActualizacion;

	public ViewArticulosPorActualizar() {
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}
