package co.com.contabilidad.online.tmo.dto;

public class SegmentoDTO {

	private Integer codigo;
	private String descripcion;
	private UsuarioDTO usuario;

	public SegmentoDTO() {
		super();
		this.usuario = new UsuarioDTO();
	}

	public SegmentoDTO(Integer codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
