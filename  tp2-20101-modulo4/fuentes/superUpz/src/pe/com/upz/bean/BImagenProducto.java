/**
 * Resumen.
 * Objeto                     : BImagenProducto.
 * Descripción                : Bean de imagen producto.
 * Fecha de Creación          : 15/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de imagen producto.
 *
 */
public class BImagenProducto extends Bean {
	
	/**
	 * codigo de la imagen
	 */
	int codigo;
	/**
	 * nombre de la imagen
	 */
	String nombre;
	
	/**
	 * nombre del archivo
	 */
	String archivo;

	/**
	 * Retorna codigo de la imagen, tipo int
	 * @return codigo de la imagen, tipo int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 *  Setea codigo de la imagen, tipo int
	 * @param codigo  codigo de la imagen, tipo int
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna nombre de la imagen, tipo String.
	 * @return  nombre de la imagen, tipo String.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea nombre de la imagen, tipo String.
	 * @param nombre nombre de la imagen, tipo String.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna archivo del archivo, tipo String.
	 * @return archivo del archivo, tipo String.
	 */
	public String getArchivo() {
		return archivo;
	}

	/**
	 * Setea archivo del archivo, tipo String.
	 * @param archivo del archivo, tipo String.
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	
}
