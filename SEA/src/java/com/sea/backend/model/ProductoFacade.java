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

import com.sea.backend.dto.ProductoDTO;
import com.sea.backend.entities.Producto;
import com.sea.frontend.controller.ProductoAuxiliar;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProductoFacade() {
		super(Producto.class);
	}

	@Override
	public Producto productoDescripcion(int idProducto) throws Exception {
		Producto productoDescripcion = null;
		String consulta;

		try {
			consulta = "FROM Producto p WHERE p.idProducto = ?1";

			Query query = em.createQuery(consulta);
			query.setParameter(1, idProducto);
			productoDescripcion = (Producto) query.getSingleResult();
			System.out.println(productoDescripcion);
		} catch (Exception e) {
			throw e;
		}
		return productoDescripcion;
	}

	@Override
	public double productoPrecio(int idProducto) throws Exception {
		double precio = 0;

		String consulta3 = "SELECT PRECIO  FROM tbl_producto WHERE ID_PRODUCTO=?1;";
		Query query = em.createNativeQuery(consulta3);
		query.setParameter(1, idProducto);

		Object ls = query.getSingleResult();

		precio = Float.parseFloat(ls.toString());

		return precio;

	}

	@Override
	public List<ProductoDTO> listaProductos() {
		List<Object[]> listaDatos;
		List<ProductoDTO> listaProductoPrecio;
//		String consulta = "SELECT p.referencia, p.descripcion, m.nombre, f.nombre, p.fechaActualizacion, cos.costo, p.precio FROM Producto p "
//				+ "JOIN p.materialList m\n"
//				+ "JOIN p.tblFabricanteIdFabricante f\n"
//				+ "JOIN p.registroCostoList cos\n";

		String consulta = "select p.ID_PRODUCTO, p.REFERENCIA, p.DESCRIPCION, p.FECHA_ACTUALIZACION, c.COSTO, p.PRECIO, f.NOMBRE as FABRICANTE, group_concat(distinct m.nombre) MATERIAL from tbl_producto_material pm\n"
				+ "INNER JOIN tbl_material m\n"
				+ "ON pm.TBL_MATERIAL_ID_MATERIAL = m.ID_MATERIAL\n"
				+ "INNER JOIN tbl_producto p\n"
				+ "ON pm.TBL_PRODUCTO_ID_PRODUCTO = p.ID_PRODUCTO\n"
				+ "INNER JOIN tbl_fabricante f\n"
				+ "ON p.TBL_FABRICANTE_ID_FABRICANTE = f.ID_FABRICANTE\n"
				+ "INNER JOIN tbl_registro_costo c\n"
				+ "ON c.TBL_PRODUCTO_ID_PRODUCTO = p.ID_PRODUCTO\n"
				+ "group by p.ID_PRODUCTO;";

		Query query = em.createNativeQuery(consulta);
		//listaDatos = query.getResultList();
		listaProductoPrecio = query.getResultList();
		
		return listaProductoPrecio;

	}

	@Override
	public List<ProductoAuxiliar> datosEspecificacionProducto(String referencia) throws Exception {

		List<Object[]> listaDatos;
		List<ProductoAuxiliar> listaDatosEspecificacionProducto;
		listaDatosEspecificacionProducto = new ArrayList<>();
		String consulta2 = "SELECT es.descripcion\n"
				+ "FROM tbl_producto AS pr \n"
				+ "INNER JOIN tbl_especificacion_producto AS ep\n"
				+ "ON pr.ID_PRODUCTO = ep.TBL_PRODUCTO_ID_PRODUCTO\n"
				+ "INNER JOIN tbl_especificacion AS es \n"
				+ "on ep.TBL_ESPECIFICACION_ID_ESPECIFICACION = es.ID_ESPECIFICACION\n"
				+ "WHERE pr.REFERENCIA = ?1";
		Query query = em.createNativeQuery(consulta2);
		query.setParameter(1, referencia);

		listaDatos = query.getResultList();

		for (Object[] ProductoAuxiliar : listaDatos) {
			ProductoAuxiliar PA = new ProductoAuxiliar();
			PA.setDescripcion(ProductoAuxiliar[0].toString());

			listaDatosEspecificacionProducto.add(PA);

		}

		return listaDatosEspecificacionProducto;
	}

	@Override
	public void crearProductoMaterial(int idProducto, int idMaterial) {
		String insert = "INSERT INTO tbl_producto_material SET TBL_MATERIAL_ID_MATERIAL = ?1,TBL_PRODUCTO_ID_PRODUCTO = ?2";
		Query query = em.createNativeQuery(insert);
		query.setParameter(1, idMaterial);
		query.setParameter(2, idProducto);
		query.executeUpdate();
	}

	@Override
	public void crearProductoDescuento(int idDescuento, int idProducto) {
		//
		String insert = "INSERT INTO tbl_producto_descuento SET TBL_DESCUENTO_ID_DESCUENTO = ?1,TBL_PRODUCTO_ID_PRODUCTO = ?2";
		Query query = em.createNativeQuery(insert);
		query.setParameter(1, idDescuento);
		query.setParameter(2, idProducto);
		query.executeUpdate();
	}
}
