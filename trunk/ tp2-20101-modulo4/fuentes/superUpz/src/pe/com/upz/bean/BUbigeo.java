/**
 * Resumen.
 * Objeto                     : BUbigeo.
 * Descripción                : Bean de Ubigeo del sistema.
 * Fecha de Creación          : 01/07/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de Ubigeo del sistema.
 *
 */
public class BUbigeo extends Bean {
	/**
	 * codigo del ubigeo.
	 */
	private int codigo;
	/**
	 * codigo del departamento
	 */
	private String departamento;
	/**
	 * codigo de provincia
	 */
	private String provincia;
	/**
	 * codigo de distrito
	 */
	private String distrito;
	/**
	 * nombre del ubigeo.
	 */
	private String nombre;
	/**
	 * Retorna codigo del ubigeo, tipo int.
	 * @return codigo del ubigeo, tipo int.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo del ubigeo, tipo int.
	 * @param codigo codigo del ubigeo, tipo int.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Obtiene codigo de departamento, tipo String.
	 * @return codigo de departamento, tipo String.
	 */
	public String getDepartamento() {
		return departamento;
	}
	/**
	 * Setea codigo de departamento, tipo String.
	 * @param departamento codigo de departamento, tipo String.
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * REtorna codigo de provincia, tipo String.
	 * @return codigo de provincia, tipo String.
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * Setea codigo de provincia, tipo String.
	 * @param provincia codigo de provincia, tipo String.
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * Retorna codigo de distrito, tipo String.
	 * @return codigo de distrito, tipo String.
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * Setea codigo de distrito, tipo String.
	 * @param distrito codigo de distrito, tipo String.
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	/**
	 * Retorna nombre del ubigeo, tipo String.
	 * @return nombre del ubigeo, tipo String.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setea nombre del ubigeo, tipo String.
	 * @param nombre nombre del ubigeo, tipo String.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
