package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DepartamentoDTO;
import co.com.contabilidad.online.tmo.dto.MunicipioDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;

public interface IMunicipio {
	
    public List<MunicipioDTO> consultarMunicipios(final DepartamentoDTO departamento);
	
	public RespuestaDTO guardarMunicipio(final MunicipioDTO municipio);

	public RespuestaDTO actualizarMunicipio(final MunicipioDTO municipio);

	public RespuestaDTO eliminarMunicipio(final MunicipioDTO municipio);

}
