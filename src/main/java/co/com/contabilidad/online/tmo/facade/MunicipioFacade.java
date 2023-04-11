package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IMunicipio;

@Component
public class MunicipioFacade {

	@Autowired
	private IMunicipio municipio;

	public IMunicipio getMunicipio() {
		return municipio;
	}

}
