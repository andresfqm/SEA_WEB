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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_usuario")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
	, @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
	, @NamedQuery(name = "Usuario.findByNumeroDocumento", query = "SELECT u FROM Usuario u WHERE u.numeroDocumento = :numeroDocumento")
	, @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
	, @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido")
	, @NamedQuery(name = "Usuario.findByIdInterno", query = "SELECT u FROM Usuario u WHERE u.idInterno = :idInterno")
	, @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	, @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
	, @NamedQuery(name = "Usuario.findByHabilitado", query = "SELECT u FROM Usuario u WHERE u.habilitado = :habilitado")
	, @NamedQuery(name = "Usuario.findByAutenticado", query = "SELECT u FROM Usuario u WHERE u.autenticado = :autenticado")})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
	private Integer idUsuario;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NOMBRE")
	private String nombre;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "APELLIDO")
	private String apellido;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ID_INTERNO")
	private String idInterno;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "CONTRASENA")
	private String contrasena;
	@Basic(optional = false)
    @NotNull
    @Column(name = "HABILITADO")
	private boolean habilitado;
	@Basic(optional = false)
    @NotNull
    @Column(name = "AUTENTICADO")
	private boolean autenticado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblUsuarioIdUsuario")
	private List<Cliente> clienteList;
	@OneToMany(mappedBy = "tblUsuarioIdUsuario")
	private List<Telefono> telefonoList;
	@OneToMany(mappedBy = "tblUsuarioIdUsuario")
	private List<Direccion> direccionList;
	@JoinColumn(name = "TBL_CARGO_ID_CARGO", referencedColumnName = "ID_CARGO")
    @ManyToOne(optional = false)
	private Cargo tblCargoIdCargo;
	@JoinColumn(name = "TBL_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false)
	private TipoDocumento tblTipoDocumentoIdTipoDocumento;
	@OneToMany(mappedBy = "tblUsuarioIdUsuario")
	private List<Email> emailList;

	public Usuario() {
	}

	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(Integer idUsuario, String numeroDocumento, String nombre, String apellido, String idInterno, int consecutivoCotizacion, String nombreUsuario, String contrasena, boolean habilitado, boolean autenticado) {
		this.idUsuario = idUsuario;
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.idInterno = idInterno;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.habilitado = habilitado;
		this.autenticado = autenticado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(String idInterno) {
		this.idInterno = idInterno;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean getAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	@XmlTransient
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	@XmlTransient
	public List<Telefono> getTelefonoList() {
		return telefonoList;
	}

	public void setTelefonoList(List<Telefono> telefonoList) {
		this.telefonoList = telefonoList;
	}

	@XmlTransient
	public List<Direccion> getDireccionList() {
		return direccionList;
	}

	public void setDireccionList(List<Direccion> direccionList) {
		this.direccionList = direccionList;
	}

	public Cargo getTblCargoIdCargo() {
		return tblCargoIdCargo;
	}

	public void setTblCargoIdCargo(Cargo tblCargoIdCargo) {
		this.tblCargoIdCargo = tblCargoIdCargo;
	}

	public TipoDocumento getTblTipoDocumentoIdTipoDocumento() {
		return tblTipoDocumentoIdTipoDocumento;
	}

	public void setTblTipoDocumentoIdTipoDocumento(TipoDocumento tblTipoDocumentoIdTipoDocumento) {
		this.tblTipoDocumentoIdTipoDocumento = tblTipoDocumentoIdTipoDocumento;
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
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Usuario[ idUsuario=" + idUsuario + " ]";
	}
	
}
