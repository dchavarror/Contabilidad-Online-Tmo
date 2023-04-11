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

import co.com.contabilidad.online.tmo.dao.IPersona;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class PersonaDAO implements IPersona {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(PersonaDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<PersonaDTO> consultarPersonas(PersonaDTO persona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO guardarPersona(PersonaDTO persona, final UsuarioDTO usuario, final EmpresaDTO empresa) {
		lOGGER.info("Ingreso guardarPersona  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------NUMERO IDENTIFICACION------------:  " + persona.getDocumentoIdentificacion().getNumero());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_persona(?,?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, persona.getPrimerNombre() != null ? persona.getPrimerNombre() :"");
			callableStatement.setString(2, persona.getSegundoNombre() != null ? persona.getSegundoNombre() :"");
			callableStatement.setString(3, persona.getPrimerApellido() != null ? persona.getPrimerApellido() :"");
			callableStatement.setString(4, persona.getSegundoApellido() != null ?  persona.getSegundoApellido() :"");
			callableStatement.setString(5, persona.getDocumentoIdentificacion().getNumero() != null ?  persona.getDocumentoIdentificacion().getNumero() :"");
			callableStatement.setString(6, persona.getTipo().getCodigo() != null?  persona.getTipo().getCodigo() :"");
			callableStatement.setString(7, empresa.getRazonSocial() != null ?   empresa.getRazonSocial() :"");
			callableStatement.setString(8, persona.getFecNacimiento() != null ?   persona.getFecNacimiento() :"");
			callableStatement.setString(9, usuario.getNombre()  != null ?   usuario.getNombre() :"");
			
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.execute();
			
			respuesta.setTipoRespuesta(callableStatement.getString(10));
			respuesta.setCodigoError(callableStatement.getString(11));
			respuesta.setDescripcion(callableStatement.getString(12));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
		}catch (Exception e) {
			lOGGER.error("Error guardarPersona: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarPersona  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarPersona(PersonaDTO persona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarPersona(PersonaDTO persona) {
		// TODO Auto-generated method stub
		return null;
	}

}
