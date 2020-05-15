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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_cargo_perfil")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "CargoPerfil.findAll", query = "SELECT c FROM CargoPerfil c")
	, @NamedQuery(name = "CargoPerfil.findByIdCargoPerfil", query = "SELECT c FROM CargoPerfil c WHERE c.idCargoPerfil = :idCargoPerfil")})
public class CargoPerfil implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARGO_PERFIL")
	private Integer idCargoPerfil;
	@JoinColumn(name = "TBL_CARGO_ID_CARGO", referencedColumnName = "ID_CARGO")
    @ManyToOne(optional = false)
	private Cargo tblCargoIdCargo;
	@JoinColumn(name = "TBL_PERFIL_ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne(optional = false)
	private Perfil tblPerfilIdPerfil;

	public CargoPerfil() {
	}

	public CargoPerfil(Integer idCargoPerfil) {
		this.idCargoPerfil = idCargoPerfil;
	}

	public Integer getIdCargoPerfil() {
		return idCargoPerfil;
	}

	public void setIdCargoPerfil(Integer idCargoPerfil) {
		this.idCargoPerfil = idCargoPerfil;
	}

	public Cargo getTblCargoIdCargo() {
		return tblCargoIdCargo;
	}

	public void setTblCargoIdCargo(Cargo tblCargoIdCargo) {
		this.tblCargoIdCargo = tblCargoIdCargo;
	}

	public Perfil getTblPerfilIdPerfil() {
		return tblPerfilIdPerfil;
	}

	public void setTblPerfilIdPerfil(Perfil tblPerfilIdPerfil) {
		this.tblPerfilIdPerfil = tblPerfilIdPerfil;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCargoPerfil != null ? idCargoPerfil.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CargoPerfil)) {
			return false;
		}
		CargoPerfil other = (CargoPerfil) object;
		if ((this.idCargoPerfil == null && other.idCargoPerfil != null) || (this.idCargoPerfil != null && !this.idCargoPerfil.equals(other.idCargoPerfil))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.CargoPerfil[ idCargoPerfil=" + idCargoPerfil + " ]";
	}
	
}
