/**
 * Resumen.
 * Objeto                     : IOpcion.
 * Descripción                : Clase Interface para DAO de opciones del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.bean.BRol;
import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de opciones del sistema.
 *
 */
public interface IOpcion {

	/**
	 * Obtiene los roles de una determinada opcion.
	 * @param rol rol del cual se obtendra sus opciones.
	 * @throws SQLException para excepciones tipo SQL.
	 */
	public abstract void obtenerOpcionesUsuario(BRol rol)
    throws SQLException;	
	
	/**
	 * Obtiene el listado del menu de la aplicacion.
	 * @return listado del menu de la aplicacion
	 * @throws SQLException para excepciones tipo SQL.
	 */
	public abstract Lista obtenerMenu()
    throws SQLException;
}
