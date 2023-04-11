package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.ITelefono;

@Component
public class TelefonoFacade {
	
	@Autowired
	private ITelefono telefono;

	public ITelefono getTelefono() {
		return telefono;
	}
}
