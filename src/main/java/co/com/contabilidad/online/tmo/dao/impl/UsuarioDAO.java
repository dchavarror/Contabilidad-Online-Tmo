package co.com.contabilidad.online.tmo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dao.IUsuario;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.service.response.ValidarUsuarioResponse;
import co.com.contabilidad.online.tmo.utils.Conexion;
import co.com.contabilidad.online.tmo.utils.EncriptarDesancriptar;

@Component
public class UsuarioDAO implements IUsuario {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(UsuarioDAO.class);
    
    private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EncriptarDesancriptar seguridad;
	
	private ValidarUsuarioResponse usuarioResponse ;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	

	@Override
	public ValidarUsuarioResponse validarUsuario(UsuarioDTO usuario) {
		lOGGER.info("Ingreso validarUsuario  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		
		try {
			this.usuarioResponse = new ValidarUsuarioResponse();
			//this.encriptarDesc = new EncriptarDesancriptar();
			lOGGER.info("---------------USUARIO------------:  " + usuario.getNombre());
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call seguridad.fn_validar_usuario(?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, usuario.getNombre() != null ? usuario.getNombre() :"");
			callableStatement.setString(2, usuario.getPassword() != null ? this.seguridad.encriptar(usuario.getPassword()) :"");
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.execute();
			this.usuarioResponse.getRespuesta().setTipoRespuesta(callableStatement.getString(8));
			this.usuarioResponse.getRespuesta().setCodigoError(callableStatement.getString(9));
			this.usuarioResponse.getRespuesta().setDescripcion(callableStatement.getString(10));
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + this.usuarioResponse.getRespuesta().getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION RESPUESTA------------:  " + this.usuarioResponse.getRespuesta().getDescripcion());
		    if(this.usuarioResponse.getRespuesta().getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
		    	this.usuarioResponse.getUsuario().setCodigoSegmento(callableStatement.getInt(3));
		    	this.usuarioResponse.getUsuario().getRol().setCodigo(callableStatement.getInt(4));
		    	this.usuarioResponse.getUsuario().getRol().setDescripcion(callableStatement.getString(5));
		    	this.usuarioResponse.getUsuario().getEmpresa().setRazonSocial(callableStatement.getString(6));
		    	this.usuarioResponse.getUsuario().getEmpresa().setNit(callableStatement.getString(7));
		    	
		    }
		}catch (Exception e) {
			lOGGER.error("Error validarUsuario: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida validarUsuario  ");
		return this.usuarioResponse;
	}



	@Override
	public RespuestaDTO guardarUsuario(final UsuarioDTO usuario, final PersonaDTO persona) {
		lOGGER.info("Ingreso guardarUsuario  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------NOMBRE USUARIO------------:  " + usuario.getNombre());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call seguridad.fn_guardar_usuario(?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, usuario.getNombre() != null ? usuario.getNombre() :"");
			callableStatement.setString(2, usuario.getPassword() != null ?  this.seguridad.encriptar(usuario.getPassword()) :"");
			callableStatement.setString(3, usuario.getEmpresa().getRazonSocial() != null ? usuario.getEmpresa().getRazonSocial() :"");
			callableStatement.setString(4, persona.getDocumentoIdentificacion().getNumero() != null ?  persona.getDocumentoIdentificacion().getNumero() :"");
			callableStatement.setInt(5, usuario.getRol().getCodigo() != null ?  usuario.getRol().getCodigo() :0);
			callableStatement.setString(6, usuario.getNombre()  != null ?   usuario.getNombre()  :"");
			
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
			lOGGER.error("Error guardarUsuario: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarUsuario  ");
		return respuesta;
	}

}
