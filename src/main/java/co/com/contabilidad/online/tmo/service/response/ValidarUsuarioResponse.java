package co.com.contabilidad.online.tmo.service.response;

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public class ValidarUsuarioResponse {

	private RespuestaDTO respuesta;
	private UsuarioDTO usuario;

	public ValidarUsuarioResponse() {
		super();
		this.respuesta = new RespuestaDTO();
		this.usuario = new UsuarioDTO();
	}

	public RespuestaDTO getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaDTO respuesta) {
		this.respuesta = respuesta;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
