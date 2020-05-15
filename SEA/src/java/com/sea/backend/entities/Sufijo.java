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
@Table(name = "tbl_sufijo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Sufijo.findAll", query = "SELECT s FROM Sufijo s")
	, @NamedQuery(name = "Sufijo.findByIdSufijo", query = "SELECT s FROM Sufijo s WHERE s.idSufijo = :idSufijo")
	, @NamedQuery(name = "Sufijo.findByCodigo", query = "SELECT s FROM Sufijo s WHERE s.codigo = :codigo")})
public class Sufijo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SUFIJO")
	private Integer idSufijo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO")
	private String codigo;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRIPCION_FABRICANTE")
	private String descripcionFabricante;
	@ManyToMany(mappedBy = "sufijoList")
	private List<Producto> productoList;

	public Sufijo() {
	}

	public Sufijo(Integer idSufijo) {
		this.idSufijo = idSufijo;
	}

	public Sufijo(Integer idSufijo, String codigo, String descripcionFabricante) {
		this.idSufijo = idSufijo;
		this.codigo = codigo;
		this.descripcionFabricante = descripcionFabricante;
	}

	public Integer getIdSufijo() {
		return idSufijo;
	}

	public void setIdSufijo(Integer idSufijo) {
		this.idSufijo = idSufijo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcionFabricante() {
		return descripcionFabricante;
	}

	public void setDescripcionFabricante(String descripcionFabricante) {
		this.descripcionFabricante = descripcionFabricante;
	}

	@XmlTransient
	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSufijo != null ? idSufijo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Sufijo)) {
			return false;
		}
		Sufijo other = (Sufijo) object;
		if ((this.idSufijo == null && other.idSufijo != null) || (this.idSufijo != null && !this.idSufijo.equals(other.idSufijo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Sufijo[ idSufijo=" + idSufijo + " ]";
	}
	
}
