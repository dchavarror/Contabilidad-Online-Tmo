package co.com.contabilidad.online.tmo.dto;

public class CategoriaDTO {
	
	private Integer codigo;
	private String descripcion;
	private Integer codigoSegmento;
	private UsuarioDTO usuario;
	
	public CategoriaDTO() {
		super();
		this.usuario = new UsuarioDTO();
	}

	public CategoriaDTO(Integer codigo, String descripcion, Integer codigoSegmento) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoSegmento = codigoSegmento;
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

	public Integer getCodigoSegmento() {
		return codigoSegmento;
	}

	public void setCodigoSegmento(Integer codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
