/**
 * Resumen.
 * Objeto                     : CMantenimiento.
 * Descripci�n                : Clase controladora para el modulo de mantenimiento. 
 * Fecha de Creaci�n          : 31/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DTipoProducto;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.daoInterface.ITipoProducto;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de mantenimiento.
 *
 */
public class CMantenimiento {

	
	/**
	 * Obtiene el lsiatdo de productos segun un filtro de busqueda.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @return listadoProducto listado con los productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoProductos(boolean soloActivos, int filtro)throws SQLException{
		Lista listadoProducto=null;
		IProducto daoProducto = new DProducto();
		
		listadoProducto = daoProducto.obtenerListadoProductos(soloActivos,filtro);
		
		return listadoProducto;
	}
	/**
	 * Obtiene el lsiatdo de tipos de productos.
	 * @return listadoTipoProducto listado con los tipos de productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoTipoProductos()throws SQLException{
		Lista listadoTipoProducto=null;
		ITipoProducto daoTipoProducto = new DTipoProducto();
		
		listadoTipoProducto = daoTipoProducto.obtenerListadoTipo();
		
		return listadoTipoProducto;
	}	
	
	public void almacenarProducto(BProducto producto, Connection conn)throws SQLException{
		IProducto dProducto = new DProducto();
		dProducto.alamacenarProducto(producto, conn);
	}
	
}
