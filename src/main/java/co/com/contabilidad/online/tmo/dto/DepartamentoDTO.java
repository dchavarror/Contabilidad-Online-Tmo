package co.com.contabilidad.online.tmo.dto;

public class DepartamentoDTO {
	private Integer codigo;
	private String nombre;

	public DepartamentoDTO() {
		super();
	}

	public DepartamentoDTO(Integer codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
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

}
