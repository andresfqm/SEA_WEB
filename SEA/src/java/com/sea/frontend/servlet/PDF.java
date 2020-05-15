/*
 * The MIT License
 *
 * Copyright 2017 EdisonArturo.
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
package com.sea.frontend.servlet;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author EdisonArturo
 */
public class PDF extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, JRException {
		try {
			Connection conexion;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException ex) {
				Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
			}
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/fulldotaciones", "root", "");

			Map parameters = new HashMap();
//A nuestro informe de prueba le vamos a enviar la fecha de hoy
			parameters.put("numero_cotizacion", "AD1 -1");

			ServletOutputStream out;
/// Creamos un objecto jasper con el fichero previamente compilado

			JasperReport jasperReportjasperReport;
			jasperReportjasperReport = (JasperReport) JRLoader.loadObject("C:\\Users\\EdisonArturo\\Documents\\NetBeansProjects\\SEA\\src\\java\\com\\sea\\backend\\reportes\\Cotizacion.jasper");

// Generamos el informe pasandole como parametros el objecto creado anteriormente jasperReport, parameters que es el hashmap
// creado anteriormente con todos los parametros que necesitemos enviarle al informe y conn una conexión activa con vuestro
// servidor de BD
			byte[] fichero = JasperRunManager.runReportToPdf("C:\\Users\\EdisonArturo\\Documents\\NetBeansProjects\\SEA\\src\\java\\com\\sea\\backend\\reportes\\Cotizacion.jasper", parameters, conexion);

// Y enviamos el pdf a la salida del navegador como podríamos hacer con cualquier otro pdf
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
			response.setHeader("Cache-Control", "max-age=30");
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(fichero.length);
			out = response.getOutputStream();

			out.write(fichero, 0, fichero.length);
			out.flush();
			out.close();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		}

	}


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JRException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JRException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
		public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
