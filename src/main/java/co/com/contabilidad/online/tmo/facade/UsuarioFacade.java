package co.com.contabilidad.online.tmo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IUsuario;

@Component
public class UsuarioFacade {
	
	@Autowired
	private IUsuario usuario;

	public IUsuario getUsuario() {
		return usuario;
	}
}
