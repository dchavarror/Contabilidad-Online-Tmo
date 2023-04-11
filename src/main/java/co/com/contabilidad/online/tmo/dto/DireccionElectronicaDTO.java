package co.com.contabilidad.online.tmo.dto;

public class DireccionElectronicaDTO {

	private Integer codigo;
	private String correoElectronico;
	private TipoGenericoDTO tipo;

	public DireccionElectronicaDTO() {
		super();
		this.tipo = new TipoGenericoDTO();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public TipoGenericoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoGenericoDTO tipo) {
		this.tipo = tipo;
	}

}
