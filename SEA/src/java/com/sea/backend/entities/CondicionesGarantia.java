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
@Table(name = "tbl_condiciones_garantia")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "CondicionesGarantia.findAll", query = "SELECT c FROM CondicionesGarantia c")
	, @NamedQuery(name = "CondicionesGarantia.findByIdCondicionesGarantia", query = "SELECT c FROM CondicionesGarantia c WHERE c.idCondicionesGarantia = :idCondicionesGarantia")
	, @NamedQuery(name = "CondicionesGarantia.findByNombre", query = "SELECT c FROM CondicionesGarantia c WHERE c.nombre = :nombre")
	, @NamedQuery(name = "CondicionesGarantia.findByActivo", query = "SELECT c FROM CondicionesGarantia c WHERE c.activo = :activo")})
public class CondicionesGarantia implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONDICIONES_GARANTIA")
	private Integer idCondicionesGarantia;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NOMBRE")
	private String nombre;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ACTIVO")
	private Boolean activo;
	@JoinTable(name = "tbl_devolucion_condiciones_garantia", joinColumns = {
    	@JoinColumn(name = "TBL_CONDICIONES_GARANTIA_ID_CONDICIONES_GARANTIA", referencedColumnName = "ID_CONDICIONES_GARANTIA")}, inverseJoinColumns = {
    	@JoinColumn(name = "TBL_DEVOLUCION_ID_DEVOLUCION", referencedColumnName = "ID_DEVOLUCION")})
    @ManyToMany
	private List<Devolucion> devolucionList;

	public CondicionesGarantia() {
	}

	public CondicionesGarantia(Integer idCondicionesGarantia) {
		this.idCondicionesGarantia = idCondicionesGarantia;
	}

	public CondicionesGarantia(Integer idCondicionesGarantia, String nombre, String descripcion) {
		this.idCondicionesGarantia = idCondicionesGarantia;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getIdCondicionesGarantia() {
		return idCondicionesGarantia;
	}

	public void setIdCondicionesGarantia(Integer idCondicionesGarantia) {
		this.idCondicionesGarantia = idCondicionesGarantia;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@XmlTransient
	public List<Devolucion> getDevolucionList() {
		return devolucionList;
	}

	public void setDevolucionList(List<Devolucion> devolucionList) {
		this.devolucionList = devolucionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCondicionesGarantia != null ? idCondicionesGarantia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CondicionesGarantia)) {
			return false;
		}
		CondicionesGarantia other = (CondicionesGarantia) object;
		if ((this.idCondicionesGarantia == null && other.idCondicionesGarantia != null) || (this.idCondicionesGarantia != null && !this.idCondicionesGarantia.equals(other.idCondicionesGarantia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.CondicionesGarantia[ idCondicionesGarantia=" + idCondicionesGarantia + " ]";
	}
	
}
