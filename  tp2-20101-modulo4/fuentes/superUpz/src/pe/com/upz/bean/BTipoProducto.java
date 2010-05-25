/**
 * Resumen.
 * Objeto                     : BTipoProducto.
 * Descripción                : Bean de tipos de productos de fidelizacion.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;


/**
 * Bean de tipos de productos de fidelizacion.
 *
 */
public class BTipoProducto extends Bean{
	/**
	 * codigo del tipo
	 */
	private int codigo;
	/**
	 * descripcion del tipo
	 */
	private String descripcion;
	/**
	 * estado de vigencia
	 */
	private int estado;

	/**
	 * Retorna codigo del tipo.
	 * @return codigo del tipo.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo del tipo.
	 * @param codigo codigo del tipo.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna descripcion del tipo.
	 * @return descripcion del tipo.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Setea descripcion del tipo.
	 * @param descripcion descripcion del tipo.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Retorna estado de vigencia.
	 * @return estado de vigencia.
	 */
	public int getEstado() {
		return estado;
	}
	/** 
	 * Setea estado de vigencia.
	 * @param estado estado de vigencia.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
