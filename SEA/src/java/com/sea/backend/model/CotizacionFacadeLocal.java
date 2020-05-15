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
import com.sea.backend.entities.ViewArticulosPorActualizar;
import com.sea.backend.entities.ViewIndexCotizacionesActivas;
import com.sea.backend.entities.ViewIndexOpPorGenerar;
import com.sea.backend.entities.ViewOpEnSeguimiento;
import com.sea.backend.entities.ViewOpPorEstado;
import com.sea.backend.entities.ViewCotizacionPorEstado;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Depurador
 */
@Local
public interface CotizacionFacadeLocal {

	void create(Cotizacion cotizacion);

	void edit(Cotizacion cotizacion);

	void remove(Cotizacion cotizacion);

	Cotizacion find(Object id);

	List<Cotizacion> findAll();

	List<Cotizacion> findRange(int[] range);
	
	List<Cotizacion> listaSeguimiento(int usuario);
	
	List<ViewArticulosPorActualizar> IndexArticulosPorActualizar();
	
	List<ViewIndexCotizacionesActivas> IndexSeguimientoCotizacion(int usuario);
	
	List<ViewIndexOpPorGenerar> IndexOpPorGenerar(int usuario);
	
	List<ViewOpEnSeguimiento> IndexOpEnSeguimiento(int usuario);
	
	List<ViewOpPorEstado> IndexOpPorEstado(int usuario, String estado);

	int count();
	
	void getReportePDF(String ruta, String numero_cotizacion) throws  ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
	
	void getReporteXLSX(String ruta, String numero_cotizacion) throws  ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	String correoUsuario(int usuario);
	
	String correoCliente(int cliente);
	
	String numeroCotizacionUsuario(int usuario);
	
	Object datosCotizacion(String numeroCotizacion) throws Exception;
	
	List<ViewCotizacionPorEstado> cotizacionPorEstados(int usuario, String estado);
	
    Object ModificacionCotizacion(String numeroCotizacion) throws Exception;
	List<Cotizacion> datosRegistradosCotizacion(String numeroCotizacion);
	
	
}
