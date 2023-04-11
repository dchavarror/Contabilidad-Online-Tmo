package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.CategoriaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;

public interface ICategoria {
	public RespuestaDTO consultarCategorias(final Integer codigoSeg,final List<CategoriaDTO> lstCategorias);
    public RespuestaDTO guardarCategoria(final CategoriaDTO parametro);
    public RespuestaDTO actualizarCategoria(final CategoriaDTO parametro);
    public RespuestaDTO eliminarCategoria(final CategoriaDTO parametro);
}
