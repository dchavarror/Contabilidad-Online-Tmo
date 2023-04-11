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

import co.com.contabilidad.online.tmo.dao.IMunicipio;
import co.com.contabilidad.online.tmo.dto.DepartamentoDTO;
import co.com.contabilidad.online.tmo.dto.MunicipioDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class MunicipioDAO implements IMunicipio{
	
	private static final Logger lOGGER = LoggerFactory.getLogger(MunicipioDAO.class);
    private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<MunicipioDTO> consultarMunicipios(DepartamentoDTO departamento) {
		lOGGER.info("Ingreso consultarMunicipios  ");
	    List<MunicipioDTO> lstMunicipios = null;
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			lOGGER.info("---------------CODIGO DEPARTAMENTO------------:  " + departamento.getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call configuracion.fn_consultar_municipios(?,?,?,?,?) }");
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
				lstMunicipios = new ArrayList<MunicipioDTO>();
				while (results.next()) {
					MunicipioDTO itemMunicipio = new MunicipioDTO(results.getInt(1),results.getString(2));
					lstMunicipios.add(itemMunicipio);
				}
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarDepartamentos: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarDepartamentos  ");
		return lstMunicipios;
	}

	@Override
	public RespuestaDTO guardarMunicipio(MunicipioDTO municipio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO actualizarMunicipio(MunicipioDTO municipio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarMunicipio(MunicipioDTO municipio) {
		// TODO Auto-generated method stub
		return null;
	}

}
