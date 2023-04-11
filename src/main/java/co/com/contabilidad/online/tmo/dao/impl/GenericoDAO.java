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

import co.com.contabilidad.online.tmo.dao.IGenerico;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TipoGenericoDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class GenericoDAO implements IGenerico{
    
	private static final Logger lOGGER = LoggerFactory.getLogger(GenericoDAO.class);
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@Override
	public RespuestaDTO  consultarParametrosGenerales(final TipoGenericoDTO parametro,final List<TipoGenericoDTO> lstParametrosGenerales) {
		    lOGGER.info("Ingreso consultarParametrosGenerales  ");
			Connection conn = null;
			CallableStatement callableStatement = null;
			
			try {
				lOGGER.info("---------------CODIGO------------:  " + parametro.getCodigo());
				lOGGER.info("---------------SUBCODIGO------------:  " + parametro.getSubCodigo());
				conn = jdbcTemplate.getDataSource().getConnection();
				conn.setAutoCommit(false);
				callableStatement = conn.prepareCall("{ call configuracion.fn_consultar_par_generales(?,?,?,?,?,?) }");
				callableStatement.setString(1, parametro.getCodigo() != null ? parametro.getCodigo() :"");
				callableStatement.setString(2, parametro.getSubCodigo() != null ? parametro.getSubCodigo() :"");
				callableStatement.registerOutParameter(3, Types.VARCHAR);
				callableStatement.registerOutParameter(4, Types.VARCHAR);
				callableStatement.registerOutParameter(5, Types.VARCHAR);
				callableStatement.registerOutParameter(6, Types.REF_CURSOR);
				callableStatement.execute();
				respuesta.setTipoRespuesta(callableStatement.getString(3));
				respuesta.setCodigoError(callableStatement.getString(4));
				respuesta.setDescripcion(callableStatement.getString(5));
				ResultSet results = (ResultSet) callableStatement.getObject(6);
				
				while (results.next()) {
					TipoGenericoDTO itemParametro = new TipoGenericoDTO(results.getString(1),results.getString(2),results.getString(3));
				    lstParametrosGenerales.add(itemParametro);
				}
			}catch (Exception e) {
				lOGGER.error("Error consultarParametrosGenerales: " + e.getMessage());
				e.printStackTrace();
			}finally {
				Conexion.cerrarConexion(conn);
			}
			lOGGER.info("Salida consultarParametrosGenerales  ");
			return respuesta;
	}

	@Override
	public RespuestaDTO guardarParametroGenerales(TipoGenericoDTO parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO actualizarParametrosGenerales(TipoGenericoDTO parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarParametroGenerales(TipoGenericoDTO parametro) {
		// TODO Auto-generated method stub
		return null;
	}
}
