/**
 * Resumen.
 * Objeto                     : DTipoProducto.
 * Descripción                : Clase DAO de tipos de producto.
 * Fecha de Creación          : 31/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.SQLException;

import pe.com.upz.daoInterface.ITipoProducto;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de tipos de producto.
 *
 */
public class DTipoProducto implements ITipoProducto{

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.ITipoProducto#obtenerListadoTipo(boolean)
	 */
	@Override
	public Lista obtenerListadoTipo(boolean SoloActivos) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.ITipoProducto#obtenerListadoTipo()
	 */
	@Override
	public Lista obtenerListadoTipo() throws SQLException {
		// TODO Auto-generated method stub
		return obtenerListadoTipo(true);
		
	}

}
