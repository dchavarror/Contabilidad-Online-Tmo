package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IDepartamento;

@Component
public class DepartamentoFacade {

	@Autowired
	private IDepartamento departamento;

	public IDepartamento getDepartamento() {
		return departamento;
	}

}
