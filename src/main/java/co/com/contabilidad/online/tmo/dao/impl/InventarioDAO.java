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

import co.com.contabilidad.online.tmo.dao.IInventario;
import co.com.contabilidad.online.tmo.dto.DetalleProductoDTO;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TipoGenericoDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class InventarioDAO implements IInventario {
    private static final Logger lOGGER = LoggerFactory.getLogger(ProductoDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public RespuestaDTO consultarInventario(ProductoDTO producto, List<DetalleProductoDTO> lstInventarios) {
		lOGGER.info("Ingreso consultarInventario  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO PRODUCTO------------:  " + producto.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_inventarios(?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setInt(1, producto.getCodigo() != 0 ? producto.getCodigo() :0);
			callableStatement.setString(2, producto.getUsuario().getEmpresa().getRazonSocial() != null ? producto.getUsuario().getEmpresa().getRazonSocial() :"");
			lOGGER.info("---------------FECHA INICIO------------:  " + producto.getFecInicio());
			lOGGER.info("---------------FECHA FIN ------------:  " + producto.getFecFin());
			callableStatement.setString(3, producto.getFecInicio() != null ? producto.getFecInicio() :"");
			callableStatement.setString(4, producto.getFecFin() != null ? producto.getFecFin() :"");
			callableStatement.setString(5, producto.getTipoUnidad() != null ? producto.getTipoUnidad().getCodigo() :"");
			callableStatement.setString(6, producto.getCodigoBarras() != null ? producto.getCodigoBarras() :"");
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(7));
			respuesta.setCodigoError(callableStatement.getString(8));
			respuesta.setDescripcion(callableStatement.getString(9));
			ResultSet results = (ResultSet) callableStatement.getObject(10);
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			if(results != null) {
				while (results.next()) {
					DetalleProductoDTO itemProducto = new DetalleProductoDTO();
					itemProducto.setCantidad(results.getInt(1));
					itemProducto.getProducto().setCodigo(results.getInt(2));
					itemProducto.setPrecioFinal(results.getDouble(3));
					itemProducto.getProducto().setDescripcion(results.getString(4));
					itemProducto.getProducto().setCodigoBarras(results.getString(5));
					itemProducto.setPorcentajeGanancia(results.getInt(6));
					itemProducto.setPrecioUnitario(results.getDouble(7));
					itemProducto.setFecCreacion(results.getString(8));
					itemProducto.setFecModificacion(results.getString(9));
					TipoGenericoDTO tipoUnid = new TipoGenericoDTO();
					tipoUnid.setCodigo(results.getString(10));
					tipoUnid.setDescripcion(results.getString(11));
					itemProducto.setTipoUnidad(tipoUnid);
					itemProducto.setCodigoBarras(results.getString(12));
					
					lstInventarios.add(itemProducto);
				}
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarInventario: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarInventario  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO guardarInventario(DetalleProductoDTO detalleProducto) {
		lOGGER.info("Ingreso guardarInventario  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------CODIGO PRODUCTO------------:  " + detalleProducto.getProducto().getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_inventario(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, detalleProducto.getUsuario().getEmpresa().getRazonSocial() != null ? detalleProducto.getUsuario().getEmpresa().getRazonSocial() :"");
			callableStatement.setInt(2, detalleProducto.getProducto().getCodigo() != 0 ? detalleProducto.getProducto().getCodigo() :null);
			callableStatement.setDouble(3, detalleProducto.getPrecioUnitario() != null ? detalleProducto.getPrecioUnitario() :null);
			callableStatement.setString(4, detalleProducto.getCapacidad() != null ?  detalleProducto.getCapacidad() :"");
			callableStatement.setInt(5, detalleProducto.getCantidad() != 0 ?  detalleProducto.getCantidad() :0);
			callableStatement.setInt(6, detalleProducto.getPorcentajeGanancia() != 0 ?  detalleProducto.getPorcentajeGanancia() :0);
			callableStatement.setDouble(7, detalleProducto.getPrecioFinal() != null ?  detalleProducto.getPrecioFinal() :null);
			callableStatement.setString(8, detalleProducto.getUsuario().getNombre() != null ?  detalleProducto.getUsuario().getNombre() :"");
			callableStatement.setString(9, detalleProducto.getTipoUnidad() != null ?  detalleProducto.getTipoUnidad().getCodigo() :"");
			callableStatement.setString(10, detalleProducto.getCodigoBarras() != null ?  detalleProducto.getCodigoBarras() :"");
			
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.registerOutParameter(13, Types.VARCHAR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(11));
			respuesta.setCodigoError(callableStatement.getString(12));
			respuesta.setDescripcion(callableStatement.getString(13));
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
			
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			
		}catch (Exception e) {
			lOGGER.error("Error guardarInventario: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarInventario  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarInventario(final DetalleProductoDTO detalleProducto) {
		lOGGER.info("Ingreso actualizarInventario  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------CODIGO PRODUCTO------------:  " + detalleProducto.getProducto().getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_actualizar_inventario(?,?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, detalleProducto.getUsuario().getEmpresa().getRazonSocial() != null ? detalleProducto.getUsuario().getEmpresa().getRazonSocial() :"");
			callableStatement.setInt(2, detalleProducto.getProducto().getCodigo() != 0 ? detalleProducto.getProducto().getCodigo() :null);
			callableStatement.setDouble(3, detalleProducto.getPrecioUnitario() != null ? detalleProducto.getPrecioUnitario() :null);
			callableStatement.setString(4, detalleProducto.getCapacidad() != null ?  detalleProducto.getCapacidad() :"");
			callableStatement.setInt(5, detalleProducto.getCantidad() != 0 ?  detalleProducto.getCantidad() :0);
			callableStatement.setInt(6, detalleProducto.getPorcentajeGanancia() != 0 ?  detalleProducto.getPorcentajeGanancia() :0);
			callableStatement.setDouble(7, detalleProducto.getPrecioFinal() != null ?  detalleProducto.getPrecioFinal() :null);
			callableStatement.setString(8, detalleProducto.getUsuario().getNombre() != null ?  detalleProducto.getUsuario().getNombre() :"");
			callableStatement.setString(9, detalleProducto.getTipoUnidad() != null ?  detalleProducto.getTipoUnidad().getCodigo() :"");
			
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(10));
			respuesta.setCodigoError(callableStatement.getString(11));
			respuesta.setDescripcion(callableStatement.getString(12));
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
			
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			
		}catch (Exception e) {
			lOGGER.error("Error actualizarInventario: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida actualizarInventario  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO eliminarInventario(ProductoDTO producto) {
		// TODO Auto-generated method stub
		return null;
	}

}
