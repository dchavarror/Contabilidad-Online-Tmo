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

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.dto.SegmentoDTO;
import co.com.contabilidad.online.tmo.facade.SegmentoFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Segmento/")
public class SegmentoService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(SegmentoService.class);

	@Autowired
	private SegmentoFacade segmentoFacade;
	
	@RequestMapping(value = "obtenerSegmentos", method = RequestMethod.GET)
	public ResponseEntity<List<SegmentoDTO>> obtenerSegmentos() {
		lOGGER.info("Ingreso obtenerCategorias:  ");
		List<SegmentoDTO> lstSegmentos = new ArrayList<SegmentoDTO>();
		try {

			lstSegmentos = segmentoFacade.getSegmento().consultarSegmentos();
			lOGGER.info("---------------CANTIDAD DE SEGMENTOS------------:  " + lstSegmentos.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerCategorias: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerCategorias  ");
		return new ResponseEntity<List<SegmentoDTO>>(lstSegmentos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarSegmento", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarSegmento(
			@Valid @RequestBody SegmentoDTO segmento) {
		lOGGER.info("Ingreso guardarSegmentos:  ");
		lOGGER.info("---------------DESCRIPCION------------:  " + segmento.getDescripcion());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = segmentoFacade.getSegmento().guardarSegmento(segmento);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarSegmentos: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarSegmentos  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
