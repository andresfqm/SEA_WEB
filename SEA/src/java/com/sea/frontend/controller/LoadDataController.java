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
package com.sea.frontend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author Depurador
 */
@Named
@ViewScoped
public class LoadDataController implements Serializable {

	@PostConstruct
	public void init() {
	}
	private Part fileCSV;
	private String nombre;
	private String pathReal;
	private String tabla;

	public void uploadFile() {
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("uploadFiles");
		path = path.substring(0, path.indexOf("\\build"));
		path = path + "\\uploadFiles\\";
		try {
			this.nombre = fileCSV.getSubmittedFileName();
			pathReal = "/uploadFiles/" + nombre;
			path = path + this.nombre;
			InputStream in = fileCSV.getInputStream();
			byte[] data = new byte[in.available()];
			in.read(data);
			FileOutputStream out = new FileOutputStream(new File(path));
			out.write(data);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("pathReal: "+pathReal);
		System.out.println("path: "+path);
	}
	
	
	
	public void loadData(){
		
	}

	public Part getFileCSV() {
		return fileCSV;
	}

	public void setFileCSV(Part fileCSV) {
		this.fileCSV = fileCSV;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPathReal() {
		return pathReal;
	}

	public void setPathReal(String pathReal) {
		this.pathReal = pathReal;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
	public void leerTabla(){
		tabla = tabla;
	}
	
}
