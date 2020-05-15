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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tbl_pagina")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Pagina.findAll", query = "SELECT p FROM Pagina p")
	, @NamedQuery(name = "Pagina.findByIdPagina", query = "SELECT p FROM Pagina p WHERE p.idPagina = :idPagina")
	, @NamedQuery(name = "Pagina.findByNombre", query = "SELECT p FROM Pagina p WHERE p.nombre = :nombre")
	, @NamedQuery(name = "Pagina.findByNombreBoton", query = "SELECT p FROM Pagina p WHERE p.nombreBoton = :nombreBoton")
	, @NamedQuery(name = "Pagina.findByUrl", query = "SELECT p FROM Pagina p WHERE p.url = :url")
	, @NamedQuery(name = "Pagina.findByUrlImagen", query = "SELECT p FROM Pagina p WHERE p.urlImagen = :urlImagen")
	, @NamedQuery(name = "Pagina.findByPosicion", query = "SELECT p FROM Pagina p WHERE p.posicion = :posicion")})
public class Pagina implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAGINA")
	private Integer idPagina;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
	private String nombre;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_BOTON")
	private String nombreBoton;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "URL")
	private String url;
	@Size(max = 128)
    @Column(name = "URL_IMAGEN")
	private String urlImagen;
	@Basic(optional = false)
    @NotNull
    @Column(name = "POSICION")
	private int posicion;
	@JoinTable(name = "tbl_perfil_pagina", joinColumns = {
    	@JoinColumn(name = "TBL_PAGINA_ID_PAGINA", referencedColumnName = "ID_PAGINA")}, inverseJoinColumns = {
    	@JoinColumn(name = "TBL_PERFIL_ID_PERFIL", referencedColumnName = "ID_PERFIL")})
    @ManyToMany
	private List<Perfil> perfilList;
	@JoinColumn(name = "TBL_MENU_ID_MENU", referencedColumnName = "ID_MENU")
    @ManyToOne(optional = false)
	private Menu tblMenuIdMenu;

	public Pagina() {
	}

	public Pagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	public Pagina(Integer idPagina, String nombre, String descripcion, String nombreBoton, String url, int posicion) {
		this.idPagina = idPagina;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombreBoton = nombreBoton;
		this.url = url;
		this.posicion = posicion;
	}

	public Integer getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreBoton() {
		return nombreBoton;
	}

	public void setNombreBoton(String nombreBoton) {
		this.nombreBoton = nombreBoton;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	@XmlTransient
	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}

	public Menu getTblMenuIdMenu() {
		return tblMenuIdMenu;
	}

	public void setTblMenuIdMenu(Menu tblMenuIdMenu) {
		this.tblMenuIdMenu = tblMenuIdMenu;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPagina != null ? idPagina.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Pagina)) {
			return false;
		}
		Pagina other = (Pagina) object;
		if ((this.idPagina == null && other.idPagina != null) || (this.idPagina != null && !this.idPagina.equals(other.idPagina))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Pagina[ idPagina=" + idPagina + " ]";
	}
	
}
