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
	/**
	 * codigo del cliente.
	 */
	private int codigo;
	/**
	 * nombre del cliente
	 */
	private String nombre;
	/**
	 * apellido paterno del cliente
	 */
	private String apellidoPaterno;
	/**
	 * apellido materno del cliente.
	 */
	private String apellidoMaterno;
	/**
	 * numero de documento del cliente
	 */
	private String numeroDocumento;
	/**
	 * telefono 1
	 */
	private String telefono;
	/**
	 * telefono 2
	 */
	private String telefonoDos;
	/**
	 * direccion de cliente.
	 */
	private String direccion;
	/**
	 * ubigeo de cliente
	 */
	private BUbigeo ubigeo;
	/**
	 * tipo de cliente
	 */
	private int tipoCliente;
	/**
	 * estado del cliente
	 */
	private int estado;
	/**
	 * correo electronico del cliente
	 */
	private String correo;
	
	/**
	 * Obtiene codigo del cliente.
	 * @return codigo del cliente, tipo int.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo del cliente.
	 * @param codigo codigo del cliente, tipo int.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Obtiene nombre del cliente
	 * @return nombre del cliente, tipo String.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setea nombre del cliente
	 * @param nombre del cliente, tipo String.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene apellido paterno
	 * @return apellido paterno, tipo String.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Setea apellido paterno
	 * @param apellidoPaterno apellido paterno, tipo String.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Obtiene apellido materno
	 * @return apellido materno, tipo String.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Setea apellido materno
	 * @param apellidoMaterno apellido materno, tipo String.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * Obtiene numero de documento de identidad
	 * @return numero de documento de identidad, tipo String.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * Setea numero de documento de identidad
	 * @param numeroDocumento numero de documento de identidad, tipo String.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * Obtiene telefono numero uno
	 * @return telefono numero uno, tipo String.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * Setea telefono numero uno
	 * @param telefono telefono numero uno, tipo String.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * Obtiene telefono numero dos
	 * @return telefono numero dos, tipo String.
	 */
	public String getTelefonoDos() {
		return telefonoDos;
	}
	/**
	 * Setea telefono numero dos
	 * @param telefonoDos telefono numero dos, tipo String.
	 */
	public void setTelefonoDos(String telefonoDos) {
		this.telefonoDos = telefonoDos;
	}
	/**
	 * Obtiene ubigeo del cliente
	 * @return ubigeo del cliente, tipo BUbigeo.
	 */
	public BUbigeo getUbigeo() {
		return ubigeo;
	}
	/**
	 * Setea ubigeo del cliente
	 * @param ubigeo ubigeo del cliente, tipo BUbigeo.
	 */
	public void setUbigeo(BUbigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	/**
	 * Obtiene tipo de cliente
	 * @return tipo de cliente, tipo int.
	 */
	public int getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * Setea tipo de cliente
	 * @param tipoCliente tipo de cliente, tipo int.
	 */
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * Obtiene estado del cliente
	 * @return estado del cliente, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea estado del cliente
	 * @param estado estado del cliente, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * Obtiene direccion del cliente
	 * @return direccion del cliente, tipo String.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Setea direccion del cliente
	 * @param direccion direccion del cliente, tipo String.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Obtiene correo electronico,
	 * @return correo electronico, tipo String.
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * Setea correo electronico,
	 * @param correo correo electronico, tipo String.
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
