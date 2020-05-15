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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_email")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
	, @NamedQuery(name = "Email.findByIdEmail", query = "SELECT e FROM Email e WHERE e.idEmail = :idEmail")
	, @NamedQuery(name = "Email.findByEmail", query = "SELECT e FROM Email e WHERE e.email = :email")})
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMAIL")
	private Integer idEmail;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "EMAIL")
	private String email;
	@JoinColumn(name = "TBL_CLIENTE_ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
	private Cliente tblClienteIdCliente;
	@JoinColumn(name = "TBL_TIPO_EMAIL_ID_TIPO_EMAIL", referencedColumnName = "ID_TIPO_EMAIL")
    @ManyToOne
	private TipoEmail tblTipoEmailIdTipoEmail;
	@JoinColumn(name = "TBL_USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
	private Usuario tblUsuarioIdUsuario;

	public Email() {
	}

	public Email(Integer idEmail) {
		this.idEmail = idEmail;
	}

	public Email(Integer idEmail, String email) {
		this.idEmail = idEmail;
		this.email = email;
	}

	public Integer getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getTblClienteIdCliente() {
		return tblClienteIdCliente;
	}

	public void setTblClienteIdCliente(Cliente tblClienteIdCliente) {
		this.tblClienteIdCliente = tblClienteIdCliente;
	}

	public TipoEmail getTblTipoEmailIdTipoEmail() {
		return tblTipoEmailIdTipoEmail;
	}

	public void setTblTipoEmailIdTipoEmail(TipoEmail tblTipoEmailIdTipoEmail) {
		this.tblTipoEmailIdTipoEmail = tblTipoEmailIdTipoEmail;
	}

	public Usuario getTblUsuarioIdUsuario() {
		return tblUsuarioIdUsuario;
	}

	public void setTblUsuarioIdUsuario(Usuario tblUsuarioIdUsuario) {
		this.tblUsuarioIdUsuario = tblUsuarioIdUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEmail != null ? idEmail.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Email)) {
			return false;
		}
		Email other = (Email) object;
		if ((this.idEmail == null && other.idEmail != null) || (this.idEmail != null && !this.idEmail.equals(other.idEmail))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Email[ idEmail=" + idEmail + " ]";
	}
	
}
