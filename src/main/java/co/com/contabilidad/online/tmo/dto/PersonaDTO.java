package co.com.contabilidad.online.tmo.dto;

public class PersonaDTO {

	private Integer codigo;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String fecNacimiento;
	private DocumentoIdentificacionDTO documentoIdentificacion;
	private UsuarioDTO usuario;
	private TipoGenericoDTO tipo;
	
	public PersonaDTO() {
		super();
		this.documentoIdentificacion = new DocumentoIdentificacionDTO();
		this.usuario = new UsuarioDTO();
		this.tipo = new TipoGenericoDTO();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public DocumentoIdentificacionDTO getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}

	public void setDocumentoIdentificacion(DocumentoIdentificacionDTO documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public TipoGenericoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoGenericoDTO tipo) {
		this.tipo = tipo;
	}

}
