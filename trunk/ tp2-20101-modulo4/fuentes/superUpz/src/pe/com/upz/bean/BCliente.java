/**
 * Resumen.
 * Objeto                     : BCliente.
 * Descripción                : Bean de clientes del programa.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de clientes del programa.
 *
 */
public class BCliente extends Bean {
	private int codigo;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String numeroDocumento;
	private String telefono;
	private String telefonoDos;
	private String direccion;
	private BUbigeo ubigeo;
	private int tipoCliente;
	private int estado;
	private String correo;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefonoDos() {
		return telefonoDos;
	}
	public void setTelefonoDos(String telefonoDos) {
		this.telefonoDos = telefonoDos;
	}
	public BUbigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(BUbigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public int getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
