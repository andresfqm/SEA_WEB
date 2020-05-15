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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_registro_costo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "RegistroCosto.findAll", query = "SELECT r FROM RegistroCosto r")
	, @NamedQuery(name = "RegistroCosto.findByIdRegistroCosto", query = "SELECT r FROM RegistroCosto r WHERE r.idRegistroCosto = :idRegistroCosto")
	, @NamedQuery(name = "RegistroCosto.findByInicioVigencia", query = "SELECT r FROM RegistroCosto r WHERE r.inicioVigencia = :inicioVigencia")
	, @NamedQuery(name = "RegistroCosto.findByFinVigencia", query = "SELECT r FROM RegistroCosto r WHERE r.finVigencia = :finVigencia")
	, @NamedQuery(name = "RegistroCosto.findByCosto", query = "SELECT r FROM RegistroCosto r WHERE r.costo = :costo")})
public class RegistroCosto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGISTRO_COSTO")
	private Integer idRegistroCosto;
	@Basic(optional = false)
    @NotNull
    @Column(name = "INICIO_VIGENCIA")
    @Temporal(TemporalType.DATE)
	private Date inicioVigencia;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FIN_VIGENCIA")
    @Temporal(TemporalType.DATE)
	private Date finVigencia;
	@Basic(optional = false)
    @NotNull
    @Column(name = "COSTO")
	private float costo;
	@JoinColumn(name = "TBL_PRODUCTO_ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
	private Producto tblProductoIdProducto;

	public RegistroCosto() {
	}

	public RegistroCosto(Integer idRegistroCosto) {
		this.idRegistroCosto = idRegistroCosto;
	}

	public RegistroCosto(Integer idRegistroCosto, Date inicioVigencia, Date finVigencia, float costo) {
		this.idRegistroCosto = idRegistroCosto;
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.costo = costo;
	}

	public Integer getIdRegistroCosto() {
		return idRegistroCosto;
	}

	public void setIdRegistroCosto(Integer idRegistroCosto) {
		this.idRegistroCosto = idRegistroCosto;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Producto getTblProductoIdProducto() {
		return tblProductoIdProducto;
	}

	public void setTblProductoIdProducto(Producto tblProductoIdProducto) {
		this.tblProductoIdProducto = tblProductoIdProducto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idRegistroCosto != null ? idRegistroCosto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RegistroCosto)) {
			return false;
		}
		RegistroCosto other = (RegistroCosto) object;
		if ((this.idRegistroCosto == null && other.idRegistroCosto != null) || (this.idRegistroCosto != null && !this.idRegistroCosto.equals(other.idRegistroCosto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.RegistroCosto[ idRegistroCosto=" + idRegistroCosto + " ]";
	}
	
}
