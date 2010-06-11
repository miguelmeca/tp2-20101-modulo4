/**
 * Resumen.
 * Objeto                     : BRol.
 * Descripción                : Bean de roles del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

/**
 * Bean de roles del sistema.
 *
 */
public class BRol extends Bean{
	/**
	 * Codigo del rol.
	 */
	private int codigoRol;
	/**
	 * descripcion del rol.
	 */
	private String descripcionRol;
	/**
	 * Listado de opciones del rol.
	 */
	private Lista listaOpciones;
	
	/**
	 * retorna codigo del rol.
	 * @return codigo del rol.
	 */
	public int getCodigoRol() {
		return codigoRol;
	}
	/**
	 * Setea codigo del rol.
	 * @param codigoRol codigo del rol.
	 */
	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}
	/**
	 * Retorna descripcion del rol.
	 * @return descripcion del rol.
	 */
	public String getDescripcionRol() {
		return descripcionRol;
	}
	/**
	 * Setea descripcion del rol.
	 * @param descripcionRol descripcion del rol.
	 */
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	/**
	 * Retorna listado de opciones del rol.
	 * @return listado de opciones del rol.
	 */
	public Lista getListaopciones() {
		return listaOpciones;
	}
	/**
	 * Setea listado de opciones del rol.
	 * @param listaOpciones listado de opciones del rol.
	 */
	public void setListaopciones(Lista listaOpciones) {
		this.listaOpciones = listaOpciones;
	}
	
	
}
