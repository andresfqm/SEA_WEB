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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "view_index_cotizaciones_activas")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewIndexCotizacionesActivas.findAll", query = "SELECT v FROM ViewIndexCotizacionesActivas v")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByUsuario", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.usuario = :usuario")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByNumeroCotizacion", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.numeroCotizacion = :numeroCotizacion")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByCliente", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.cliente = :cliente")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByDocumento", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.documento = :documento")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByContacto", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.contacto = :contacto")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByTelefonoContacto", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.telefonoContacto = :telefonoContacto")
	, @NamedQuery(name = "ViewIndexCotizacionesActivas.findByDiasRestantes", query = "SELECT v FROM ViewIndexCotizacionesActivas v WHERE v.diasRestantes = :diasRestantes")})
public class ViewIndexCotizacionesActivas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
	private int usuario;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_cotizacion")
	@Id
	private String numeroCotizacion;
	@Size(max = 129)
    @Column(name = "cliente")
	private String cliente;
	@Size(max = 41)
    @Column(name = "documento")
	private String documento;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "contacto")
	private String contacto;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "telefono_contacto")
	private String telefonoContacto;
	@Column(name = "dias_restantes")
	private Integer diasRestantes;

	public ViewIndexCotizacionesActivas() {
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getNumeroCotizacion() {
		return numeroCotizacion;
	}

	public void setNumeroCotizacion(String numeroCotizacion) {
		this.numeroCotizacion = numeroCotizacion;
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

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Integer getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(Integer diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
	
}
