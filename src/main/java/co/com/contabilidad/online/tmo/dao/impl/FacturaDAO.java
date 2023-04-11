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

import co.com.contabilidad.online.tmo.dao.IFactura;
import co.com.contabilidad.online.tmo.dto.FacturaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.utils.Conexion;

@Component
public class FacturaDAO implements IFactura {
	
   private static final Logger lOGGER = LoggerFactory.getLogger(FacturaDAO.class);
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	@Qualifier("jdbcContabilidadOnline")
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public RespuestaDTO consultarFacturas(String numero, List<FacturaDTO> lstCategorias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO guardarCategoria(FacturaDTO factura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO actualizarCategoria(FacturaDTO factura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaDTO eliminarCategoria(FacturaDTO factura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDTO generarNumeroFactura(String razonSocial, final UsuarioDTO usuario,final RespuestaDTO pRespuesta) {
		lOGGER.info("Ingreso generarNumeroFactura  ");
		Connection conn = null;
		CallableStatement callableStatement = null;
		FacturaDTO factura  = new FacturaDTO();
		try {
			lOGGER.info("---------------RAZON SOCIAL------------:  " + razonSocial);
			conn = jdbcTemplate.getDataSource().getConnection();
		    conn.setAutoCommit(true);
			callableStatement = conn.prepareCall("{ call configuracion.fn_generar_numero_factura(?,?,?,?,?,?) }");
			callableStatement.setString(1, razonSocial != null ? razonSocial :"");
			callableStatement.setString(2, usuario.getNombre() != null ?   usuario.getNombre() :"");
			
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.execute();
			
			factura.setNumero(callableStatement.getString(3));
			pRespuesta.setTipoRespuesta(callableStatement.getString(4));
			pRespuesta.setCodigoError(callableStatement.getString(5));
			pRespuesta.setDescripcion(callableStatement.getString(6));
			
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + pRespuesta.getTipoRespuesta());
			lOGGER.info("---------------DESCRIPCION------------:  " + pRespuesta.getDescripcion());
			
			if(pRespuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)){
			    //conn.commit();
			}else {
				//conn.rollback();
			}
		}catch (Exception e) {
			lOGGER.error("Error generarNumeroFactura: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexion.cerrarConexion(conn);
		}
		lOGGER.info("Salida generarNumeroFactura  ");
		return factura;
	}

}
