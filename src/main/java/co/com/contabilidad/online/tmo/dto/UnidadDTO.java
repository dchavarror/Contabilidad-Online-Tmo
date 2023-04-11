package co.com.contabilidad.online.tmo.dto;

public class UnidadDTO {
	private Integer codigo;
	private String descripcion;
	private Integer codigoCategoria;
	private UsuarioDTO usuario;

	public UnidadDTO() {
		super();
		this.usuario = new UsuarioDTO();
	}

	public UnidadDTO(Integer codigo, String descripcion, Integer codigoCategoria) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoCategoria = codigoCategoria;
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

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
