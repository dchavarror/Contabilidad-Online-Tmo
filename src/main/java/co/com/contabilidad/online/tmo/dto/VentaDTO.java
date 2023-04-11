package co.com.contabilidad.online.tmo.dto;

public class VentaDTO {
	private String codigo;
	private ProductoDTO producto;
	private Integer cantidad;
	private double precioVenta;
	private String numeroFactura;
	private double valorTotal;
	private UsuarioDTO usuario;
	private String fecCreacion;
	private String fecModificacion;
	
	public VentaDTO() {
		this.producto = new ProductoDTO();
		this.usuario = new UsuarioDTO();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
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
}
