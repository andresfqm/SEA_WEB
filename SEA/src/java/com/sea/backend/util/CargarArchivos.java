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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rpc
 */
public class CargarArchivos implements Serializable{
	
	public static String cargarArchivos(UploadedFile fi, String pathArchivo) {
		String pathReal1 = null;
		System.out.println("path = " + pathArchivo);
		System.out.println("Archivo :: " + fi.getFileName());
		try {
			String nombreArchivo = fi.getFileName();
			pathArchivo += "\\" + nombreArchivo;
			pathReal1 =  pathArchivo;
			InputStream input = fi.getInputstream();
			byte[] data = new byte[input.available()];
			input.read(data);
			FileOutputStream output = new FileOutputStream(pathArchivo);
			System.out.println("path:: " + pathArchivo);
			output.write(data);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return pathReal1;

	}
	
}
