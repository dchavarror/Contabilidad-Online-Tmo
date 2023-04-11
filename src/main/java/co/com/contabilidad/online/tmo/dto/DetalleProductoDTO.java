package co.com.contabilidad.online.tmo.dto;

public class DetalleProductoDTO {
	private String codigo;
	private Double precioUnitario;
	private String capacidad;
	private ProductoDTO producto;
	private Integer cantidad;
	private Integer porcentajeGanancia;
	private Double precioFinal;
	private UsuarioDTO usuario;
	private String fecCreacion;
	private String fecModificacion;
	private TipoGenericoDTO tipoUnidad;
	private String codigoBarras;

	public DetalleProductoDTO() {
		this.producto = new ProductoDTO();
		this.usuario = new UsuarioDTO();
		this.tipoUnidad = new TipoGenericoDTO();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public Integer getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(Integer porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public TipoGenericoDTO getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(TipoGenericoDTO tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	public String getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(String fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public String getFecModificacion() {
		return fecModificacion;
	}

	public void setFecModificacion(String fecModificacion) {
		this.fecModificacion = fecModificacion;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

}
