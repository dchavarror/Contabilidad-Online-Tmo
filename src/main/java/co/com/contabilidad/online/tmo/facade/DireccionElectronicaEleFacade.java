package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IDireccionElectronica;

@Component
public class DireccionElectronicaEleFacade {

	@Autowired
	private IDireccionElectronica direccionEle;

	public IDireccionElectronica getDireccionEle() {
		return direccionEle;
	}

}
