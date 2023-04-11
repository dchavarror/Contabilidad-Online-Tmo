package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.FacturaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public interface IFactura {
	
	public RespuestaDTO consultarFacturas(final String numero,final List<FacturaDTO> lstCategorias);
    public RespuestaDTO guardarCategoria(final FacturaDTO factura);
    public RespuestaDTO actualizarCategoria(final FacturaDTO factura);
    public RespuestaDTO eliminarCategoria(final FacturaDTO factura);
    public FacturaDTO generarNumeroFactura(final String razonSocial, final UsuarioDTO usuario, final RespuestaDTO respuesta);

}
