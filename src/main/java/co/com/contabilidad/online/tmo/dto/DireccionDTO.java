package co.com.contabilidad.online.tmo.dto;

public class DireccionDTO {

	private Integer codigo;
	private String descripcion;
	private TipoGenericoDTO departamento;
	private TipoGenericoDTO municipio;
	private TipoGenericoDTO tipo;

	public DireccionDTO() {
		super();
		this.tipo = new TipoGenericoDTO();
		this.departamento = new TipoGenericoDTO();
		this.municipio = new TipoGenericoDTO();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoGenericoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(TipoGenericoDTO departamento) {
		this.departamento = departamento;
	}

	public TipoGenericoDTO getMunicipio() {
		return municipio;
	}

	public void setMunicipio(TipoGenericoDTO municipio) {
		this.municipio = municipio;
	}

	public TipoGenericoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoGenericoDTO tipo) {
		this.tipo = tipo;
	}

}
