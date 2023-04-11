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

import co.com.contabilidad.online.tmo.dao.IDireccion;
import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class DireccionDAO implements IDireccion {
	
    private static final Logger lOGGER = LoggerFactory.getLogger(DireccionDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	
	private RespuestaDTO respuesta = new RespuestaDTO();

	@Override
	public List<DireccionDTO> consultarDirecciones(final String razonSocial, final String numeroIdentificacion,final RespuestaDTO pRespuesta) {
		lOGGER.info("Ingreso consultarDirecciones  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		List<DireccionDTO> lstDirecciones = null;
		
		try {
			lOGGER.info("---------------RAZON SOCIAL------------:  " + razonSocial);
			lOGGER.info("---------------NUMERO IDENTIFICACION------------:  " + numeroIdentificacion);
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			callableStatement = conn.prepareCall("{ call public.fn_consultar_direcciones(?,?,?,?,?,?) }");
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
			lstDirecciones = new ArrayList<DireccionDTO>(0);
			while (results.next()) {
				DireccionDTO itemDireccion = new DireccionDTO();
				itemDireccion.setDescripcion(results.getString(1));
				itemDireccion.getDepartamento().setCodigo(String.valueOf(results.getInt(2)));
				itemDireccion.getDepartamento().setDescripcion(results.getString(3));
				itemDireccion.getMunicipio().setCodigo(String.valueOf(results.getInt(4)));
				itemDireccion.getMunicipio().setDescripcion(results.getString(5));
				lstDirecciones.add(itemDireccion);
			}
		}catch (Exception e) {
			lOGGER.error("Error consultarDirecciones: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida consultarDirecciones  " + lstDirecciones.size());
		return lstDirecciones;
	}

	@Override
	public RespuestaDTO guardarDireccion(DireccionDTO direccion, UsuarioDTO usuario, EmpresaDTO empresa,  PersonaDTO persona) {
		lOGGER.info("Ingreso guardarDireccion  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			lOGGER.info("---------------DIRECCION DESCRIPCION------------:  " + direccion.getDescripcion());
			lOGGER.info("---------------DEPARTAMENTO CODIGO------------:  " + direccion.getDepartamento().getCodigo());
			lOGGER.info("---------------MUNICIPIO CODIGO------------:  " + direccion.getMunicipio().getCodigo());
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call public.fn_guardar_direccion(?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setString(1, direccion.getDescripcion() != null ? direccion.getDescripcion() :"");
			callableStatement.setInt(2, direccion.getDepartamento().getCodigo()!= null ? Integer.parseInt(direccion.getDepartamento().getCodigo()) :null);
			callableStatement.setInt(3, direccion.getMunicipio().getCodigo() != null ? Integer.parseInt(direccion.getMunicipio().getCodigo()) :null);
			callableStatement.setString(4, empresa.getRazonSocial() != null ?  empresa.getRazonSocial() :"");
			callableStatement.setString(5, persona.getTipo().getCodigo() != null ?  persona.getTipo().getCodigo() :"");
			callableStatement.setString(6, persona.getDocumentoIdentificacion().getNumero() != null ?  persona.getDocumentoIdentificacion().getNumero() :"");
			callableStatement.setString(7, direccion.getTipo().getCodigo() != null ?   direccion.getTipo().getCodigo() :"");
			callableStatement.setString(8, usuario.getNombre() != null ?   usuario.getNombre() :"");
			
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.execute();
			
			respuesta.setTipoRespuesta(callableStatement.getString(9));
			respuesta.setCodigoError(callableStatement.getString(10));
			respuesta.setDescripcion(callableStatement.getString(11));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + respuesta.getDescripcion());
			
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
		}catch (Exception e) {
			lOGGER.error("Error guardarDireccion: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida guardarDireccion  ");
		return respuesta;
	}

	@Override
	public RespuestaDTO actualizarDireccion(DireccionDTO direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarDireccion(DireccionDTO direccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
