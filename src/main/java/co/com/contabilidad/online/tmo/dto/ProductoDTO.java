package co.com.contabilidad.online.tmo.dto;


public class ProductoDTO{

	private Integer codigo;
	private String descripcion;
	private String codigoBarras;
	private Integer codigoCategoria;
	private UsuarioDTO usuario;
	private String fecInicio;
	private String fecFin;
	private TipoGenericoDTO tipoUnidad;

	public ProductoDTO(Integer codigo, String descripcion, String codigoBarras, Integer codigoCategoria ) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoBarras = codigoBarras;
		this.codigoCategoria = codigoCategoria;
		this.usuario = new UsuarioDTO();
		this.tipoUnidad = new TipoGenericoDTO();
	}
	
	public ProductoDTO() {
		this.usuario = new UsuarioDTO();
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

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}

	public String getFecFin() {
		return fecFin;
	}

	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	public TipoGenericoDTO getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(TipoGenericoDTO tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}
	
}
