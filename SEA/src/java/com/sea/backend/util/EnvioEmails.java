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

import com.sea.backend.entities.Email;
import com.sea.backend.entities.Usuario;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Andres Quintana
 */
public class EnvioEmails implements Serializable {

	private String user = "";
	private String pass = "";
	private String empresa = "";
	private String mensaje;
	Properties properties;
	private final static Logger log = Logger.getLogger(EnvioEmails.class);

	public EnvioEmails() {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(props);

			properties = new Properties();
			InputStream entrada = null;
			entrada = new FileInputStream("config.properties");
			properties.load(entrada);
			user = properties.getProperty("user");
			pass = properties.getProperty("pass");
			empresa = properties.getProperty("empresa");

		} catch (Exception e) {
			log.error("Se presento el siguiente error al leer el archivo properties  " + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
    * @author Andres Quintana
	* Fecha modificación 11/08/2020
	* Metodo encargado de enviar las cotizaciones via mail
	 */
	public void enviarEmail(String fileName, String extension, String numeroCotizacion, String rutaArchivo, List<String> emailC, String emailU, String mensaje) {

		log.info("Ingreso al proceso de envio de email de la cotización # " + numeroCotizacion);

		//2nd paso)compose message
		try {

			//1st paso) Obtener el objeto de sesión
			Properties props = new Properties();
			props = cargarDatosServidorDeCorreos();

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			});

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaArchivo + numeroCotizacion + extension)));
			adjunto.setFileName(fileName);

			BodyPart texto = new MimeBodyPart();
			texto.setText(mensaje);
			MimeMultipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(texto);
			multiparte.addBodyPart(adjunto);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, empresa));

			String dest1 = "";
			String dest2 = "";
			int count = 0;
			if (emailC.size() > 0) {
				for (String e : emailC) {
					if (count == 0) {
						dest1 = e;
						count = count + 1;
					} else if (count == 1) {
						dest2 = e;
					}
				}
			}

			if (dest2.isEmpty()) {
				dest2 = dest1;
			}
			InternetAddress[] destinatarios = {
				new InternetAddress(dest1),
				new InternetAddress(dest2),
				new InternetAddress(emailU)
			};

			message.setRecipients(Message.RecipientType.TO, destinatarios);
			message.setSubject("Solicitud de cotizacion");
			message.setContent(multiparte, "text/html; charset=utf-8");

			//3rd paso)send message
			Transport.send(message);

		} catch (MessagingException e) {
			log.error("Se presento el siguiente error al enviar el correo de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("Se presento el siguiente error al enviar el correo de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Se presento el siguiente error al enviar el correo de la cotización # " + numeroCotizacion + " " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
    * @author Andres Quintana
	* Fecha modificación 11/08/2020
	* Metodo encargado de enviar los mails de recuperación de contraseñas
	 */
	public void envioEmailRecuperacionPass(String email, String clave) {

		log.info("Ingreso al proceso de envio de email para la recuperación de contraseña");

		try {
			//1st paso) Obtener el objeto de sesión
			Properties props = new Properties();
			props = cargarDatosServidorDeCorreos();

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			});

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

		} catch (MessagingException e) {
			log.error("Se presento el siguiente error al enviar el correo de recuperación de contraseña : " + " " + e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("Se presento el siguiente error al enviar el correo de recuperación de contraseña : " + " " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Se presento el siguiente error al enviar el correo de recuperación de contraseña : " + " " + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
    * @author Andres Quintana
	* Fecha creación 11/08/2020
	* Metodo encargado de setear los valores del servidor de correos
	 */
	private Properties cargarDatosServidorDeCorreos() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.starttls.required", "false");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		return props;
	}

	
	/*
    * @author Andres Quintana
	* Fecha creación 14/08/2020
	* Metodo encargado de enviar correo con la confirmación de la creación del usuario y detalla los datos de acceso
	 */
	public String enviarEmailRegistroUsuarios(Usuario usuario, String urlIngreso) {
		log.info("Ingreso al proceso de envio de email de creación de usuario del usuario : " + usuario);
		String respuestaEnvio = "";
		try {
			//1st paso) Obtener el objeto de sesión
			Properties props = new Properties();
			props = cargarDatosServidorDeCorreos();

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			});

			String contraseñaDesencriptada = Encriptacion.Desencriptar(usuario.getContrasena());
			BodyPart texto = new MimeBodyPart();
			texto.setText("Bienvenido al sistema SEA\n" + usuario.getNombre() + "\n"
					+ "\n" + "Sus datos de ingreso al sistema son los siguientes : " + "\n"
					+ "Usuario: " + usuario.getNombreUsuario() + "\n" + "Contraseña: " + contraseñaDesencriptada
					+ "\n" + "ID interno: " + usuario.getIdInterno() + "\n" + "Url ingreso al sistema: " + urlIngreso
					+ "\n" + "\n"
					+ "Cordialmente," + "\n" + empresa);
			MimeMultipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(texto);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, empresa));

			String EmailDestino = "";
			for (Email email : usuario.getEmailList()) {
				EmailDestino = email.getEmail();
				break;
			}
			message.setRecipients(Message.RecipientType.TO, EmailDestino);
			message.setSubject("Creación de usuario " + usuario.getNombre() + " " + usuario.getApellido());
			message.setContent(multiparte, "text/html; charset=utf-8");

			//3rd paso)send message
			Transport.send(message);
			respuestaEnvio = "OK";
		} catch (Exception e) {
			log.error("Se presento el siguiente error al enviar el correo de creación de usuarios del usuario " + usuario.getNombreUsuario() + e.getMessage());
			e.printStackTrace();
			return respuestaEnvio = "FALLO";
		}
		return respuestaEnvio;

	}
}
