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

import com.sea.backend.entities.Cotizacion;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.ViewIndexCotizacionesActivas;
import com.sea.backend.entities.ViewArticulosPorActualizar;
import com.sea.backend.entities.ViewCotizacionPorEstado;
import com.sea.backend.entities.ViewIndexOpPorGenerar;
import com.sea.backend.entities.ViewOpEnSeguimiento;
import com.sea.backend.entities.ViewOpPorEstado;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Depurador
 */
@Stateless
public class CotizacionFacade extends AbstractFacade<Cotizacion> implements CotizacionFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CotizacionFacade() {
		super(Cotizacion.class);
	}

	@Override
	public List<Cotizacion> listaSeguimiento(int usuario) {
		List<Cotizacion> listaSeguimientoCotizacions;

		String consulta = "SELECT c.numero_cotizacion, cl.nombre_o_razon_social, cl.nombre_contacto, t.numero_telefono\n"
				+ "FROM tbl_cotizacion AS c\n"
				+ "INNER JOIN tbl_cliente as cl \n"
				+ "ON c.TBL_CLIENTE_ID_CLIENTE = cl.ID_CLIENTE \n"
				+ "INNER JOIN tbl_telefono AS t \n"
				+ "ON cl.ID_CLIENTE = t.TBL_CLIENTE_ID_CLIENTE\n"
				+ "INNER JOIN tbl_usuario AS u \n"
				+ "ON cl.TBL_USUARIO_ID_USUARIO = u.ID_USUARIO\n"
				+ "WHERE id_usuario = ?1";
		Query query = em.createNativeQuery(consulta);
		query.setParameter(1, usuario);

		listaSeguimientoCotizacions = query.getResultList();
		return listaSeguimientoCotizacions;
	}

	@Override
	public List<ViewArticulosPorActualizar> IndexArticulosPorActualizar() {
		List<ViewArticulosPorActualizar> listaArticulosPorActualizar = em.createNamedQuery("ViewArticulosPorActualizar.findAll")
				.getResultList();
		return listaArticulosPorActualizar;
	}

	@Override
	public List<ViewIndexCotizacionesActivas> IndexSeguimientoCotizacion(int idUsuario) {
		List<ViewIndexCotizacionesActivas> listaIndexSeguimientoCotizacion = em.createNamedQuery("ViewIndexCotizacionesActivas.findByUsuario")
				.setParameter("usuario", idUsuario)
				.getResultList();
		return listaIndexSeguimientoCotizacion;
	}

	@Override
	public List<ViewIndexOpPorGenerar> IndexOpPorGenerar(int idUsuario) {
		List<ViewIndexOpPorGenerar> listaIndexOpPorGenerar = em.createNamedQuery("ViewIndexOpPorGenerar.findByUsuario")
				.setParameter("usuario", idUsuario)
				.getResultList();
		return listaIndexOpPorGenerar;
	}

	@Override
	public List<ViewOpEnSeguimiento> IndexOpEnSeguimiento(int idUsuario) {
		List<ViewOpEnSeguimiento> listaOpEnSeguimiento = em.createNamedQuery("ViewOpEnSeguimiento.findByUsuario")
				.setParameter("usuario", idUsuario)
				.getResultList();
		return listaOpEnSeguimiento;
	}

	@Override
	public List<ViewOpPorEstado> IndexOpPorEstado(int idUsuario, String estado) {
		List<ViewOpPorEstado> listaIndexOpPorEstado = em.createNamedQuery("ViewOpPorEstado.findByUsuarioAndEstado")
				.setParameter("usuario", idUsuario)
				.setParameter("estado", estado)
				.getResultList();
		return listaIndexOpPorEstado;
	}

	@Override
	public String correoCliente(int cliente) {
		Object email = null;
		String correo;
		String consulta = "SELECT e.email\n"
				+ "FROM tbl_email AS e\n"
				+ "INNER JOIN tbl_cliente as c \n"
				+ "ON e.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE \n"
				+ "WHERE id_cliente = ?1";
		Query query = em.createNativeQuery(consulta);
		query.setParameter(1, cliente);
		email = query.getSingleResult();
		correo = email.toString();
		return correo;
	}

	@Override
	public String correoUsuario(int usuario) {
		Object email = null;
		String correo;
		String consulta = "SELECT e.email\n"
				+ "FROM tbl_email AS e\n"
				+ "INNER JOIN tbl_usuario as u \n"
				+ "ON e.TBL_USUARIO_ID_USUARIO = u.ID_USUARIO \n"
				+ "WHERE id_usuario = ?1";
		Query query = em.createNativeQuery(consulta);
		query.setParameter(1, usuario);
		email = query.getSingleResult();
		correo = email.toString();
		return correo;
	}

	@Override
	public void getReportePDF(String ruta, String numero_cotizacion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conexion = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {

			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fulldotaciones?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "Tempo123#$");

			//Se definen los parametros si es que el reporte necesita
			Map parameter = new HashMap();
			parameter.put("numero_cotizacion", numero_cotizacion);

			File file = new File(ruta);
			String destino = "D:\\SEA\\Reportes\\PDF\\cotizacion_N_" + numero_cotizacion + ".pdf";

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

			JasperExportManager.exportReportToPdfFile(jasperPrint, destino);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				try {
					conexion.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void getReporteXLSX(String ruta, String numero_cotizacion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conexion;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fulldotaciones?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "Tempo123#$");

		//Se definen los parametros si es que el reporte necesita
		Map parameter = new HashMap();
		parameter.put("numero_cotizacion", numero_cotizacion);
		String destino = "D:\\SEA\\Reportes\\EXCEL\\cotizacion_N_" + numero_cotizacion + ".xlsx";

		try {
			File file = new File(ruta);

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

			JRExporter jrExporter = null;
			jrExporter = new JRXlsxExporter();
			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(destino));

			if (jrExporter != null) {
				try {
					jrExporter.exportReport();
				} catch (JRException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				try {
					conexion.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Object datosCotizacion(String numeroCotizacion) throws Exception {

		String consulta2 = "SELECT co.numero_cotizacion, c.nombre_o_razon_social, ci.nombre, e.email, d.direccion, co.lugar_emision\n"
				+ "FROM tbl_cotizacion AS co\n"
				+ "INNER JOIN tbl_cliente as c \n"
				+ "ON co.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE \n"
				+ "INNER JOIN tbl_usuario AS u \n"
				+ "ON c.TBL_USUARIO_ID_USUARIO = u.ID_USUARIO\n"
				+ "INNER JOIN\n"
				+ "TBL_EMAIL e ON c.ID_CLIENTE = e.TBL_CLIENTE_ID_CLIENTE\n"
				+ "INNER JOIN\n"
				+ "TBL_TIPO_EMAIL te ON e.TBL_TIPO_EMAIL_ID_TIPO_EMAIL = te.ID_TIPO_EMAIL\n"
				+ "INNER JOIN\n"
				+ "TBL_DIRECCION d ON d.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE\n"
				+ "INNER JOIN\n"
				+ "TBL_TIPO_DIRECCION tdi ON d.TBL_TIPO_DIRECCION_ID_TIPO_DIRECCION = tdi.ID_TIPO_DIRECCION\n"
				+ "INNER JOIN\n"
				+ "TBL_CIUDAD ci ON d.TBL_CIUDAD_ID_CIUDAD = ci.ID_CIUDAD\n"
				+ "WHERE numero_cotizacion = ?1";

		Query query = em.createNativeQuery(consulta2);
		query.setParameter(1, numeroCotizacion);

		//datosCliente = query.getResultList();
		Object datosCotizacion = query.getSingleResult();

		return datosCotizacion;
	}

	@Override
	public List<ViewCotizacionPorEstado> cotizacionPorEstados(int usuario, String estado) {
		List<ViewCotizacionPorEstado> listaCotizacionesPorEstados = em.createNamedQuery("ViewCotizacionPorEstado.findByUsuarioAndEstado")
				.setParameter("usuario", usuario)
				.setParameter("estado", estado)
				.getResultList();
		return listaCotizacionesPorEstados;
	}

	@Override
	public Object ModificacionCotizacion(String numeroCotizacion) throws Exception {
		String consulta5 = "SELECT co.numero_cotizacion, co.lugar_emision, co.fecha_emision, \n"
				+ "                c.nombre_o_razon_social, ci.nombre, de.nombre, e.email, t.numero_telefono \n"
				+ "				FROM tbl_cotizacion AS co\n"
				+ "				INNER JOIN tbl_cliente AS c\n"
				+ "				ON co.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE\n"
				+ "				INNER JOIN\n"
				+ "				TBL_EMAIL e ON c.ID_CLIENTE = e.TBL_CLIENTE_ID_CLIENTE\n"
				+ "				INNER JOIN\n"
				+ "				TBL_TIPO_EMAIL te ON e.TBL_TIPO_EMAIL_ID_TIPO_EMAIL = te.ID_TIPO_EMAIL\n"
				+ "				INNER JOIN\n"
				+ "				TBL_TELEFONO t ON c.ID_CLIENTE = t.TBL_CLIENTE_ID_CLIENTE\n"
				+ "				INNER JOIN\n"
				+ "				TBL_TIPO_TELEFONO tt ON t.TBL_TIPO_TELEFONO_ID_TIPO_TELEFONO = tt.ID_TIPO_TELEFONO\n"
				+ "                INNER JOIN\n"
				+ "                TBL_DIRECCION d ON d.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE\n"
				+ "                INNER JOIN\n"
				+ "                TBL_TIPO_DIRECCION tdi ON d.TBL_TIPO_DIRECCION_ID_TIPO_DIRECCION = tdi.ID_TIPO_DIRECCION\n"
				+ "                INNER JOIN\n"
				+ "                TBL_CIUDAD ci ON d.TBL_CIUDAD_ID_CIUDAD = ci.ID_CIUDAD\n"
				+ "                INNER JOIN\n"
				+ "                TBL_DEPARTAMENTO de ON ci.TBL_DEPARTAMENTO_ID_DEPARTAMENTO = de.ID_DEPARTAMENTO\n"
				+ "				WHERE numero_cotizacion = ?1";

		Query query = em.createNativeQuery(consulta5);
		query.setParameter(1, numeroCotizacion);

		//datosCliente = query.getResultList();
		Object ModificacionCotizacion = query.getSingleResult();

		return ModificacionCotizacion;
	}

	@Override
	public List<Cotizacion> datosRegistradosCotizacion(String numeroCotizacion) {

		List<Cotizacion> listaDatosRegistradosCotizacion;
		String consulta6 = "SELECT  co.lugar_emision, co.fecha_emision, co.numero_remision, co.visita, co.prestamo_muestra, co.descuento, co.iva,co.validez_oferta,\n"
				+ " mp.descripcion, le.descripcion, dv.descripcion, te.descripcion, pn.descripcion\n"
				+ "FROM tbl_cotizacion AS co\n"
				+ "INNER JOIN tbl_modalidad_de_pago AS mp\n"
				+ "ON co.tbl_modalidad_de_pago_id_modalidad_de_pago = mp.id_modalidad_de_pago\n"
				+ "INNER JOIN tbl_lugares_entrega AS le\n"
				+ "ON co.tbl_lugares_entrega_id_lugares_entrega = le.id_lugares_entrega\n"
				+ "INNER JOIN tbl_descuento_volumen AS dv\n"
				+ "ON co.tbl_descuento_volumen_id_descuento_volumen = dv.id_descuento_volumen\n"
				+ "INNER JOIN tbl_tiempo_entrega AS te\n"
				+ "ON co.tbl_tiempo_entrega_id_tiempo_entrega = te.id_tiempo_entrega\n"
				+ "INNER JOIN tbl_propuesta_no_incluye AS pn\n"
				+ "ON co.tbl_propuesta_no_incluye_id_propuesta_no_incluye = pn.id_propuesta_no_incluye\n"
				+ "WHERE numero_cotizacion = ?1";
		Query query = em.createNativeQuery(consulta6);
		query.setParameter(1, numeroCotizacion);
		listaDatosRegistradosCotizacion = query.getResultList();
		return listaDatosRegistradosCotizacion;

	}

	@Override
	public String numeroCotizacionUsuario(int usuario) {
		Object usu = null;
		String numeroCotizacionUsuario = "";
		String consulta = "SELECT fn_obtener_consecutivo_cotizacion(?)";
		Query query = em.createNativeQuery(consulta);
		query.setParameter(1, usuario);
		usu = query.getSingleResult();
		if (usu != null) {
			numeroCotizacionUsuario = usu.toString();
		} else {

			numeroCotizacionUsuario = "0";
		}

		return numeroCotizacionUsuario;
	}
}
