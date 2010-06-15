/**
 * Resumen.
 * Objeto                     : IRol.
 * Descripción                : Clase Interface para DAO de sucursales del sistema.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.bean.BSucursal;
import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de sucursales del sistema.
 *
 */
public interface ISucursal {

	/**
	 * Obtiene el listado de sucursales.
	 * @return listado de sucursales, tipo Lista.
	 * @throws SQLException captura excepciones SQL.
	 */
	public abstract Lista obtenerListadoSucursales()throws SQLException;
	
	/**
	 * Obtiene una sucursal sucursales.
	 * @param codigoSucursal codigo de la sucursal a buscar, tipo int.
	 * @return sucursal, tipo BSucursal.
	 * @throws SQLException captura excepciones SQL.
	 */
	public abstract BSucursal obtenerSucursal(int codigoSucursal)throws SQLException;
	
}
