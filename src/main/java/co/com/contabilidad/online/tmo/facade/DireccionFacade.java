package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IDireccion;

@Component
public class DireccionFacade {
	
	@Autowired
	private IDireccion direccion;

	public IDireccion getDireccion() {
		return direccion;
	}
}
