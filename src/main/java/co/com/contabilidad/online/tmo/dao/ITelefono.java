package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface ITelefono {
	
    public List<TelefonoDTO> consultarTelefonos(final String razonSocial, final String numeroIdentificacion, final RespuestaDTO respuesta);
	
	public RespuestaDTO guardarTelefono(final List<TelefonoDTO> lstTelefonos, final UsuarioDTO usuario, final EmpresaDTO empresa, final PersonaDTO persona);

	public RespuestaDTO actualizarTelefono(final TelefonoDTO telefono);

	public RespuestaDTO eliminarTelefono(final TelefonoDTO telefono);

}
