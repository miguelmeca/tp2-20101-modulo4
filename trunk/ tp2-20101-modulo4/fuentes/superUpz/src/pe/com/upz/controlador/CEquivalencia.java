/**
 * Resumen.
 * Objeto                     : CEquivalencia.
 * Descripción                : Clase controladora para el modulo de equivalencia. 
 * Fecha de Creación          : 20/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DEquivalencia;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.IEquivalencia;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de equivalencia. 
 *
 */
public class CEquivalencia {

	/**
	 * btiene listado con los productos y sus equivalencias.
	 * @param soloActivos indicador de productos activos, tipo boolean.
	 * @param filtro tipo de filtro a emplear, tipo int.
	 * @param valorAuxiliar valor de filtro a emplear, tipo String.
	 * @return listadoProducto listado con los productos y sus equivalencias, tipo Lista.
	 * @throws SQLException captura las excepciones SQL.
	 */
	public Lista obtenerListadoProductosPuntaje(boolean soloActivos, int filtro, String  valorAuxiliar)throws SQLException{
		Lista listadoProducto=null;
		IProducto daoProducto = new DProducto();
		
		listadoProducto = daoProducto.obtenerListadoProductosPuntaje(soloActivos,filtro,  valorAuxiliar);
		
		return listadoProducto;
	}
	/**
	 * Almacena la equivalencia de un producto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param equivalencia Equivalencia de puntos, tipo BEquivalencia.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param codProducto codigo del producto a colocar equivalencia, tipo int.
	 * @throws SQLException captura las excepciones SQL.
	 */
	public void alamcenarNuevaEquivalencia(BUsuario usuario,BEquivalencia equivalencia, Connection conn, int codProducto)throws SQLException{
		
		IEquivalencia dEquivalencia = new DEquivalencia();
		
		//primero elimino
		dEquivalencia.eliminarEquivalenciasActivasProducto(usuario, conn, codProducto);
		//ahora almaceno
		dEquivalencia.almacenarEquivalencia(usuario, conn, equivalencia, codProducto);
		
	}
	
}
