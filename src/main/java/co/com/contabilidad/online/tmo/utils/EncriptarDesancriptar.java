package co.com.contabilidad.online.tmo.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncriptarDesancriptar {
    
	private static final Logger lOGGER = LoggerFactory.getLogger(EncriptarDesancriptar.class);
	private static String secretKey = "";
	
	@Autowired
	private PropiedadesConfiguracion propiedades;
	
	/**
	 * Metodo de encriptacion de la contraseña acesso.
	 * 
	 * @param texto
	 * @return Contraseña encriptada.
	 */
	public String encriptar(String texto) {
		lOGGER.info("Ingreso encriptar  ");
		lOGGER.info("--------------------------ENTRADA TEXTO------------------------  " + texto);
		secretKey = propiedades.getPropiedad("duenio");// llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception e) {
			lOGGER.error("Error encriptar: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("--------------------------SALIDA ENCRITADO------------------------  " + base64EncryptedString);
		lOGGER.info("Salida encriptar  " );
		return base64EncryptedString;
	}

	/**
	 * Metodo de desencriptacion de contraseña.
	 * 
	 * @param textoEncriptado
	 * @return contraseña desencriptada.
	 * @throws Exception
	 */
	public String desencriptar(String textoEncriptado) throws Exception {
		lOGGER.info("Ingreso desencriptar:  " );
		secretKey = "keyconlinetmo";//propiedades.getPropiedad("duenio"); // llave para desenciptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "CONLINE");

			Cipher decipher = Cipher.getInstance("CONLINE");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception e) {
			lOGGER.error("Error desencriptar: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida desencriptar:  " );
		return base64EncryptedString;
	}
	
	public static void main(String[] args) throws Exception {
		EncriptarDesancriptar ed = new EncriptarDesancriptar();
		System.out.println("clave " + ed.encriptar("welcome1"));
	}
}
