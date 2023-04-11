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

import co.com.contabilidad.online.tmo.dao.ITelefono;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class TelefonoDAO implements ITelefono {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(TelefonoDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	private static final String TIPO_RESPUESTA_ER = "ER";
	private static final String DESCRIPCION_NO_TIENE_TELEFONOS = "POR FAVOR DE ENVIAR LOS TELEFONOS A GUARDAR";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<TelefonoDTO> consultarTelefonos(final String razonSocial, final String numeroIdentificacion, final RespuestaDTO pRespuesta) {
		lOGGER.info("Ingreso consultarTelefonos  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		List<TelefonoDTO> lstTelefonos = null;
		
		try {
			lOGGER.info("---------------RAZON SOCIAL------------:  " + razonSocial);
			lOGGER.info("---------------NUMERO IDENTIFICACION------------:  " + numeroIdentificacion);
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_telefonos(?,?,?,?,?,?) }");
			callableStatement.setString(1, razonSocial);
			callableStatement.setString(2, numeroIdentificacion);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.REF_CURSOR);
			callableStatement.execute();
			pRespuesta.setTipoRespuesta(callableStatement.getString(3));
			pRespuesta.setCodigoError(callableStatement.getString(4));
			pRespuesta.setDescripcion(callableStatement.getString(5));
			ResultSet results = (ResultSet) callableStatement.getObject(6);
			lstTelefonos = new ArrayList<TelefonoDTO>(0);
			while (results.next()) {
				TelefonoDTO itemTelefono = new TelefonoDTO();
				itemTelefono.setNumero(results.getString(1));
				itemTelefono.getTipo().setCodigo(results.getString(2));
				lstTelefonos.add(itemTelefono);
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarTelefonos: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarTelefonos  ");
		return lstTelefonos;
	}

	@Override
	public RespuestaDTO guardarTelefono(List<TelefonoDTO> lstTelefonos, final UsuarioDTO usuario, final EmpresaDTO empresa, final PersonaDTO persona) {
		lOGGER.info("Ingreso guardarTelefono  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			if(lstTelefonos != null && lstTelefonos.size()>0) {
				for(int i =0; i< lstTelefonos.size();i++) {
					lOGGER.info("---------------NUMERO TELEFONO------------:  " + lstTelefonos.get(i).getNumero());
					lOGGER.info("---------------TIPO TELEFONO------------:  " +  lstTelefonos.get(i).getTipo().getCodigo());
					conn = jdbcTemplate.getDataSource().getConnection();
				    conn.setAutoCommit(true);
					callableStatement = conn.prepareCall("{ call public.fn_guardar_telefono(?,?,?,?,?,?,?,?,?) }");
					callableStatement.setString(1, lstTelefonos.get(i).getNumero() != null ? lstTelefonos.get(i).getNumero() :"");
					callableStatement.setString(2, empresa.getRazonSocial() != null ? empresa.getRazonSocial() :"");
					callableStatement.setString(3, persona.getTipo().getCodigo() != null ? persona.getTipo().getCodigo() :"");
					callableStatement.setString(4, persona.getDocumentoIdentificacion().getNumero() != null ? persona.getDocumentoIdentificacion().getNumero():"");
					callableStatement.setString(5, lstTelefonos.get(i).getTipo().getCodigo() != null ?  lstTelefonos.get(i).getTipo().getCodigo() :"");
					callableStatement.setString(6, usuario.getNombre() != null?  usuario.getNombre() :"");
					
					callableStatement.registerOutParameter(7, Types.VARCHAR);
					callableStatement.registerOutParameter(8, Types.VARCHAR);
					callableStatement.registerOutParameter(9, Types.VARCHAR);
					callableStatement.execute();
					
					respuesta.setTipoRespuesta(callableStatement.getString(7));
					respuesta.setCodigoError(callableStatement.getString(8));
					respuesta.setDescripcion(callableStatement.getString(9));
					lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
					lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
				}
			}else {
				respuesta.setTipoRespuesta(TIPO_RESPUESTA_ER);
				respuesta.setDescripcion(DESCRIPCION_NO_TIENE_TELEFONOS);
			}
			
			
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
		}catch (Exception e) {
			lOGGER.error("Error guardarTelefono: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarTelefono  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarTelefono(TelefonoDTO telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarTelefono(TelefonoDTO telefono) {
		// TODO Auto-generated method stub
		return null;
	}

}
