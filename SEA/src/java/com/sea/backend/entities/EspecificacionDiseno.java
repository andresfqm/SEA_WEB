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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_especificacion_diseno")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "EspecificacionDiseno.findAll", query = "SELECT e FROM EspecificacionDiseno e")
	, @NamedQuery(name = "EspecificacionDiseno.findByIdEspecificacionDiseno", query = "SELECT e FROM EspecificacionDiseno e WHERE e.idEspecificacionDiseno = :idEspecificacionDiseno")
	, @NamedQuery(name = "EspecificacionDiseno.findByTipoEspecificacion", query = "SELECT e FROM EspecificacionDiseno e WHERE e.tipoEspecificacion = :tipoEspecificacion")})
public class EspecificacionDiseno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESPECIFICACION_DISENO")
	private Integer idEspecificacionDiseno;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TIPO_ESPECIFICACION")
	private String tipoEspecificacion;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION")
	private String descripcion;
	@JoinColumn(name = "TBL_DISENO_PRODUCTO_ID_DISENO_PRODUCTO", referencedColumnName = "ID_DISENO_PRODUCTO")
    @ManyToOne(optional = false)
	private DisenoProducto tblDisenoProductoIdDisenoProducto;

	public EspecificacionDiseno() {
	}

	public EspecificacionDiseno(Integer idEspecificacionDiseno) {
		this.idEspecificacionDiseno = idEspecificacionDiseno;
	}

	public EspecificacionDiseno(Integer idEspecificacionDiseno, String tipoEspecificacion, String descripcion) {
		this.idEspecificacionDiseno = idEspecificacionDiseno;
		this.tipoEspecificacion = tipoEspecificacion;
		this.descripcion = descripcion;
	}

	public Integer getIdEspecificacionDiseno() {
		return idEspecificacionDiseno;
	}

	public void setIdEspecificacionDiseno(Integer idEspecificacionDiseno) {
		this.idEspecificacionDiseno = idEspecificacionDiseno;
	}

	public String getTipoEspecificacion() {
		return tipoEspecificacion;
	}

	public void setTipoEspecificacion(String tipoEspecificacion) {
		this.tipoEspecificacion = tipoEspecificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DisenoProducto getTblDisenoProductoIdDisenoProducto() {
		return tblDisenoProductoIdDisenoProducto;
	}

	public void setTblDisenoProductoIdDisenoProducto(DisenoProducto tblDisenoProductoIdDisenoProducto) {
		this.tblDisenoProductoIdDisenoProducto = tblDisenoProductoIdDisenoProducto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEspecificacionDiseno != null ? idEspecificacionDiseno.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof EspecificacionDiseno)) {
			return false;
		}
		EspecificacionDiseno other = (EspecificacionDiseno) object;
		if ((this.idEspecificacionDiseno == null && other.idEspecificacionDiseno != null) || (this.idEspecificacionDiseno != null && !this.idEspecificacionDiseno.equals(other.idEspecificacionDiseno))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.EspecificacionDiseno[ idEspecificacionDiseno=" + idEspecificacionDiseno + " ]";
	}
	
}
