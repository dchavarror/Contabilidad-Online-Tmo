package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;



public interface IVenta {
	public RespuestaDTO consultarVentas(final ProductoDTO venta,
			final List<VentaDTO> lstVentas);

	public RespuestaDTO guardarVentas(final List<VentaDTO> lstVentas, final String numeroFactura );

	public RespuestaDTO actualizarVenta(final VentaDTO venta);

	public RespuestaDTO eliminarVenta(final VentaDTO venta);

}
