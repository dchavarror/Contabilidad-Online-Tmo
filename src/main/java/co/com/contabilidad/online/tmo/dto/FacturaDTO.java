package co.com.contabilidad.online.tmo.dto;

public class FacturaDTO {

	private String numero;
	private byte[] archivo;

	public FacturaDTO() {
		this.numero ="202102191";
	}
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
