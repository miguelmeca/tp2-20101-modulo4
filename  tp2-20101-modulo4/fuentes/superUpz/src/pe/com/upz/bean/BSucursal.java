/**
 * Resumen.
 * Objeto                     : BSucursal.
 * Descripción                : Bean de sucursales del sistema.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de sucursales del sistema.
 *
 */
public class BSucursal extends Bean{
	/**
	 * codigo de la sucursal.
	 */
	private int codigo;
	/**
	 * nombre de la sucursal.
	 */
	private  String descripcion;
	/**
	 * telefono de la sucursal.
	 */
	private  String telefono;
	/**
	 * direccion de la sucursal.
	 */
	private  String direccion;
	/**
	 * estado de la sucursal.
	 */
	private  int estado;
	/**
	 *Retorna  codigo de la sucursal.
	 * @return codigo de la sucursal, tipo int.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo de la sucursal
	 * @param codigo codigo de la sucursal, tipo int.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna descripcion de la sucursal.
	 * @return descripcion de la sucursal, tipo String.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Setea descripcion de la sucursal.
	 * @param descripcion descripcion de la sucursal, tipo String.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Retorna telefono de la sucursal.
	 * @return telefono de la sucursal,tipo String.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * Setea telefono de la sucursal.
	 * @param telefono telefono de la sucursal,tipo String.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * Retorna direccion de la sucursal.
	 * @return direccion de la sucursal, tipo String
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Setea direccion de la sucursal.
	 * @param direccion direccion de la sucursal, tipo String.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Retorna estado de la sucursal.
	 * @return estado estado de la sucursal, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea estado de la sucursal.
	 * @param estado estado de la sucursal, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	} 
	
	
}
