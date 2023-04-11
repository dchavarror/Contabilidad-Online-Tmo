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

import co.com.contabilidad.online.tmo.dao.IEmpresa;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class EmpresaDAO implements IEmpresa{
	
    private static final Logger lOGGER = LoggerFactory.getLogger(EmpresaDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<EmpresaDTO> consultarEmpresas(EmpresaDTO empresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO guardarEmpresa(EmpresaDTO empresa, PersonaDTO persona, UsuarioDTO usuario) {
		lOGGER.info("Ingreso guardarEmpresa  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		RespuestaDTO respuesta = null;
		try {
			lOGGER.info("---------------RAZON SOCIAL EMPRESA------------:  " + empresa.getRazonSocial());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_empresa(?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, empresa.getNit() != null ? empresa.getNit() :"");
			callableStatement.setString(2, empresa.getRazonSocial() != null ? empresa.getRazonSocial() :"");
			callableStatement.setString(3, persona.getDocumentoIdentificacion().getNumero() != null ? persona.getDocumentoIdentificacion().getNumero() :"");
			callableStatement.setString(4, persona.getTipo().getCodigo() != null ?  persona.getTipo().getCodigo() :"");
			callableStatement.setString(5, usuario.getNombre() != null ?  usuario.getNombre() :"");
			callableStatement.setInt(6, usuario.getCodigoSegmento()!= null ?  usuario.getCodigoSegmento() :0);
			
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.execute();
			respuesta = new RespuestaDTO();
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
			lOGGER.error("Error guardarEmpresa: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarEmpresa  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarEmpresa(EmpresaDTO empresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarEmpresa(EmpresaDTO empresa) {
		// TODO Auto-generated method stub
		return null;
	}

}
