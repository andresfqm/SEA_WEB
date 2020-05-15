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
package com.sea.backend.model;

import com.sea.backend.entities.Direccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Depurador
 */
@Stateless
public class DireccionFacade extends AbstractFacade<Direccion> implements DireccionFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DireccionFacade() {
		super(Direccion.class);
	}

	@Override
	public Direccion direccionCliente(int idCliente) {
		Direccion direccionCliente = null;
		String consulta;
		try {
			consulta = "FROM Direccion d WHERE d.tblClienteIdCliente.idCliente = ?1";
			//consulta = "c.nombre FROM Direccion d INNER JOIN Cliente cl ON d.tblClienteIdCliente.idCliente=cl.idCliente INNER JOIN Ciudad c ON d.tblCiudadIdCiudad=c.idCiudad AND d.tblClienteIdCliente.idCliente=?1";
			//consulta = "c.nombre FROM Direccion d INNER JOIN d.Cliente cl ON d.tblClienteIdCliente.idCliente=cl.idCliente AND d.tblClienteIdCliente= ?1 INNER JOIN Ciudad c ON d.tblCiudadIdCiudad.idCiudad=c.idCiudad";
			//SELECT c.nombre FROM TBL_DIRECCION d INNER JOIN TBL_CLIENTE cl ON d.TBL_CLIENTE_ID_CLIENTE=cl.ID_CLIENTE AND d.TBL_CLIENTE_ID_CLIENTE=211 INNER JOIN TBL_CIUDAD c ON d.TBL_CIUDAD_ID_CIUDAD=c.ID_CIUDAD;
			Query query = em.createQuery(consulta);
			query.setParameter(1, idCliente);
			direccionCliente = (Direccion) query.getSingleResult();
			System.out.println(direccionCliente);
		} catch (Exception e) {
			throw e;
		}
		return direccionCliente;
	}
}
