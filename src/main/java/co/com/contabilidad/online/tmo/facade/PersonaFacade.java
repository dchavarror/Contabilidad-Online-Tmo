package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IPersona;

@Component
public class PersonaFacade {

	@Autowired
	private IPersona persona;

	public IPersona getPersona() {
		return persona;
	}

}
