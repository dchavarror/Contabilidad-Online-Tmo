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

import co.com.contabilidad.online.tmo.dto.ProductoDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.facade.ProductoFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Producto/")
public class ProductoService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(ProductoService.class);

	@Autowired
	private ProductoFacade productoFacade;
	
	
	@RequestMapping(value = "obtenerProductos", method = RequestMethod.POST)
	public ResponseEntity<List<ProductoDTO>> obtenerProductos(
			@Valid @RequestBody ProductoDTO producto) {
		lOGGER.info("Ingreso obtenerProductos:  ");
		lOGGER.info("---------------CODIGO PRODUCTO------------:  " + producto.getCodigo());
		final List<ProductoDTO> lstProductos = new ArrayList<ProductoDTO>();
		try {

			productoFacade.getProducto().consultarProductos(producto, lstProductos);
			lOGGER.info("---------------CANTIDAD DE PRODUCTOS------------:  " + lstProductos.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerProductos: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerProductos  ");
		return new ResponseEntity<List<ProductoDTO>>(lstProductos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarProducto", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarProducto(
			@Valid @RequestBody ProductoDTO producto) {
		lOGGER.info("Ingreso guardarProducto:  ");
		lOGGER.info("---------------CODIGO PRODUCTO------------:  " + producto.getCodigo());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = productoFacade.getProducto().guardarProducto(producto);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarProducto: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarProducto  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
