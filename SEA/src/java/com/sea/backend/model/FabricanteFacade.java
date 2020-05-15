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

import com.sea.backend.entities.Fabricante;
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
public class FabricanteFacade extends AbstractFacade<Fabricante> implements FabricanteFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public FabricanteFacade() {
		super(Fabricante.class);
	}

	@Override
	public List<Fabricante> descripcionFabricante(int idProducto) throws Exception {
		List<Fabricante> listaFabricante;
		String consulta2 = "SELECT fa.nombre FROM tbl_producto AS P\n"
				+ "					 INNER JOIN tbl_fabricante AS fa \n"
				+ "					 ON p.tbl_fabricante_id_fabricante = fa.id_fabricante\n"
				+ "					where p.id_producto= ?1;";

		consulta2 = "SELECT fa.nombre FROM tbl_producto AS P\n"
				+ "INNER JOIN tbl_fabricante AS fa \n"
				+ "ON p.tbl_fabricante_id_fabricante = fa.id_fabricante\n"
				+ "where p.id_producto= ?1";

		Query query = em.createNativeQuery(consulta2);
		query.setParameter(1, idProducto);
		listaFabricante = query.getResultList();

		return listaFabricante;

	}
}
