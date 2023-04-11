package co.com.contabilidad.online.tmo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropiedadesConfiguracion {

	private static final Logger lOGGER = LoggerFactory.getLogger(PropiedadesConfiguracion.class);
	  
	@Autowired
	private Environment env;
	
	public String getPropiedad(final String llave) {
		lOGGER.info("Ingreso getPropiedad  " );
		try {
			String valorPropiedad = env.getProperty(llave);
			if (valorPropiedad != null && !valorPropiedad.isEmpty()) {
				return valorPropiedad;
			}
		} catch (Exception e) {
			lOGGER.error("Error getPropiedad: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida getPropiedad  ");
		return null;
	}
}
