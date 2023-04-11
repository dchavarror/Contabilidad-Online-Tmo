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

import co.com.contabilidad.online.tmo.dao.ICategoria;
import co.com.contabilidad.online.tmo.dto.CategoriaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class CategoriaDAO implements ICategoria {
    
	private static final Logger lOGGER = LoggerFactory.getLogger(CategoriaDAO.class);
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@Override
	public RespuestaDTO consultarCategorias(final Integer codigoSeg,final List<CategoriaDTO> lstCategorias) {
		
		lOGGER.info("Ingreso consultarCategorias  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO SEGMENTO------------:  " + codigoSeg);
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_categorias(?,?,?,?,?) }");
			callableStatement.setInt(1, codigoSeg != null ? codigoSeg :0);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(2));
			respuesta.setCodigoError(callableStatement.getString(3));
			respuesta.setDescripcion(callableStatement.getString(4));
			ResultSet results = (ResultSet) callableStatement.getObject(5);
			
			while (results.next()) {
				CategoriaDTO itemParametro = new CategoriaDTO(results.getInt(1),results.getString(2) , codigoSeg);
				lstCategorias.add(itemParametro);
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarCategorias: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarCategorias  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO guardarCategoria(CategoriaDTO parametro) {
		lOGGER.info("Ingreso guardarCategoria  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO SEGMENTO------------:  " +  parametro.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_categorias(?,?,?,?,?,?) }");
			callableStatement.setInt(1, parametro.getCodigoSegmento() != null ?  parametro.getCodigoSegmento() :0);
			callableStatement.setString(2, parametro.getDescripcion() != null ?  parametro.getDescripcion() :"");
			callableStatement.setString(3, parametro.getUsuario().getNombre() != null ?  parametro.getUsuario().getNombre() :"");
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(4));
			respuesta.setCodigoError(callableStatement.getString(5));
			respuesta.setDescripcion(callableStatement.getString(6));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
		}catch (Exception e) {
			lOGGER.error("Error guardarCategoria: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarCategoria  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarCategoria(CategoriaDTO parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarCategoria(CategoriaDTO parametro) {
		// TODO Auto-generated method stub
		return null;
	}

}
