/**
 * Resumen.
 * Objeto                     : IUsuario.
 * Descripción                : Clase Interface para DAO de usuarios del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Lista;

/**
 * @author DMJAZABACHE
 *
 */
public interface IUsuario {

	/**
	 * Indica si el login de un usuario existe registrado en el sistema.
	 * @param sCodigo login del usuario, tipo String.
	 * @return true si existe el login, en caso cotrario, false, tipo boolean.
	 */
	public abstract boolean validarExisteUsuario(String sCodigo);
	
	/**
	 * Retorna un usuario si su clase y login son correctos.
	 * @param sCodigo login del usuario, tipo String.
	 * @param sPassword clave del usuario, tipo String.
	 * @return usuario del sistema, tipo BUsuario.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract BUsuario validarUsuario(String sCodigo, String sPassword)
    throws SQLException;

	
	/**
	 * retorna la lista de usuarios del sistema.
	 * @return lista de usuarios, tipo Lista.
	 * @throws Exception captura las excepciones del sistema.
	 */
	public abstract Lista obtenerUsuarios() throws Exception;
	
	/**
	 * Retorna un listado con los usuarios de un tipo.
	 * @param tipo tipo de usuario, tipo String.
	 * @return listado de usuarios, tipo Lista.
	 * @throws Exception captura las excepciones del sistema.
	 */
	public abstract Lista obtenerUsuariosxTipo(String tipo) throws Exception;
	
	/**
	 * Obtiene las opciones del menu de un usuario determinado.
	 * @param usuario usuario a buscar iopciones del menu, tipo BUsuario.
	 * @return lista de opcioness, tipo Lista.
	 * @throws Exception captura las excepciones del sistema.
	 */
	public abstract Lista obtenerMenu(BUsuario usuario) throws Exception;
}
