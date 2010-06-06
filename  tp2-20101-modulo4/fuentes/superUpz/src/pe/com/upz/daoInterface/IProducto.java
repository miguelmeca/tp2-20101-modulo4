/**
 * Resumen.
 * Objeto                     : IOpcion.
 * Descripción                : Clase Interface para DAO de productos del sistema.
 * Fecha de Creación          : 24/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BRol;
import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de productos del sistema.
 *
 */
public interface IProducto {
	/**
	 * obtiene un listado con los productos registrados.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @return listado de productos
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoProductos(boolean soloActivos, int filtro)
    throws SQLException;
	
	/**
	 * indica si la fecha indicada es valida para la generacion de una orden de pedido.
	 * @param fechaHoy fecha que se validara.
	 * @return indicador true si la fecha es valida o false en caso contrario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract boolean validarFechaGeneracionOrden(String fechaHoy)
    throws SQLException;
	

	/**
	 * Almacena un producto en la BD.
	 * @param producto producto a almacenar, tipo BProducto.
	 * @param conn conexion a la BD, tipo Connection.
	 */
	public abstract void alamacenarProducto(BProducto producto, Connection conn);
	

}
