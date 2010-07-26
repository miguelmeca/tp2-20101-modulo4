/**
 * Resumen.
 * Objeto                     : IOpcion.
 * Descripción                : Clase Interface para DAO de productos del sistema.
 * Fecha de Creación          : 24/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	 * @return cantidad stock del producto, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerStockLocalProducto(BProducto bProducto, BSucursal bSucursal)throws SQLException;
	
	/**
	 * Actualiza el stock del producto en la sucursal
	 * @param bProducto producto a buscar, tipo BProducto.
	 * @param bSucursal sucursal a buscar, tipo BSucursal.
	 * @param cantidad stock del producto, tipo int.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void actualizarStockProductoSucursal(BProducto bProducto, BSucursal bSucursal, int cantidad,Connection conn, BUsuario usuario)throws SQLException;
	
	/**
	 * ingresa el producto en el local.
	 * @param bProducto producto a buscar, tipo BProducto.
	 * @param bSucursal sucursal a buscar, tipo BSucursal.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void crearProductoSucursal(BProducto bProducto,
			BSucursal bSucursal, Connection conn, BUsuario usuario) throws SQLException;
	
	/**
	 * Retorna lista sucursal-producto.
	 * @return liosta con sucursal-producto, tipo Lista.
	 * @throws SQLException
	 */
	public abstract Lista obtenerSucursalProductoStock() throws SQLException;
	public abstract boolean consultarSalida(int codSucursal, int codProducto) throws SQLException;
	/**
	 * Obtiene el consumo promedio de producto
	 * @param codSucursal codigo de sucursal, tipo int.
	 * @param codProducto codigo de producto, tipo int.
	 * @return promedio
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerSucursalProductoPromedio(int codSucursal, int codProducto, int anio, int mes) throws SQLException;
	
	/**
	 * Elimina logicamente un producto en la BD.
	 * @param codigo codigo del producto, tipo int.
	 * @param conn conexion a la BD, tipo Connection.
	 * @return codigo generado, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void eliminarProducto(int codigo, BUsuario usuario, Connection conn)throws SQLException;
	
	/**
	 * Elimina logicamente un producto-sucursal en la BD.
	 * @param codigo codigo del producto, tipo int.
	 * @param conn conexion a la BD, tipo Connection.
	 * @return codigo generado, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	
	public abstract void eliminarProductoSucursal(int codigo, BUsuario usuario, Connection conn)throws SQLException;

	/**
	 * Obtiene un producto por codigo.
	 * @param codigo codigo del producto, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract BProducto obtenerProductos(int codigo) throws SQLException;
	
	/**
	 * Almacena cambios de un producto.
	 * @param producto producto a actualizar, tipo BProducto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param conn conexion a la BD, tipo Connection.
	 * @throws SQLException captura excepciones tipo SQL.
	 */	
	public abstract void guardarCambiosProducto(BProducto producto, BUsuario usuario, Connection conn)throws SQLException;
}
