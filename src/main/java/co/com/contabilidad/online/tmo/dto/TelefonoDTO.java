package co.com.contabilidad.online.tmo.dto;

public class TelefonoDTO {
	
	private Integer codigo;
	private String numero;
	private TipoGenericoDTO tipo;
	
	public TelefonoDTO() {
		super();
		this.tipo = new TipoGenericoDTO();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
