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
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;
import co.com.contabilidad.online.tmo.facade.UsuarioFacade;
import co.com.contabilidad.online.tmo.service.response.ValidarUsuarioResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Usuario/")
public class UsuarioService {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFacade usuarioFacade;
	
	@RequestMapping(value = "validarUsuario", method = RequestMethod.POST)
	public ResponseEntity<ValidarUsuarioResponse> validarUsuario(
			@Valid @RequestBody UsuarioDTO usuario) {
		lOGGER.info("Ingreso validarUsuario  ");
		ValidarUsuarioResponse usuarioResponse = new ValidarUsuarioResponse();
		try {

			usuarioResponse = usuarioFacade.getUsuario().validarUsuario(usuario);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + usuarioResponse.getRespuesta().getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error validarUsuario: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida validarUsuario  ");
		return new ResponseEntity<ValidarUsuarioResponse>(usuarioResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "guardarUsuario", method = RequestMethod.POST)
	public ResponseEntity<RespuestaDTO> guardarUsuario(
			@Valid @RequestBody UsuarioDTO usuario) {
		lOGGER.info("Ingreso guardarUsuario  ");
		RespuestaDTO respuesta = new RespuestaDTO();
		try {

			//respuesta = usuarioFacade.getUsuario().guardarUsuario(usuario);
			lOGGER.info("---------------TIPO RESPUESTA------------:  " + respuesta.getTipoRespuesta());
		} catch (Exception e) {
			lOGGER.error("Error guardarUsuario: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida guardarUsuario  ");
		return new ResponseEntity<RespuestaDTO>(respuesta, HttpStatus.OK);
	}

}
