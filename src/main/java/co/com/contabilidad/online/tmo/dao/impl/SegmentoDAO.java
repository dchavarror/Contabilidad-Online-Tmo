/**
 * 
 */
package co.com.contabilidad.online.tmo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.ISegmento;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.SegmentoDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

/**
 * @author dchavarro
 *
 */

@Component
public class SegmentoDAO implements ISegmento {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(SegmentoDAO.class);
    
    private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<SegmentoDTO> consultarSegmentos() {
		lOGGER.info("Ingreso consultarSegmentos  ");
		
		List<SegmentoDTO> lstSegmentos = null;
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_segmentos(?,?,?,?) }");
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(1));
			respuesta.setCodigoError(callableStatement.getString(2));
			respuesta.setDescripcion(callableStatement.getString(3));
			ResultSet results = (ResultSet) callableStatement.getObject(4);
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
				lstSegmentos = new ArrayList<SegmentoDTO>();
				while (results.next()) {
					SegmentoDTO itemParametro = new SegmentoDTO(results.getInt(1),results.getString(2));
					lstSegmentos.add(itemParametro);
				}
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarSegmentos: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarSegmentos  ");
		return lstSegmentos;
	}

	@Override
	public RespuestaDTO guardarSegmento(SegmentoDTO segmento) {
		lOGGER.info("Ingreso guardarSegmento  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------DESCRIPCION SEGMENTO------------:  " +  segmento.getDescripcion());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_segmento(?,?,?,?,?) }");
			callableStatement.setString(1, segmento.getDescripcion() != null ?  segmento.getDescripcion() :"");
			callableStatement.setString(2, segmento.getUsuario().getNombre() != null ?  segmento.getUsuario().getNombre() :"");
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(3));
			respuesta.setCodigoError(callableStatement.getString(4));
			respuesta.setDescripcion(callableStatement.getString(5));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
		}catch (Exception e) {
			lOGGER.error("Error guardarSegmento: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarSegmento  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarSegmento(SegmentoDTO segmento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarSegmento(SegmentoDTO segmento) {
		// TODO Auto-generated method stub
		return null;
	}

}
