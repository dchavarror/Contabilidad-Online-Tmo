package co.com.contabilidad.online.tmo.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.contabilidad.online.tmo.dto.RespuestaDTO;
import co.com.contabilidad.online.tmo.facade.DireccionElectronicaEleFacade;
import co.com.contabilidad.online.tmo.facade.DireccionFacade;
import co.com.contabilidad.online.tmo.facade.EmpresaFacade;
import co.com.contabilidad.online.tmo.facade.PersonaFacade;
import co.com.contabilidad.online.tmo.facade.TelefonoFacade;
import co.com.contabilidad.online.tmo.facade.UsuarioFacade;
import co.com.contabilidad.online.tmo.service.request.GestionarEmpresaRequest;

@Component
public class EmpresaLogica {
	
	private static final String TIPO_RESPUESTA_OK = "OK";
	
	@Autowired
	private PersonaFacade persona;
	
	@Autowired
	private DireccionFacade direccion;
	
	@Autowired
	private DireccionElectronicaEleFacade direccionElectronicaEle;
	
	@Autowired
	private TelefonoFacade telefono;
	
	@Autowired
	private UsuarioFacade usuario;
	
	@Autowired
	private EmpresaFacade empresa;
	
	public RespuestaDTO gestionarEmpresa(GestionarEmpresaRequest gestionarEmpresaRequest) {
		RespuestaDTO respuesta = new RespuestaDTO();
		respuesta = this.persona.getPersona().guardarPersona(gestionarEmpresaRequest.getPersona(),  gestionarEmpresaRequest.getUsuario(), gestionarEmpresaRequest.getEmpresa());
		
		if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
			 respuesta = new RespuestaDTO();
			 respuesta = this.empresa.getEmpresa().guardarEmpresa(gestionarEmpresaRequest.getEmpresa(), gestionarEmpresaRequest.getPersona(), gestionarEmpresaRequest.getUsuario());
			 
			 if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
				 respuesta = new RespuestaDTO();
				 respuesta = this.usuario.getUsuario().guardarUsuario(gestionarEmpresaRequest.getUsuario(), gestionarEmpresaRequest.getPersona());
				 
				 if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
					 respuesta = new RespuestaDTO();
					 respuesta = this.direccion.getDireccion().guardarDireccion(gestionarEmpresaRequest.getDireccion(), gestionarEmpresaRequest.getUsuario(), gestionarEmpresaRequest.getEmpresa(), gestionarEmpresaRequest.getPersona());
					 
					 if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
						 respuesta = new RespuestaDTO();
						 respuesta = this.direccionElectronicaEle.getDireccionEle().guardarDireccionEle(gestionarEmpresaRequest.getDireccionElectronica(), gestionarEmpresaRequest.getUsuario(), gestionarEmpresaRequest.getEmpresa(), gestionarEmpresaRequest.getPersona());
						 
						 if(respuesta.getTipoRespuesta().equals(TIPO_RESPUESTA_OK)) {
							 respuesta = new RespuestaDTO();
							 respuesta = this.telefono.getTelefono().guardarTelefono(gestionarEmpresaRequest.getLstTelefonos(), gestionarEmpresaRequest.getUsuario(), gestionarEmpresaRequest.getEmpresa(), gestionarEmpresaRequest.getPersona());
							 
						 }
					 }
				 }
			 }
		}
		return respuesta;
	}

}
