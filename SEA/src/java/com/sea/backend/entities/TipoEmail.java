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
@Table(name = "tbl_tipo_email")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TipoEmail.findAll", query = "SELECT t FROM TipoEmail t")
	, @NamedQuery(name = "TipoEmail.findByIdTipoEmail", query = "SELECT t FROM TipoEmail t WHERE t.idTipoEmail = :idTipoEmail")
	, @NamedQuery(name = "TipoEmail.findByTipo", query = "SELECT t FROM TipoEmail t WHERE t.tipo = :tipo")})
public class TipoEmail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_EMAIL")
	private Integer idTipoEmail;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "TIPO")
	private String tipo;
	@OneToMany(mappedBy = "tblTipoEmailIdTipoEmail")
	private List<Email> emailList;

	public TipoEmail() {
	}

	public TipoEmail(Integer idTipoEmail) {
		this.idTipoEmail = idTipoEmail;
	}

	public TipoEmail(Integer idTipoEmail, String tipo) {
		this.idTipoEmail = idTipoEmail;
		this.tipo = tipo;
	}

	public Integer getIdTipoEmail() {
		return idTipoEmail;
	}

	public void setIdTipoEmail(Integer idTipoEmail) {
		this.idTipoEmail = idTipoEmail;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlTransient
	public List<Email> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<Email> emailList) {
		this.emailList = emailList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoEmail != null ? idTipoEmail.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TipoEmail)) {
			return false;
		}
		TipoEmail other = (TipoEmail) object;
		if ((this.idTipoEmail == null && other.idTipoEmail != null) || (this.idTipoEmail != null && !this.idTipoEmail.equals(other.idTipoEmail))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.TipoEmail[ idTipoEmail=" + idTipoEmail + " ]";
	}
	
}
