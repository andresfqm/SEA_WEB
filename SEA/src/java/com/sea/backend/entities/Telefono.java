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
@Table(name = "tbl_telefono")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t")
	, @NamedQuery(name = "Telefono.findByIdTelefono", query = "SELECT t FROM Telefono t WHERE t.idTelefono = :idTelefono")
	, @NamedQuery(name = "Telefono.findByNumeroTelefono", query = "SELECT t FROM Telefono t WHERE t.numeroTelefono = :numeroTelefono")})
public class Telefono implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TELEFONO")
	private Integer idTelefono;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "NUMERO_TELEFONO")
	private String numeroTelefono;
	@JoinColumn(name = "TBL_CLIENTE_ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
	private Cliente tblClienteIdCliente;
	@JoinColumn(name = "TBL_TIPO_TELEFONO_ID_TIPO_TELEFONO", referencedColumnName = "ID_TIPO_TELEFONO")
    @ManyToOne(optional = false)
	private TipoTelefono tblTipoTelefonoIdTipoTelefono;
	@JoinColumn(name = "TBL_USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
	private Usuario tblUsuarioIdUsuario;

	public Telefono() {
	}

	public Telefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
	}

	public Telefono(Integer idTelefono, String numeroTelefono) {
		this.idTelefono = idTelefono;
		this.numeroTelefono = numeroTelefono;
	}

	public Integer getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public Cliente getTblClienteIdCliente() {
		return tblClienteIdCliente;
	}

	public void setTblClienteIdCliente(Cliente tblClienteIdCliente) {
		this.tblClienteIdCliente = tblClienteIdCliente;
	}

	public TipoTelefono getTblTipoTelefonoIdTipoTelefono() {
		return tblTipoTelefonoIdTipoTelefono;
	}

	public void setTblTipoTelefonoIdTipoTelefono(TipoTelefono tblTipoTelefonoIdTipoTelefono) {
		this.tblTipoTelefonoIdTipoTelefono = tblTipoTelefonoIdTipoTelefono;
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
		hash += (idTelefono != null ? idTelefono.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Telefono)) {
			return false;
		}
		Telefono other = (Telefono) object;
		if ((this.idTelefono == null && other.idTelefono != null) || (this.idTelefono != null && !this.idTelefono.equals(other.idTelefono))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Telefono[ idTelefono=" + idTelefono + " ]";
	}
	
}
