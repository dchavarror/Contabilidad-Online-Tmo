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

import co.com.contabilidad.online.tmo.dto.TipoGenericoDTO;
import co.com.contabilidad.online.tmo.facade.GenericoFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Generico/")
public class GenericoService {
	private static final Logger lOGGER = LoggerFactory.getLogger(GenericoService.class);

	@Autowired
	private GenericoFacade genericoFacade;

	@RequestMapping(value = "obtenerParametrosGenerales", method = RequestMethod.POST)
	public ResponseEntity<List<TipoGenericoDTO>> obtenerParametrosGenerales(
			@Valid @RequestBody TipoGenericoDTO parametro) {
		lOGGER.info("Ingreso obtenerParametrosGenerales:  ");
		lOGGER.info("---------------CODIGO------------:  " + parametro.getCodigo());
		lOGGER.info("---------------SUBCODIGO------------:  " + parametro.getSubCodigo());
		final List<TipoGenericoDTO> lstParametros = new ArrayList<TipoGenericoDTO>();
		try {

			genericoFacade.getGenerico().consultarParametrosGenerales(parametro, lstParametros);
			lOGGER.info("---------------CANTIDAD DE PARAMETROS------------:  " + lstParametros.size());
		} catch (Exception e) {
			lOGGER.error("Error obtenerParametrosGenerales: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida obtenerParametrosGenerales  ");
		return new ResponseEntity<List<TipoGenericoDTO>>(lstParametros, HttpStatus.OK);
	}

}
