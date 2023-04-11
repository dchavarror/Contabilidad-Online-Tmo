package co.com.contabilidad.online.tmo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IDireccionElectronica;
import co.com.contabilidad.online.tmo.dto.DireccionElectronicaDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class DireccionElectronicaEleDAO implements IDireccionElectronica {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(DireccionElectronicaEleDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<DireccionElectronicaDTO> consultarDireccionesEle(DireccionElectronicaDTO direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO guardarDireccionEle(DireccionElectronicaDTO direccion, UsuarioDTO usuario, EmpresaDTO empresa, PersonaDTO persona) {
		lOGGER.info("Ingreso guardarDireccionEle  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------DIRECCION ELECTRONICA------------:  " + direccion.getCorreoElectronico());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_dir_electronica(?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, direccion.getCorreoElectronico() != null ? direccion.getCorreoElectronico() :"");
			callableStatement.setString(2, empresa.getRazonSocial() != null ?  empresa.getRazonSocial() :"");
			callableStatement.setString(3,  persona.getTipo().getCodigo() != null ? persona.getTipo().getCodigo() :"");
			callableStatement.setString(4, persona.getDocumentoIdentificacion().getNumero() != null ?  persona.getDocumentoIdentificacion().getNumero() :"");
			callableStatement.setString(5, direccion.getTipo().getCodigo() != null ?  direccion.getTipo().getCodigo() :"");
			callableStatement.setString(6, usuario.getNombre() != null ?   usuario.getNombre() :"");
			
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.execute();
			
			respuesta.setTipoRespuesta(callableStatement.getString(7));
			respuesta.setCodigoError(callableStatement.getString(8));
			respuesta.setDescripcion(callableStatement.getString(9));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
		}catch (Exception e) {
			lOGGER.error("Error guardarDireccionEle: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarDireccionEle  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarDireccionEle(DireccionElectronicaDTO direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarDireccionEle(DireccionElectronicaDTO direccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
