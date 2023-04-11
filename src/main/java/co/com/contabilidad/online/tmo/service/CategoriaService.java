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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.contabilidad.online.tmo.dto.CategoriaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.facade.CategoriaFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Categoria/")

@Service
public class CategoriaService {

	private static final Logger lOGGER = LoggerFactory.getLogger(CategoriaService.class);

	@Autowired
	private CategoriaFacade categoriaFacade;
	
	@RequestMapping(value = "obtenerCategorias", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> obtenerCategorias(
			@PathParam("codigoSeg") Integer codigoSeg) {
		lOGGER.info("Ingreso obtenerCategorias:  ");
		lOGGER.info("---------------CODIGO SEGMENTO------------:  " + codigoSeg);
		final List<CategoriaDTO> lstCategorias = new ArrayList<CategoriaDTO>();
		try {

			categoriaFacade.getCategoria().consultarCategorias(codigoSeg, lstCategorias);
			lOGGER.info("---------------CANTIDAD DE CATEGORIAS------------:  " + lstCategorias.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerCategorias: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerCategorias  ");
		return new ResponseEntity<List<CategoriaDTO>>(lstCategorias, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarCategoria", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarCategoria(
			@Valid @RequestBody CategoriaDTO categoria) {
		lOGGER.info("Ingreso guardarCategoria:  ");
		lOGGER.info("---------------DESCRIPCION------------:  " + categoria.getDescripcion());
		lOGGER.info("---------------CODIGO SEGMENTO------------:  " + categoria.getCodigoSegmento());
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = categoriaFacade.getCategoria().guardarCategoria(categoria);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarCategoria: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarCategoria  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}
}
