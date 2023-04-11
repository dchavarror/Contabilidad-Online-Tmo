package co.com.contabilidad.online.tmo.service;

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
import co.com.contabilidad.online.tmo.logica.EmpresaLogica;
import co.com.contabilidad.online.tmo.service.request.GestionarEmpresaRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Empresa/")
public class EmpresaService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(EmpresaService.class);

	@Autowired
	private EmpresaLogica empresaLogica;
	
	@RequestMapping(value = "gestionarEmpresa", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> gestionarEmpresa(
			@Valid @RequestBody GestionarEmpresaRequest gestionarEmpresaRequest) {
		lOGGER.info("Ingreso gestionarEmpresa:  ");
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = empresaLogica.gestionarEmpresa(gestionarEmpresaRequest);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error gestionarEmpresa: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida gestionarEmpresa  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
