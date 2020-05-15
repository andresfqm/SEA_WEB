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

import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Departamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Depurador
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> implements CiudadFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CiudadFacade() {
		super(Ciudad.class);
	}

	@Override
	public List<Ciudad> listaCiudades(Departamento ciu) {
		List<Ciudad> lista;
		String jpql = "select nombre from tbl_ciudad\n"
				+ "where tbl_departamento_id_departamento = ?1";
		Query query = em.createNativeQuery(jpql);
		query.setParameter(1, ciu.getIdDepartamento());
		lista = query.getResultList();


		return lista;
	}
	
	@Override
	public Ciudad listaCiudad(String ci) {
		Ciudad ciudad = null;
		List<Ciudad> lista;
		String jpql = "FROM Ciudad c where c.nombre = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, ci);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
				ciudad = lista.get(0);
			}

		return ciudad;
	}
	
}
