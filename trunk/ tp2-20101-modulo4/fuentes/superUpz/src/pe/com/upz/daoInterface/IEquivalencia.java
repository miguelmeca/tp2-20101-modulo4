/**
 * Resumen.
 * Objeto                     : IEquivalencia.
 * Descripción                : Clase Interface para DAO de equivalencia de puntos.
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BUsuario;

/**
 * Clase Interface para DAO de equivalencia de puntos.
 *
 */
public interface IEquivalencia {

	/**
	 * Almacena la equivalencia de un producto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param equivalencia Equivalencia de puntos, tipo BEquivalencia.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param codProducto codigo del producto a colocar equivalencia, tipo int.
	 * @throws SQLException captura las excepciones SQL.
	 */
	public abstract void almacenarEquivalencia(BUsuario usuario,Connection conn,BEquivalencia equivalencia, int codProducto)throws SQLException;
	
	/**
	 * Obtiene el codigo maximo para la generacion de equivalencia, tipo int.
	 * @param conn conexion a la BD, tipo Connection.
	 * @return codigo maximo para la generacion de equivalencia, tipo int.
	 * @throws SQLException captura las excepciones SQL.
	 */
	public abstract int obtenerMaximaCodificacion(Connection conn)throws SQLException;
	
	/**
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param codProducto codigo del producto a eliminar equivalencia, tipo int.
	 * @throws SQLException captura las excepciones SQL.
	 */
	public abstract void eliminarEquivalenciasActivasProducto(BUsuario usuario,
			Connection conn, int codProducto) throws SQLException;
	
}
