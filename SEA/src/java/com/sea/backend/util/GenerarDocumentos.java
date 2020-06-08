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
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author rpc
 */
public class GenerarDocumentos implements Serializable {

	private CotizacionFacadeLocal cotizacionEJB;

	public void generarArchivo(int formatoCotizacion, String numeroCotizacion) {
		cotizacionEJB = new CotizacionFacade();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("/reportes/cotizacion.jasper");

		try {
			if (formatoCotizacion == 1) {
				cotizacionEJB.getReportePDF(ruta, numeroCotizacion);
			} else {
				cotizacionEJB.getReporteXLSX(ruta, numeroCotizacion);
			}

		} catch (Exception e) {
			System.out.println(" Se presento la siguiente excepcion a la hora de generar el pdf o excel");
		}

	}

}
