package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface IPersona {
	public List<PersonaDTO> consultarPersonas(final PersonaDTO persona);

	public RespuestaDTO guardarPersona(final PersonaDTO persona , final UsuarioDTO usuario, final EmpresaDTO empresa);

	public RespuestaDTO actualizarPersona(final PersonaDTO persona);

	public RespuestaDTO eliminarPersona(final PersonaDTO persona);


}
