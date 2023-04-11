package co.com.contabilidad.online.tmo.dao;

import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.service.response.ValidarUsuarioResponse;

public interface IUsuario {
	
	public ValidarUsuarioResponse validarUsuario(final UsuarioDTO usuario);
	
	public RespuestaDTO guardarUsuario(final UsuarioDTO usuario, final PersonaDTO persona);

}
