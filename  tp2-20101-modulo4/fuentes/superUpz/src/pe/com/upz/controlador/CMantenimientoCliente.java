/**
 * Resumen.
 * Objeto                     : CMantenimientoCliente.
 * Descripción                : Clase controladora para el modulo de clientes. 
 * Fecha de Creación          : 15/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.SQLException;

import pe.com.upz.dao.DCliente;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de clientes. 
 *
 */
public class CMantenimientoCliente {
	/**
	 * Obtiene el listado de clientes segun un filtro de busqueda.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @param valorAux valor para realizar la busqueda con filtro, tipo String.
	 * @return listadoProducto listado con los productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoClientes(boolean soloActivos, int filtro,String valorAux,String valorAux2,String valorAux3)throws SQLException{
		Lista listadoCliente=null;
		ICliente daoCliente = new DCliente();
		
		listadoCliente = daoCliente.obtenerListadoClientes(soloActivos, filtro, valorAux, valorAux2, valorAux3);
		
		return listadoCliente;
	}
}
