package co.com.contabilidad.online.tmo.service.request;

import java.util.List;

import co.com.contabilidad.online.tmo.dto.DireccionDTO;
import co.com.contabilidad.online.tmo.dto.DireccionElectronicaDTO;
import co.com.contabilidad.online.tmo.dto.EmpresaDTO;
import co.com.contabilidad.online.tmo.dto.PersonaDTO;
import co.com.contabilidad.online.tmo.dto.TelefonoDTO;
import co.com.contabilidad.online.tmo.dto.UsuarioDTO;

public class GestionarEmpresaRequest {

	private EmpresaDTO empresa;
	private PersonaDTO persona;
	private DireccionDTO direccion;
	private DireccionElectronicaDTO direccionElectronica;
	private List<TelefonoDTO> lstTelefonos;
	private UsuarioDTO usuario;

	public GestionarEmpresaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public DireccionElectronicaDTO getDireccionElectronica() {
		return direccionElectronica;
	}

	public void setDireccionElectronica(DireccionElectronicaDTO direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

	public List<TelefonoDTO> getLstTelefonos() {
		return lstTelefonos;
	}

	public void setLstTelefonos(List<TelefonoDTO> lstTelefonos) {
		this.lstTelefonos = lstTelefonos;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
