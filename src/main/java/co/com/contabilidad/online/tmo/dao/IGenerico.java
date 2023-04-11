package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TipoGenericoDTO;

public interface IGenerico {
    public RespuestaDTO consultarParametrosGenerales(final TipoGenericoDTO parametro, final  List<TipoGenericoDTO> lstParametros);
    public RespuestaDTO guardarParametroGenerales(final TipoGenericoDTO parametro);
    public RespuestaDTO actualizarParametrosGenerales(final TipoGenericoDTO parametro);
    public RespuestaDTO eliminarParametroGenerales(final TipoGenericoDTO parametro);
}
