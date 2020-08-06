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
package com.sea.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author rpc
 */
public class Main {

	private final static Logger log = Logger.getLogger(Main.class);

	public static void main(String Args[]) throws FileNotFoundException, IOException {
		int hora, minutos, segundos;
		Calendar calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);

		System.out.println(hora + ":" + minutos + ":" + segundos);
		
		System.out.println("Fecha y hora con new date " + new Date());

//		PropertyConfigurator.configure("log4j.properties");
//		log.warn("un warning");
//		log.error("un error");
//
//		log.info("iniciando aplicación");
//
//		for (int i = 10; i >= 0; i--) {
//			try {
//				double n = 100 / i * 1.0;
//				log.debug("el valor de n=" + n);
//			} catch (Exception ex) {
//				log.error(ex);
//			}
//		}
//		Properties p = new Properties();
//		InputStream entrada = null;
//
//		String x;
//
//		try {
//			entrada = new FileInputStream("config.properties");
//			p.load(entrada);
//			System.out.println("uno=" + p.getProperty("rutapdf"));
//			x = p.getProperty("rutapdf");
//			System.out.println("x" + x);
//		} catch (Exception e) {
//			System.out.println("Se presento error : " + e.getMessage());
//		}
	}

}
