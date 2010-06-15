/**
 * Resumen.
 * Objeto                     : IRol.
 * Descripción                : Clase Interface para DAO de roles del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.bean.BUsuario;

/**
 * Clase Interface para DAO de roles del sistema.
 *
 */
public interface IRol {

	/**
	 * Coloca los roles a un usuario.
	 * @param usuario usuario a colocar sus roles.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void obtenerRol(BUsuario usuario)
    throws SQLException;	
	
}
