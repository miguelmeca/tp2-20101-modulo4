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
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
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
	 * @param valorAux valor para realizar la busqueda con filtro, tipo String.
	 * @return listado de productos
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoProductos(boolean soloActivos, int filtro, String valorAux)
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
	 * @return codigo generado, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int alamacenarProducto(BProducto producto,BUsuario usuario, Connection conn)throws SQLException;
	
	/**
	 * Obtiene el maximo codigo de producto
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @throws SQLException captura excepciones tipo SQL.
	 * @return
	 */
	public abstract int obtenerMaximoNumeroProducto(Connection conn)throws SQLException;
	
	/**
	 * obtiene un listado con los productos registrados y su puntaje.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @return listado de productos
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoProductosPuntaje(boolean soloActivos, int filtro, String  valorAuxiliar)
    throws SQLException;
	
	/**
	 * Obtiene el numero de un producto en un local de.
	 * @param bProducto producto a buscar, tipo BProducto.
	 * @param bSucursal sucursal a buscar, tipo BSucursal.
	 * @return cantidad
	 * @throws SQLException
	 */
	public abstract int obtenerStockLocalProducto(BProducto bProducto, BSucursal bSucursal)throws SQLException;
}
