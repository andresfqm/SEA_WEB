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

import com.sea.backend.entities.CotizacionProducto;
import com.sea.frontend.controller.CotizacionProductoAuxiliar;
import java.util.ArrayList;
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
public class CotizacionProductoFacade extends AbstractFacade<CotizacionProducto> implements CotizacionProductoFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CotizacionProductoFacade() {
		super(CotizacionProducto.class);
	}

	@Override
	public List<CotizacionProductoAuxiliar> datosCotizacionProducto(String numeroCotizacion) throws Exception {
		List<CotizacionProductoAuxiliar> listaDatosCotizacionProductoAuxiliar;
		listaDatosCotizacionProductoAuxiliar = new ArrayList<>();
		String consulta1 = "SELECT pr.referencia, pr.descripcion, ma.nombre, fa.nombre, pr.id_producto\n"
				+ "FROM tbl_cotizacion_producto AS cp\n"
				+ "INNER JOIN tbl_producto AS pr\n"
				+ "ON cp.tbl_producto_id_producto = pr.id_producto\n"
				+ "INNER JOIN tbl_producto_material AS pm \n"
				+ "ON pr.id_producto = pm.tbl_producto_id_producto\n"
				+ "INNER JOIN tbl_material AS ma \n"
				+ "ON pm.tbl_material_id_material = ma.id_material\n"
				+ "INNER JOIN tbl_fabricante AS fa\n"
				+ "ON pr.tbl_fabricante_id_fabricante = fa.id_fabricante\n"
				+ "WHERE cp.tbl_cotizacion_numero_cotizacion = ?1";

		Query query = em.createNativeQuery(consulta1);
		query.setParameter(1, numeroCotizacion);

		listaDatosCotizacionProductoAuxiliar = query.getResultList();

		return listaDatosCotizacionProductoAuxiliar;
		//List<Object[]> miLista = query.getResultList();
	}

	@Override
	public List<CotizacionProducto> productosCotizados(String numeroCotizacion) throws Exception {
		List<CotizacionProducto> listaProductosCotizados;
		String consulta2 = " SELECT pr.referencia, pr.descripcion, cp.cantidad, cp.precio_base, cp.precio_para_cliente\n"
				+ "                FROM tbl_cotizacion_producto AS cp\n"
				+ "                INNER JOIN tbl_producto AS pr\n"
				+ "                ON cp.tbl_producto_id_producto = pr.id_producto\n"
				+ "                WHERE cp.tbl_cotizacion_numero_cotizacion = ?1";

		Query query = em.createNativeQuery(consulta2);
		query.setParameter(1, numeroCotizacion);
		listaProductosCotizados = query.getResultList();
		return listaProductosCotizados;

	}
}
