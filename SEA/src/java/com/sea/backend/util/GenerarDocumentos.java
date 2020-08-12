/*
 * The MIT License
 *
 * Copyright 2020 rpc.
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
package com.sea.backend.util;

import com.sea.backend.model.CotizacionFacade;
import com.sea.backend.model.CotizacionFacadeLocal;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Andres Quintana
 */
public class GenerarDocumentos implements Serializable {

	private CotizacionFacadeLocal cotizacionEJB;
	private final static Logger log = Logger.getLogger(GenerarDocumentos.class);

	public GenerarDocumentos() {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
    * @author Andres Quintana
	* Fecha modificación 11/08/2020
	* Metodo encargado de generar las cotizaciones en formato pdf o xml
	 */
	public void generarArchivo(int formatoCotizacion, String numeroCotizacion) {
		log.info("Ingreso al proceso de generación de archivos pdf o xml de la cotización # " + numeroCotizacion);
		cotizacionEJB = new CotizacionFacade();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();

		try {
			String ruta = servletContext.getRealPath("/reportes/cotizacion.jasper");
			if (formatoCotizacion == 1) {
				cotizacionEJB.getReportePDF(ruta, numeroCotizacion);
			} else {
				cotizacionEJB.getReporteXLSX(ruta, numeroCotizacion);
			}

		} catch (InstantiationException e) {
			log.error("Se presento la siguiente excepcion a la hora de generar el pdf o excel de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("Se presento la siguiente excepcion a la hora de generar el pdf o excel de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error("Se presento la siguiente excepcion a la hora de generar el pdf o excel de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("Se presento la siguiente excepcion a la hora de generar el pdf o excel de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Se presento la siguiente excepcion a la hora de generar el pdf o excel de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		}

	}

}
