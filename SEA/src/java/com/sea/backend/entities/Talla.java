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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_talla")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Talla.findAll", query = "SELECT t FROM Talla t")
	, @NamedQuery(name = "Talla.findByIdTalla", query = "SELECT t FROM Talla t WHERE t.idTalla = :idTalla")
	, @NamedQuery(name = "Talla.findByTalla", query = "SELECT t FROM Talla t WHERE t.talla = :talla")})
public class Talla implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TALLA")
	private Integer idTalla;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TALLA")
	private String talla;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblTallaIdTalla")
	private List<TallaDisenoProducto> tallaDisenoProductoList;

	public Talla() {
	}

	public Talla(Integer idTalla) {
		this.idTalla = idTalla;
	}

	public Talla(Integer idTalla, String talla) {
		this.idTalla = idTalla;
		this.talla = talla;
	}

	public Integer getIdTalla() {
		return idTalla;
	}

	public void setIdTalla(Integer idTalla) {
		this.idTalla = idTalla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@XmlTransient
	public List<TallaDisenoProducto> getTallaDisenoProductoList() {
		return tallaDisenoProductoList;
	}

	public void setTallaDisenoProductoList(List<TallaDisenoProducto> tallaDisenoProductoList) {
		this.tallaDisenoProductoList = tallaDisenoProductoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTalla != null ? idTalla.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Talla)) {
			return false;
		}
		Talla other = (Talla) object;
		if ((this.idTalla == null && other.idTalla != null) || (this.idTalla != null && !this.idTalla.equals(other.idTalla))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Talla[ idTalla=" + idTalla + " ]";
	}
	
}
