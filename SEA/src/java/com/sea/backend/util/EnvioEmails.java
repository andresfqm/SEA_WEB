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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rpc
 */
public class EnvioEmails implements Serializable {

	final String user = "andresfqm@gmail.com";//cambiará en consecuencia al servidor utilizado
	final String pass = "1013621910";

	private String mensaje;

	public void enviarEmail(String fileName, String numeroCotizacion, String rutaArchivo, String emailC, String emailU) throws UnsupportedEncodingException {

		//1st paso) Obtener el objeto de sesión
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.starttls.required", "false");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		//2nd paso)compose message
		try {

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivo + numeroCotizacion + ".pdf")));
			adjunto.setFileName(fileName);

			BodyPart texto = new MimeBodyPart();
			mensaje = "Se envia cotización # " + numeroCotizacion;
			texto.setText(mensaje);
			MimeMultipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(texto);
			multiparte.addBodyPart(adjunto);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, "Fulldotaciones"));
			InternetAddress[] destinatarios = {
				new InternetAddress(emailC),
				new InternetAddress(emailU)
			};

			message.setRecipients(Message.RecipientType.TO, destinatarios);
			message.setSubject("Solicitud de cotizacion");
			message.setContent(multiparte, "text/html; charset=utf-8");

			//3rd paso)send message
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void envioEmailRecuperacionPass(String email, String clave) throws UnsupportedEncodingException {

		//1st paso) Obtener el objeto de sesión
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.starttls.required", "false");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		try {
			BodyPart texto = new MimeBodyPart();
			mensaje = "Su nueva clave de acceso es :  " + clave + " " + "Se recomienda sea cambiada antes de ingresar al sistema";
			texto.setText(mensaje);
			MimeMultipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(texto);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, "Fulldotaciones"));
			InternetAddress[] destinatarios = {
				new InternetAddress(email)
			};

			message.setRecipients(Message.RecipientType.TO, destinatarios);
			message.setSubject("Recuperación de contraseña sistema SEA");
			message.setContent(multiparte, "text/html; charset=utf-8");

			//3rd paso)send message
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
