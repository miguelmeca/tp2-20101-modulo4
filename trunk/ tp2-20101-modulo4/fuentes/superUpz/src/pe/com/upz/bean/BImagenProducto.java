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

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	
}
