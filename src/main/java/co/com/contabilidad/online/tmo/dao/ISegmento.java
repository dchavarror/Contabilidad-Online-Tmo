package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.SegmentoDTO;

public interface ISegmento {
	public List<SegmentoDTO> consultarSegmentos();
    public RespuestaDTO guardarSegmento(final SegmentoDTO segmento);
    public RespuestaDTO actualizarSegmento(final SegmentoDTO segmento);
    public RespuestaDTO eliminarSegmento(final SegmentoDTO segmento);

}
