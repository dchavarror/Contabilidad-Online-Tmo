package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IGenerico;

@Component
public class GenericoFacade {
    
	@Autowired
	private IGenerico generico;

	public IGenerico getGenerico() {
		return generico;
	}
}
