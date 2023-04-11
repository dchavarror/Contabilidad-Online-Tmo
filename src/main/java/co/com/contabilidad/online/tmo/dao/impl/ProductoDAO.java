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

import co.com.contabilidad.online.tmo.dao.IProducto;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class ProductoDAO implements IProducto {

    private static final Logger lOGGER = LoggerFactory.getLogger(ProductoDAO.class);
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@Override
	public RespuestaDTO consultarProductos(ProductoDTO producto, List<ProductoDTO> lstProductos) {
		lOGGER.info("Ingreso consultarProductos  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO PRODUCTO------------:  " + producto.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_productos(?,?,?,?,?,?) }");
			callableStatement.setInt(1, producto.getCodigo() != 0 ? producto.getCodigo() :0);
			callableStatement.setInt(2, producto.getCodigoCategoria() != 0 ? producto.getCodigoCategoria() :0);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(3));
			respuesta.setCodigoError(callableStatement.getString(4));
			respuesta.setDescripcion(callableStatement.getString(5));
			ResultSet results = (ResultSet) callableStatement.getObject(6);
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			while (results.next()) {
				ProductoDTO itemProducto = new ProductoDTO(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4));
				lstProductos.add(itemProducto);
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarProductos: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarProductos  ");
		return respuesta;
		
	}

	@Override
	public RespuestaDTO guardarProducto(ProductoDTO producto) {
		lOGGER.info("Ingreso guardarProducto  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------CODIGO PRODUCTO------------:  " + producto.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_producto(?,?,?,?,?,?,?) }");
			callableStatement.setString(1, producto.getDescripcion() != null ? producto.getDescripcion() :"");
			callableStatement.setString(2,  producto.getCodigoBarras() != null ? producto.getCodigoBarras() :"");
			callableStatement.setInt(3,  producto.getCodigoCategoria() != 0 ? producto.getCodigoCategoria() :0);
			callableStatement.setString(4, producto.getUsuario().getNombre() != null ?  producto.getUsuario().getNombre() :"");
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(5));
			respuesta.setCodigoError(callableStatement.getString(6));
			respuesta.setDescripcion(callableStatement.getString(7));
			lOGGER.info("Respuesta tipo: " + respuesta.getTipoRespuesta());
			lOGGER.info("Respuesta descripcion: " + respuesta.getDescripcion());
			
		}catch (Exception e) {
			lOGGER.error("Error guardarProducto: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarProducto  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarProducto(ProductoDTO producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarProducto(ProductoDTO producto) {
		// TODO Auto-generated method stub
		return null;
	}

}
