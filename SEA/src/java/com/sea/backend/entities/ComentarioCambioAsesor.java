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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_comentario_cambio_asesor")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ComentarioCambioAsesor.findAll", query = "SELECT c FROM ComentarioCambioAsesor c")
	, @NamedQuery(name = "ComentarioCambioAsesor.findByIdComentarioCambioAsesor", query = "SELECT c FROM ComentarioCambioAsesor c WHERE c.idComentarioCambioAsesor = :idComentarioCambioAsesor")})
public class ComentarioCambioAsesor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMENTARIO_CAMBIO_ASESOR")
	private Integer idComentarioCambioAsesor;
	@Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIO")
	private String comentario;
	@OneToMany(mappedBy = "tblComentarioCambioAsesorIdComentarioCambioAsesor")
	private List<Cliente> clienteList;

	public ComentarioCambioAsesor() {
	}

	public ComentarioCambioAsesor(Integer idComentarioCambioAsesor) {
		this.idComentarioCambioAsesor = idComentarioCambioAsesor;
	}

	public Integer getIdComentarioCambioAsesor() {
		return idComentarioCambioAsesor;
	}

	public void setIdComentarioCambioAsesor(Integer idComentarioCambioAsesor) {
		this.idComentarioCambioAsesor = idComentarioCambioAsesor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@XmlTransient
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idComentarioCambioAsesor != null ? idComentarioCambioAsesor.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ComentarioCambioAsesor)) {
			return false;
		}
		ComentarioCambioAsesor other = (ComentarioCambioAsesor) object;
		if ((this.idComentarioCambioAsesor == null && other.idComentarioCambioAsesor != null) || (this.idComentarioCambioAsesor != null && !this.idComentarioCambioAsesor.equals(other.idComentarioCambioAsesor))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.ComentarioCambioAsesor[ idComentarioCambioAsesor=" + idComentarioCambioAsesor + " ]";
	}
	
}
