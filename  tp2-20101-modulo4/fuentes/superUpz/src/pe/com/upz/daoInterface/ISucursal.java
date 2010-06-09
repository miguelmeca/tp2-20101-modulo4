/**
 * Resumen.
 * Objeto                     : IRol.
 * Descripción                : Clase Interface para DAO de sucursales del sistema.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de sucursales del sistema.
 *
 */
public interface ISucursal {

	public abstract Lista obtenerListadoSucursales()throws SQLException;
	
}
