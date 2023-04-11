package co.com.contabilidad.online.tmo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import co.com.contabilidad.online.tmo.dto.DetalleProductoDTO;
import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.facade.InventarioFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Inventario/")
public class InventarioService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(InventarioService.class);

	@Autowired
	private InventarioFacade inventarioFacade;
	
	@RequestMapping(value = "obtenerInventario", method = RequestMethod.POST)
	public ResponseEntity<List<DetalleProductoDTO>> obtenerInventario(
			@Valid @RequestBody ProductoDTO producto) {
		lOGGER.info("Ingreso obtenerInventario  ");
		final List<DetalleProductoDTO> lstInventario = new ArrayList<DetalleProductoDTO>();
		try {

			inventarioFacade.getInventario().consultarInventario(producto, lstInventario);
			lOGGER.info("---------------CANTIDAD DE INVENTARIO------------:  " + lstInventario.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerInventario: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerInventario  ");
		return new ResponseEntity<List<DetalleProductoDTO>>(lstInventario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarInventario", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarInventario(
			@Valid @RequestBody DetalleProductoDTO detalleProducto) {
		lOGGER.info("Ingreso guardarInventario:  ");
		lOGGER.info("---------------CODIGO PRODUCTO------------:  " + detalleProducto.getProducto().getCodigo());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = inventarioFacade.getInventario().guardarInventario(detalleProducto);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarInventario: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarInventario  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "actualizarInventario", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> actualizarInventario(
			@Valid @RequestBody DetalleProductoDTO detalleProducto) {
		lOGGER.info("Ingreso actualizarInventario:  ");
		lOGGER.info("---------------CODIGO PRODUCTO------------:  " + detalleProducto.getProducto().getCodigo());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = inventarioFacade.getInventario().actualizarInventario(detalleProducto);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error actualizarInventario: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida actualizarInventario  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}
}
