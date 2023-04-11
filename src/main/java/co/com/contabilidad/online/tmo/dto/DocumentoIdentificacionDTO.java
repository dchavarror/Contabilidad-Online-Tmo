package co.com.contabilidad.online.tmo.dto;

public class DocumentoIdentificacionDTO {

	private String numero;
	private TipoGenericoDTO tipo;

	public DocumentoIdentificacionDTO() {
		super();
		this.tipo = new TipoGenericoDTO();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoGenericoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoGenericoDTO tipo) {
		this.tipo = tipo;
	}

}
