package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UnidadDTO;

public interface IUnidad {
	public RespuestaDTO consultarUnidades(final Integer codigoCategoria,final List<UnidadDTO> lstUnidades);
    public RespuestaDTO guardarUnidad(final UnidadDTO parametro);
    public RespuestaDTO actualizarUnidad(final UnidadDTO parametro);
    public RespuestaDTO eliminarUnidad(final UnidadDTO parametro);
}
