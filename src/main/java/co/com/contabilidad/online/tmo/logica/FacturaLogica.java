package co.com.contabilidad.online.tmo.logica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.FacturaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;
import co.com.contabilidad.online.tmo.facade.DireccionFacade;
import co.com.contabilidad.online.tmo.facade.TelefonoFacade;
import co.com.contabilidad.online.tmo.utils.GenerarFactura;

@Component
public class FacturaLogica {
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	private DireccionFacade direccionFacade;
	
	@Autowired
	private TelefonoFacade telefonoFacade;
	
	
	
	public FacturaDTO generarFactura(final List<VentaDTO> lstVentas, final String numeroFactura, final String nombreCliente, final double paganCon) {
		FacturaDTO factura = new FacturaDTO();
		List<DireccionDTO> lstDirecciones= new ArrayList<DireccionDTO>(0);
		List<TelefonoDTO> lstTelefonos= new ArrayList<TelefonoDTO>(0);
		GenerarFactura generarFactura = new GenerarFactura();
		RespuestaDTO respuesta  = new RespuestaDTO();
		if(lstVentas.size()>0) {
			System.out.println("razon social " + lstVentas.get(0).getUsuario().getEmpresa().getRazonSocial());
			System.out.println("direccionFacade " + this.direccionFacade);
			System.out.println("this.direccionFacade.getDireccion() " + this.direccionFacade.getDireccion());
			lstDirecciones = this.direccionFacade.getDireccion().consultarDirecciones(lstVentas.get(0).getUsuario().getEmpresa().getRazonSocial(), "", respuesta);
			System.out.println("lista direcciones " + lstDirecciones.size());
			if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
				respuesta  = new RespuestaDTO();
				lstTelefonos = this.telefonoFacade.getTelefono().consultarTelefonos(lstVentas.get(0).getUsuario().getEmpresa().getRazonSocial(), "", respuesta);
				System.out.println("lista telefonos " + lstTelefonos.size());
				if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
				   factura = generarFactura.crearFactura(lstVentas, lstVentas.get(0).getUsuario().getEmpresa(), lstDirecciones, lstTelefonos, numeroFactura, nombreCliente, paganCon);
				   factura.setNumero(numeroFactura);
				}
			}
			
			
			
		}
		
		
		return factura;
	}

}
