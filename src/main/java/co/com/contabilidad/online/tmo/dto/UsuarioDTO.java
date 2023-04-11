package co.com.contabilidad.online.tmo.dto;

public class UsuarioDTO {

	private String nombre;
	private String password;
	private EmpresaDTO empresa;
	private RolDTO rol;
	private Integer codigoSegmento;

	public UsuarioDTO(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.empresa = new EmpresaDTO();
		this.rol = new RolDTO();
	}

	public UsuarioDTO() {
		super();
		this.empresa = new EmpresaDTO();
		this.rol = new RolDTO();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public Integer getCodigoSegmento() {
		return codigoSegmento;
	}

	public void setCodigoSegmento(Integer codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}
	
}
