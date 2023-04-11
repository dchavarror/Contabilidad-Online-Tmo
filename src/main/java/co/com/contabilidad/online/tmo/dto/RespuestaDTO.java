package co.com.contabilidad.online.tmo.dto;

public class RespuestaDTO {

	private String tipoRespuesta;
	private String codigoError;
	private String descripcion;
	private byte[] documento;

	public RespuestaDTO(String tipoRespuesta, String codigoError, String descripcion, byte[] documento) {
		super();
		this.tipoRespuesta = tipoRespuesta;
		this.codigoError = codigoError;
		this.descripcion = descripcion;
		this.documento = documento;
	}
	
	public RespuestaDTO() {
		
	}

	public String getTipoRespuesta() {
		return tipoRespuesta;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTipoRespuesta(String tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

}
