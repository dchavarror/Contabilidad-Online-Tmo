package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IProducto;

@Component
public class ProductoFacade {

	@Autowired
	private IProducto producto;

	public IProducto getProducto() {
		return producto;
	}

	
}
