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

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.UnidadDTO;
import co.com.contabilidad.online.tmo.facade.UnidadFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Unidad/")
public class UnidadService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(CategoriaService.class);

	@Autowired
	private UnidadFacade unidadFacade;
	
	@RequestMapping(value = "obtenerUnidades", method = RequestMethod.GET)
	public ResponseEntity<List<UnidadDTO>> obtenerUnidades(
			@PathParam("codigoCat") Integer codigoCat) {
		lOGGER.info("Ingreso obtenerUnidades:  ");
		lOGGER.info("---------------CODIGO CATEGORIA------------:  " + codigoCat);
		final List<UnidadDTO> lstUnidades = new ArrayList<UnidadDTO>();
		try {

			unidadFacade.getUnidad().consultarUnidades(codigoCat, lstUnidades);
			lOGGER.info("---------------CANTIDAD DE UNIDADES------------:  " + lstUnidades.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerCategorias: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerUnidades  ");
		return new ResponseEntity<List<UnidadDTO>>(lstUnidades, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarUnidad", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarUnidad(
			@Valid @RequestBody UnidadDTO unidad) {
		lOGGER.info("Ingreso guardarUnidad:  ");
		lOGGER.info("---------------DESCRIPCION------------:  " + unidad.getDescripcion());
		lOGGER.info("---------------CODIGO CATEGORIA------------:  " + unidad.getCodigoCategoria());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta =unidadFacade.getUnidad().guardarUnidad(unidad);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarUnidad: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarUnidad  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
