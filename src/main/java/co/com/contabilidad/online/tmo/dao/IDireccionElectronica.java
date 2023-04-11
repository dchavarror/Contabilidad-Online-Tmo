package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DireccionElectronicaDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface IDireccionElectronica {
	
    public List<DireccionElectronicaDTO> consultarDireccionesEle(final DireccionElectronicaDTO direccion);
	
	public RespuestaDTO guardarDireccionEle(final DireccionElectronicaDTO direccion, final UsuarioDTO usuario, final EmpresaDTO empresa, final PersonaDTO persona);

	public RespuestaDTO actualizarDireccionEle(final DireccionElectronicaDTO direccion);

	public RespuestaDTO eliminarDireccionEle(final DireccionElectronicaDTO direccion);
}
