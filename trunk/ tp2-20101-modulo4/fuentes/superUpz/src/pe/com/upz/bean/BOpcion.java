/**
 * Resumen.
 * Objeto                     : BOpcion.
 * Descripción                : Bean de opciones del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */

package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de opciones del sistema.
 *
 */
public class BOpcion extends Bean{
	/**
	 * codigo de la opcion.
	 */
	private int codigoOpcion;
	/**
	 * codigo del padre de la opcion.
	 */
	private int codigoPadre;
	/**
	 * descripcion de la opcion.
	 */
	private String descripcionOpcion;
	/**
	 * ruta de la opcion.
	 */
	private String ruta;
	/**
	 * nivel de la opcion.
	 */
	private int nivel;
	/**
	 * numero del orden de la opcion
	 */
	private int orden;
	
	/**
	 * Retorna numero del orden.
	 * @return numero del orden.
	 */
	public int getCodigoOpcion() {
		return codigoOpcion;
	}
	/**
	 * Setea el numero del orden.
	 * @param codigoOpcion
	 */
	public void setCodigoOpcion(int codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	/**
	 * Retorna la descrion.
	 * @return descripcion.
	 */
	public String getDescripcionOpcion() {
		return descripcionOpcion;
	}
	/**
	 * Setea la descrion.
	 * @param descripcionOpcion descripcion.
	 */
	public void setDescripcionOpcion(String descripcionOpcion) {
		this.descripcionOpcion = descripcionOpcion;
	}
	/**
	 * Retorna ruta de la opcion.
	 * @return ruta de la opcion.
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * Setea ruta de la opcion.
	 * @param ruta ruta de la opcion.
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * Retorna codigo del padre de la opcion.
	 * @return codigo del padre de la opcion.
	 */
	public int getCodigoPadre() {
		return codigoPadre;
	}
	/**
	 * Setea codigo del padre de la opcion.
	 * @param codigoPadre codigo del padre de la opcion.
	 */
	public void setCodigoPadre(int codigoPadre) {
		this.codigoPadre = codigoPadre;
	}
	/**
	 * Retorna nivel de la opcion.
	 * @return nivel de la opcion.
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Setea nivel de la opcion.
	 * @param nivel nivel de la opcion.
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * Retorna orden de posicion de la opcion
	 * @return orden de posicion de la opcion
	 */
	public int getOrden() {
		return orden;
	}
	/**
	 * Setea orden de posicion de la opcion
	 * @param orden orden de posicion de la opcion
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}
	
	
}
