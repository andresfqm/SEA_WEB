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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Depurador
 */
@Entity
@Table(name = "tbl_diseno_producto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "DisenoProducto.findAll", query = "SELECT d FROM DisenoProducto d")
	, @NamedQuery(name = "DisenoProducto.findByIdDisenoProducto", query = "SELECT d FROM DisenoProducto d WHERE d.idDisenoProducto = :idDisenoProducto")
	, @NamedQuery(name = "DisenoProducto.findByLogotipo", query = "SELECT d FROM DisenoProducto d WHERE d.logotipo = :logotipo")
	, @NamedQuery(name = "DisenoProducto.findByDiagramaDiseno", query = "SELECT d FROM DisenoProducto d WHERE d.diagramaDiseno = :diagramaDiseno")
	, @NamedQuery(name = "DisenoProducto.findByNecesitaBordado", query = "SELECT d FROM DisenoProducto d WHERE d.necesitaBordado = :necesitaBordado")
	, @NamedQuery(name = "DisenoProducto.findByDiseno", query = "SELECT d FROM DisenoProducto d WHERE d.diseno = :diseno")})
public class DisenoProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DISENO_PRODUCTO")
	private Integer idDisenoProducto;
	@Size(max = 256)
    @Column(name = "LOGOTIPO")
	private String logotipo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "DIAGRAMA_DISENO")
	private String diagramaDiseno;
	@Column(name = "NECESITA_BORDADO")
	private Boolean necesitaBordado;
	@Size(max = 64)
    @Column(name = "DISENO")
	private String diseno;
	@Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION_DISENO")
	private String descripcionDiseno;
	@JoinColumn(name = "TBL_PRODUCTO_ESPECIFICACION_ID_PRODUCTO_ESPECIFICACION", referencedColumnName = "ID_PRODUCTO_ESPECIFICACION")
    @ManyToOne(optional = false)
	private ProductoEspecificacion tblProductoEspecificacionIdProductoEspecificacion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDisenoProductoIdDisenoProducto")
	private List<EspecificacionDiseno> especificacionDisenoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDisenoProductoIdDisenoProducto")
	private List<TallaDisenoProducto> tallaDisenoProductoList;
	@Transient
	private String descripcion;


	public DisenoProducto() {
	}

	public DisenoProducto(Integer idDisenoProducto) {
		this.idDisenoProducto = idDisenoProducto;
	}

	public DisenoProducto(Integer idDisenoProducto, String diagramaDiseno) {
		this.idDisenoProducto = idDisenoProducto;
		this.diagramaDiseno = diagramaDiseno;
	}

	public Integer getIdDisenoProducto() {
		return idDisenoProducto;
	}

	public void setIdDisenoProducto(Integer idDisenoProducto) {
		this.idDisenoProducto = idDisenoProducto;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public String getDiagramaDiseno() {
		return diagramaDiseno;
	}

	public void setDiagramaDiseno(String diagramaDiseno) {
		this.diagramaDiseno = diagramaDiseno;
	}

	public Boolean getNecesitaBordado() {
		return necesitaBordado;
	}

	public void setNecesitaBordado(Boolean necesitaBordado) {
		this.necesitaBordado = necesitaBordado;
	}

	public String getDiseno() {
		return diseno;
	}

	public void setDiseno(String diseno) {
		this.diseno = diseno;
	}

	public String getDescripcionDiseno() {
		return descripcionDiseno;
	}

	public void setDescripcionDiseno(String descripcionDiseno) {
		this.descripcionDiseno = descripcionDiseno;
	}

	public ProductoEspecificacion getTblProductoEspecificacionIdProductoEspecificacion() {
		return tblProductoEspecificacionIdProductoEspecificacion;
	}

	public void setTblProductoEspecificacionIdProductoEspecificacion(ProductoEspecificacion tblProductoEspecificacionIdProductoEspecificacion) {
		this.tblProductoEspecificacionIdProductoEspecificacion = tblProductoEspecificacionIdProductoEspecificacion;
	}

	@XmlTransient
	public List<EspecificacionDiseno> getEspecificacionDisenoList() {
		return especificacionDisenoList;
	}

	public void setEspecificacionDisenoList(List<EspecificacionDiseno> especificacionDisenoList) {
		this.especificacionDisenoList = especificacionDisenoList;
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
		hash += (idDisenoProducto != null ? idDisenoProducto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DisenoProducto)) {
			return false;
		}
		DisenoProducto other = (DisenoProducto) object;
		if ((this.idDisenoProducto == null && other.idDisenoProducto != null) || (this.idDisenoProducto != null && !this.idDisenoProducto.equals(other.idDisenoProducto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sea.backend.entities.DisenoProducto[ idDisenoProducto=" + idDisenoProducto + " ]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
