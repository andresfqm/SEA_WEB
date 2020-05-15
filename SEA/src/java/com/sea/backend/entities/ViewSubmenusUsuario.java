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
import javax.persistence.Lob;
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
@Table(name = "view_submenus_usuario")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ViewSubmenusUsuario.findAll", query = "SELECT v FROM ViewSubmenusUsuario v")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByUsuario", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.usuario = :usuario")
	, @NamedQuery(name = "ViewSubmenusUsuario.findBySeccion", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.seccion = :seccion")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByTitulo", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.titulo = :titulo")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByBoton", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.boton = :boton")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByUrl", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.url = :url")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByImagen", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.imagen = :imagen")
	, @NamedQuery(name = "ViewSubmenusUsuario.findByUsuarioAndSeccion", query = "SELECT v FROM ViewSubmenusUsuario v WHERE v.usuario = :usuario AND v.seccion = :seccion")})
public class ViewSubmenusUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
	private int usuario;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "seccion")
	private String seccion;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo")
	private String titulo;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
	private String descripcion;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "boton")
	private String boton;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "url")
	@Id
	private String url;
	@Size(max = 128)
    @Column(name = "imagen")
	private String imagen;

	public ViewSubmenusUsuario() {
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBoton() {
		return boton;
	}

	public void setBoton(String boton) {
		this.boton = boton;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
