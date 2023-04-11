package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DetalleProductoDTO;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;

public interface IInventario {
	public RespuestaDTO consultarInventario(final ProductoDTO producto,
			final List<DetalleProductoDTO> lstInventarios);

	public RespuestaDTO guardarInventario(final DetalleProductoDTO detalleProducto );

	public RespuestaDTO actualizarInventario(final DetalleProductoDTO detalleProducto);

	public RespuestaDTO eliminarInventario(final ProductoDTO producto);

}
