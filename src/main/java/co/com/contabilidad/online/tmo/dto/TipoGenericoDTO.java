package co.com.contabilidad.online.tmo.dto;

import java.io.Serializable;

public class TipoGenericoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412447759061927717L;

	private String codigo;
	private String subCodigo;
	private String descripcion;

	public TipoGenericoDTO() {
		super();
	}

	public TipoGenericoDTO(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public TipoGenericoDTO(String codigo, String subCodigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.subCodigo = subCodigo;
		this.descripcion = descripcion;
	}

	// Accesores

	public String getCodigo() {
		return codigo;
	}

	public String getSubCodigo() {
		return subCodigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setSubCodigo(String subCodigo) {
		this.subCodigo = subCodigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
