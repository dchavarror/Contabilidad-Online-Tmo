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

import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.facade.PersonaFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Persona/")
public class PersonaService {
	private static final Logger lOGGER = LoggerFactory.getLogger(PersonaService.class);

	@Autowired
	private PersonaFacade personaFacade;
	
	@RequestMapping(value = "guardarPersona", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarPersona(
			@Valid @RequestBody PersonaDTO persona) {
		lOGGER.info("Ingreso guardarPersona:  ");
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			respuesta = personaFacade.getPersona().guardarPersona(persona, persona.getUsuario() ,persona.getUsuario().getEmpresa());
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarPersona: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarPersona  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
