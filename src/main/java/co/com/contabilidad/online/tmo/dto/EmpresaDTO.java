package co.com.contabilidad.online.tmo.dto;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO {

	private String nit;
	private String razonSocial;
	private DireccionDTO direccion;
	private List<TelefonoDTO> lstTelefonos;

	public EmpresaDTO() {
		super();
		this.direccion = new DireccionDTO();
		this.lstTelefonos = new ArrayList<TelefonoDTO>();
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public List<TelefonoDTO> getLstTelefonos() {
		return lstTelefonos;
	}

	public void setLstTelefonos(List<TelefonoDTO> lstTelefonos) {
		this.lstTelefonos = lstTelefonos;
	}

}
