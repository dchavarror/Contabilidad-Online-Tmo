package co.com.contabilidad.online.tmo.dto;

public class MunicipioDTO {
	private Integer codigo;
	private String nombre;
	private Integer codigoDepartamento;

	public MunicipioDTO() {
		super();
	}

	public MunicipioDTO(Integer codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public MunicipioDTO(Integer codigo, String nombre, Integer codigoDepartamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.codigoDepartamento = codigoDepartamento;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

}
