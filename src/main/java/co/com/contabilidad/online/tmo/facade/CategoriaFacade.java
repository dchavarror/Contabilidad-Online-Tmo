package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.ICategoria;

@Component
public class CategoriaFacade {

	@Autowired
	private ICategoria categoria;

	public ICategoria getCategoria() {
		return categoria;
	}
}
