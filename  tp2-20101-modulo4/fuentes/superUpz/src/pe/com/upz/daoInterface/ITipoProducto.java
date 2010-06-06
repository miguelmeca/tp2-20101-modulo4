/**
 * Resumen.
 * Objeto                     : IRol.
 * Descripción                : Clase Interface para DAO de tipo de producto.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de tipo de producto.
 *
 */
public interface ITipoProducto {

	/**
	 * Obtiene el listado de tipos de productos.
	 * @param SoloActivos indica que solo obtenga los activos, tipo boolean.
	 * @return listado con los tipos de producto, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoTipo(boolean SoloActivos)
    throws SQLException;	
	/**
	 * Obtiene el listado de tipos de productos activos.
	 * @return listado con los tipos de producto, tipo Lista.
	 * @throws SQLException SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoTipo()
    throws SQLException;	
}
