/**
 * Resumen.
 * Objeto                     : Bean.
 * Descripción                : Clase que implementa serializable para reealizar una 
 *								generalización de Beans en el sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */

package pe.com.upz.util;

/**
 * Clase que implementa serializable para reealizar una 
 * generalización de Beans en el sistema.
 */
public abstract class Bean  implements java.io.Serializable{

	/**
	 * Obtiene la referencia de la clase.
	 * @return referencia del nombre de la clase.
	 */
	public String getReferencia() {
		return getClass().getName();
		
	}
}