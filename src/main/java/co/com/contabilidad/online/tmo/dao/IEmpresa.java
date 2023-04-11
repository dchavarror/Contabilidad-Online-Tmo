package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface IEmpresa {
	
    public List<EmpresaDTO> consultarEmpresas(final EmpresaDTO empresa);
	
	public RespuestaDTO guardarEmpresa(final EmpresaDTO empresa , final PersonaDTO persona, final UsuarioDTO usuario);

	public RespuestaDTO actualizarEmpresa(final EmpresaDTO empresa);

	public RespuestaDTO eliminarEmpresa(final EmpresaDTO empresa);

}
