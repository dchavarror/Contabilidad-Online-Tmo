package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface IDireccion {
	
    public List<DireccionDTO> consultarDirecciones(final String razonSocial, final String numeroIdentificacion, final RespuestaDTO respuesta);
	
	public RespuestaDTO guardarDireccion(final DireccionDTO direccion , final UsuarioDTO usuario, final EmpresaDTO empresa, final PersonaDTO persona);

	public RespuestaDTO actualizarDireccion(final DireccionDTO direccion);

	public RespuestaDTO eliminarDireccion(final DireccionDTO direccion);
}
