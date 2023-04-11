package co.com.contabilidad.online.tmo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IVenta;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TipoGenericoDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class VentaDAO implements IVenta {
    
	private static final Logger lOGGER = LoggerFactory.getLogger(ProductoDAO.class);
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@Override
	public RespuestaDTO consultarVentas(ProductoDTO producto, List<VentaDTO> lstVentas) {
		lOGGER.info("Ingreso consultarVentas  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------RAZON SOCIAL------------:  " + producto.getUsuario().getEmpresa().getRazonSocial());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_ventas(?,?,?,?,?,?,?) }");
			callableStatement.setString(1, producto.getUsuario().getEmpresa().getRazonSocial() != null ? producto.getUsuario().getEmpresa().getRazonSocial() :"");
			lOGGER.info("---------------FECHA INICIO------------:  " + producto.getFecInicio());
			lOGGER.info("---------------FECHA FIN ------------:  " + producto.getFecFin());
			callableStatement.setString(2, producto.getFecInicio() != null ? producto.getFecInicio():"");
			callableStatement.setString(3, producto.getFecFin() != null ? producto.getFecFin():"");
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(4));
			respuesta.setCodigoError(callableStatement.getString(5));
			respuesta.setDescripcion(callableStatement.getString(6));
			ResultSet results = (ResultSet) callableStatement.getObject(7);
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			if(results != null) {
				while (results.next()) {
					VentaDTO itemVenta = new VentaDTO();
					itemVenta.getProducto().setDescripcion(results.getString(1));
					itemVenta.setCantidad(results.getInt(2));
					itemVenta.setPrecioVenta(results.getDouble(3));
					itemVenta.setValorTotal(results.getDouble(4));
					itemVenta.setNumeroFactura(results.getString(5));
					itemVenta.setFecCreacion(results.getString(6));
					itemVenta.setFecModificacion(results.getString(7));
					TipoGenericoDTO tipoUnid = new TipoGenericoDTO();
					tipoUnid.setDescripcion(results.getString(8));
					itemVenta.getProducto().setTipoUnidad(tipoUnid);
					lstVentas.add(itemVenta);
				}
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarVentas: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarVentas  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO guardarVentas(final List<VentaDTO> lstVentas, final String numeroFactura) {
		lOGGER.info("Ingreso guardarVentas  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(true);
			for(int i=0; i<lstVentas.size(); i++) {
				callableStatement = conn.prepareCall("{ call public.fn_guardar_venta(?,?,?,?,?,?,?,?,?,?,?) }");
				callableStatement.setString(1, lstVentas.get(i).getUsuario().getEmpresa().getRazonSocial() != null ? lstVentas.get(i).getUsuario().getEmpresa().getRazonSocial() :"");
				callableStatement.setInt(2, lstVentas.get(i).getProducto().getCodigo() != 0 ? lstVentas.get(i).getProducto().getCodigo() :null);
				callableStatement.setInt(3, lstVentas.get(i).getCantidad() != 0 ? lstVentas.get(i).getCantidad() :0);
				callableStatement.setDouble(4, lstVentas.get(i).getPrecioVenta() != 0 ? lstVentas.get(i).getPrecioVenta() :null);
				callableStatement.setDouble(5, lstVentas.get(i).getValorTotal() != 0 ? lstVentas.get(i).getValorTotal() :null);
				callableStatement.setString(6, numeroFactura != null ? numeroFactura :"");
				callableStatement.setString(7, lstVentas.get(i).getUsuario().getNombre() != null ? lstVentas.get(i).getUsuario().getNombre() :"");
				callableStatement.setString(8, lstVentas.get(i).getProducto().getTipoUnidad() != null ? lstVentas.get(i).getProducto().getTipoUnidad().getCodigo() :"");
				callableStatement.registerOutParameter(9, Types.VARCHAR);
				callableStatement.registerOutParameter(10, Types.VARCHAR);
				callableStatement.registerOutParameter(11, Types.VARCHAR);
				callableStatement.execute();
				respuesta.setTipoRespuesta(callableStatement.getString(9));
				respuesta.setCodigoError(callableStatement.getString(10));
				respuesta.setDescripcion(callableStatement.getString(11));
				lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
				lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			}
			
		}catch (Exception e) {
			lOGGER.error("Error guardarVentas: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarProducto  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarVenta(VentaDTO venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarVenta(VentaDTO venta) {
		// TODO Auto-generated method stub
		return null;
	}

}
