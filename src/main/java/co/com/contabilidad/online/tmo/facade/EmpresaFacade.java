package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IEmpresa;

@Component
public class EmpresaFacade {

	@Autowired
	private IEmpresa empresa;

	public IEmpresa getEmpresa() {
		return empresa;
	}

}
