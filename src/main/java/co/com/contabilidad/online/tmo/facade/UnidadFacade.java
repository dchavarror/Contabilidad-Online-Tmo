package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IUnidad;


@Component
public class UnidadFacade {
	@Autowired
	private IUnidad unidad;

	public IUnidad getUnidad() {
		return unidad;
	}

}
