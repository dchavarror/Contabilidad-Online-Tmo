package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IFactura;

@Component
public class FacturaFacade {
	
	@Autowired
	private IFactura factura;

	public IFactura getFactura() {
		return factura;
	}

}
