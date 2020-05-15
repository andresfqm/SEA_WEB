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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_devolucion")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d")
	, @NamedQuery(name = "Devolucion.findByIdDevolucion", query = "SELECT d FROM Devolucion d WHERE d.idDevolucion = :idDevolucion")
	, @NamedQuery(name = "Devolucion.findByAceptada", query = "SELECT d FROM Devolucion d WHERE d.aceptada = :aceptada")
	, @NamedQuery(name = "Devolucion.findByValorDevolucion", query = "SELECT d FROM Devolucion d WHERE d.valorDevolucion = :valorDevolucion")})
public class Devolucion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DEVOLUCION")
	private Integer idDevolucion;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "MOTIVO_DEVOLUCION")
	private String motivoDevolucion;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ACEPTADA")
	private boolean aceptada;
	@Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_DEVOLUCION")
	private double valorDevolucion;
	@Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIOS")
	private String comentarios;
	@ManyToMany(mappedBy = "devolucionList")
	private List<CondicionesGarantia> condicionesGarantiaList;
	@JoinColumn(name = "TBL_ORDEN_PRODUCCION_ID_ORDEN_PRODUCCION", referencedColumnName = "ID_ORDEN_PRODUCCION")
    @ManyToOne(optional = false)
	private OrdenProduccion tblOrdenProduccionIdOrdenProduccion;

	public Devolucion() {
	}

	public Devolucion(Integer idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public Devolucion(Integer idDevolucion, String motivoDevolucion, boolean aceptada, double valorDevolucion) {
		this.idDevolucion = idDevolucion;
		this.motivoDevolucion = motivoDevolucion;
		this.aceptada = aceptada;
		this.valorDevolucion = valorDevolucion;
	}

	public Integer getIdDevolucion() {
		return idDevolucion;
	}

	public void setIdDevolucion(Integer idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}

	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}

	public boolean getAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}

	public double getValorDevolucion() {
		return valorDevolucion;
	}

	public void setValorDevolucion(double valorDevolucion) {
		this.valorDevolucion = valorDevolucion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@XmlTransient
	public List<CondicionesGarantia> getCondicionesGarantiaList() {
		return condicionesGarantiaList;
	}

	public void setCondicionesGarantiaList(List<CondicionesGarantia> condicionesGarantiaList) {
		this.condicionesGarantiaList = condicionesGarantiaList;
	}

	public OrdenProduccion getTblOrdenProduccionIdOrdenProduccion() {
		return tblOrdenProduccionIdOrdenProduccion;
	}

	public void setTblOrdenProduccionIdOrdenProduccion(OrdenProduccion tblOrdenProduccionIdOrdenProduccion) {
		this.tblOrdenProduccionIdOrdenProduccion = tblOrdenProduccionIdOrdenProduccion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idDevolucion != null ? idDevolucion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Devolucion)) {
			return false;
		}
		Devolucion other = (Devolucion) object;
		if ((this.idDevolucion == null && other.idDevolucion != null) || (this.idDevolucion != null && !this.idDevolucion.equals(other.idDevolucion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.Devolucion[ idDevolucion=" + idDevolucion + " ]";
	}
	
}
