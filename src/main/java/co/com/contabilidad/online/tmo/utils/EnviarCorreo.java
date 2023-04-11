package co.com.contabilidad.online.tmo.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EnviarCorreo {

	private static final Logger lOGGER = LoggerFactory.getLogger(EnviarCorreo.class);

	@Autowired
	private PropiedadesConfiguracion propiedades;

	/**
	 * Se realiza la conexion con el servidor correo indicado por un datos de un
	 * properties y se envia el correo.
	 * 
	 * @param destinatarios
	 * @param asunto
	 * @param contenido
	 * @throws Exception
	 */
	public void enviarCorreo(String[] destinatarios, String asunto, String contenido) {
		lOGGER.info("Ingreso enviarCorreo  ");
		try {
			final Properties props = System.getProperties();
			props.setProperty("mail.smtp.host", propiedades.getPropiedad("mail.smtp.host"));
			props.setProperty("mail.smtp.starttls.enable", propiedades.getPropiedad("mail.smtp.starttls.enable"));
			props.setProperty("mail.smtp.auth", propiedades.getPropiedad("mail.smtp.auth"));
			props.setProperty("mail.smtp.port", propiedades.getPropiedad("mail.smtp.port"));
			props.setProperty("mail.smtp.user", propiedades.getPropiedad("mail.smtp.user"));

			String plantilla = leerPlantilla("correo");
			plantilla = replaceVariables(plantilla, contenido);
			Session mailSession = Session.getDefaultInstance(props, null);
			// mailSession.setDebug(true);
			final Transport transport = mailSession.getTransport("smtp");
			final MimeMessage message = new MimeMessage(mailSession);

			message.setSubject(asunto);
			message.setFrom(new InternetAddress(propiedades.getPropiedad("mail.from")));
			message.setContent(plantilla, "text/html");

			for (int i = 0; i < destinatarios.length; i++) {
				String correo = destinatarios[i];
				if (correo != null)
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
			}

			transport.connect(propiedades.getPropiedad("mail.smtp.user"),
					propiedades.getPropiedad("mail.smtp.password"));
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

			transport.close();
		} catch (Exception e) {
			lOGGER.error("Error enviarCorreo: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida enviarCorreo  ");
	}

	/**
	 * Se obtiene la plantilla para el correo.
	 * 
	 * @param nombrePlantilla
	 * @return Objeto String.
	 * @throws Exception
	 */
	private static String leerPlantilla(String nombrePlantilla) {
		lOGGER.info("Ingreso leerPlantilla  ");
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			final String rutaPlantilla = "c:\\tmp\\correo.html";

			final FileInputStream fis = new FileInputStream(rutaPlantilla);
			byte[] buffer = new byte[1024];

			while (fis.read(buffer) != -1) {
				baos.write(buffer);
			}

			baos.flush();
			baos.close();
			fis.close();
		} catch (Exception e) {
			lOGGER.error("Error leerPlantilla: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida leerPlantilla  ");
		return baos.toString();
	}

	/**
	 * Se remplaza el cuerpo de la plantilla con los datos que se envian para el
	 * correo.
	 * 
	 * @param plantilla
	 * @param contenido
	 * @return Objeto String.
	 */
	private static String replaceVariables(String plantilla, String contenido) {
		lOGGER.info("Ingreso leerPlantilla:  ");
		plantilla = plantilla.replace("[CUERPO_MENSAJE]", contenido);
		lOGGER.info("Salida leerPlantilla:  ");
		return plantilla;
	}
}
