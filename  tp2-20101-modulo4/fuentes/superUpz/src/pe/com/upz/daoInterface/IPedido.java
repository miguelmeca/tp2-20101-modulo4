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
	 * Almacena la cebecera de la orden de pedido.
	 * @param conn objeto de conexion, tipo Connection.
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @return numero de la orden almacenada, tipo int.
	 * @throws SQLException SQLException captura excepciones tipo SQL.
	 */
	public abstract int almacenarOrden(Connection conn,BUsuario usuario, int tipoMov) throws SQLException;;
}
