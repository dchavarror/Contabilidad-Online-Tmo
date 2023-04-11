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

import co.com.contabilidad.online.tmo.dao.IDepartamento;
import co.com.contabilidad.online.tmo.dto.DepartamentoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class DepartamentoDAO implements IDepartamento{

    private static final Logger lOGGER = LoggerFactory.getLogger(DepartamentoDAO.class);
    private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@Override
	public List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO departamento) {
		
		lOGGER.info("Ingreso consultarDepartamentos  ");
	    List<DepartamentoDTO> lstDepartamentos = null;
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO DEPARTAMENTO------------:  " + departamento.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call configuracion.fn_consultar_departamentos(?,?,?,?,?) }");
			callableStatement.setInt(1, departamento.getCodigo() != null ? departamento.getCodigo() :0);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.REF_CURSOR);
			callableStatement.execute();
			respuesta.setTipoRespuesta(callableStatement.getString(2));
			respuesta.setCodigoError(callableStatement.getString(3));
			respuesta.setDescripcion(callableStatement.getString(4));
			ResultSet results = (ResultSet) callableStatement.getObject(5);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
				lstDepartamentos = new ArrayList<DepartamentoDTO>();
				while (results.next()) {
					DepartamentoDTO itemDepartamento = new DepartamentoDTO(results.getInt(1),results.getString(2));
					lstDepartamentos.add(itemDepartamento);
				}
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarDepartamentos: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarDepartamentos  ");
		return lstDepartamentos;
	}

	@Override
	public RespuestaDTO guardarDepartamento(DepartamentoDTO departamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO actualizarDepartamento(DepartamentoDTO departamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarDepartamento(DepartamentoDTO departamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
