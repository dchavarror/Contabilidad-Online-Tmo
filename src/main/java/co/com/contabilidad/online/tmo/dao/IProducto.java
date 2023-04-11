package co.com.contabilidad.online.tmo.dao;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;

public interface IProducto {

	public RespuestaDTO consultarProductos(final ProductoDTO producto,
			final List<ProductoDTO> lstProductos);

	public RespuestaDTO guardarProducto(final ProductoDTO producto );

	public RespuestaDTO actualizarProducto(final ProductoDTO producto);

	public RespuestaDTO eliminarProducto(final ProductoDTO producto);
}
