/**
 * Resumen.
 * Objeto                     : IPedido.
 * Descripción                : Clase Interface para DAO de pedido.
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;
import java.sql.Connection;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Lista;
/**
 * Clase Interface para DAO de pedido.
 *
 */
public interface IPedido {
	
	/**
	 * Obtiene el numero maximo de orde de pedido alamcenada en el sistema.
	 * @param conn objeto de conexion, tipo Connection.
	 * @return numero del maximo pedido, tipo int.
	 * @throws SQLException SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerMaximoNumeroPedido(Connection conn) throws SQLException;;
	

	
	/**
	 * Obtiene listado de ordenes generadas.
	 * @return Lista de ordenes generadas.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListaOrdenes () throws SQLException;
	/**
	 * Almacena la cebecera de la orden de pedido.
	 * @param conn objeto de conexion, tipo Connection.
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @return numero de la orden almacenada, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int almacenarOrden(Connection conn,BUsuario usuario, int tipoMov) throws SQLException;
	
	/**
	 * Obtiene detalle de una orden de pedido.
	 * @param numPedido numero de la orden del pedido.
	 * @return listado con el detalle de la orden.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerDetalleOrden(int numPedido)throws SQLException;

	/**
	 * Actualiza el estado de la orden.
	 * @param conn objeto de conexion, tipo Connection.
	 * @param numPedido numero de la orden del pedido. 
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void actualizarEstadoPedido(Connection conn,int numPedido, BUsuario usuario)throws SQLException;
}
