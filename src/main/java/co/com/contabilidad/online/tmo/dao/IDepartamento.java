package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DepartamentoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;

public interface IDepartamento {
	
	public List<DepartamentoDTO> consultarDepartamentos(final DepartamentoDTO departamento);
	
	public RespuestaDTO guardarDepartamento(final DepartamentoDTO departamento);

	public RespuestaDTO actualizarDepartamento(final DepartamentoDTO departamento);

	public RespuestaDTO eliminarDepartamento(final DepartamentoDTO departamento);

}
