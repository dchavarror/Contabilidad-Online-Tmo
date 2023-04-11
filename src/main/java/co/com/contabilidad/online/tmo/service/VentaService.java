package co.com.contabilidad.online.tmo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.contabilidad.online.tmo.dto.FacturaDTO;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.VentaDTO;
import co.com.contabilidad.online.tmo.facade.FacturaFacade;
import co.com.contabilidad.online.tmo.facade.VentaFacade;
import co.com.contabilidad.online.tmo.logica.FacturaLogica;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Venta/")
public class VentaService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(VentaService.class);
	private static final String TIPO_RESPUESTA_OK = "OK";

	@Autowired
	private VentaFacade ventaFacade;
	
	@Autowired
	private FacturaLogica facturaLogica;
	
	@Autowired
	private FacturaFacade facturaFacade;
	
	private RespuestaDTO respuesta = new RespuestaDTO();
	
	@RequestMapping(value = "obtenerVentas", method = RequestMethod.POST)
	public ResponseEntity<List<VentaDTO>> obtenerVentas(
			@Valid @RequestBody ProductoDTO producto) {
		lOGGER.info("Ingreso obtenerVentas  ");
		final List<VentaDTO> lstVentas = new ArrayList<VentaDTO>();
		try {

			ventaFacade.getVenta().consultarVentas(producto, lstVentas);
			lOGGER.info("---------------CANTIDAD DE VENTAS------------:  " + lstVentas.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerVentas: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerVentas  ");
		return new ResponseEntity<List<VentaDTO>>(lstVentas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarVenta", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarVenta(
			@Valid @RequestBody List<VentaDTO> lstVentas,
			@PathParam("indAplicaFactura") Boolean indAplicaFactura,
			@PathParam("nombreCliente") String nombreCliente,
			@PathParam("paganCon") double paganCon) {
		lOGGER.info("Ingreso guardarVenta:  ");
		FacturaDTO factura = new FacturaDTO();
		try {
			lOGGER.info("---------------indAplicaFactura ------------:  " + indAplicaFactura);
			if(indAplicaFactura) {
				lOGGER.info("---------------GENERANDO FACTURA ------------:  ");
				factura = new FacturaDTO();
				factura = this.facturaFacade.getFactura().generarNumeroFactura(lstVentas.get(0).getUsuario().getEmpresa().getRazonSocial(), lstVentas.get(0).getUsuario(),this.respuesta);
				factura = this.facturaLogica.generarFactura(lstVentas, factura.getNumero(), nombreCliente, paganCon);
				lOGGER.info("---------------factura ------------:  " + factura.getArchivo());
				lOGGER.info("---------------getTipoRespuesta ------------:  " + this.respuesta.getTipoRespuesta().getBytes());
			}
			if((this.respuesta.getTipoRespuesta() != null && this.respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) || !indAplicaFactura) {
				this.respuesta = ventaFacade.getVenta().guardarVentas(lstVentas, factura.getNumero() != null ? factura.getNumero():"");
			}
			this.respuesta.setDocumento(factura.getArchivo());
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarVenta: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarVenta  ");
		lOGGER.info("---------------factura final ------------:  " + respuesta.getDocumento());
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
