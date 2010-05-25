/**
 * Resumen.
 * Objeto                     : IPedido.
 * Descripci�n                : Clase Interface para DAO de pedido.
 * Fecha de Creaci�n          : 25/05/2010.
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
	
	public abstract int obtenerMaximoNumeroPedido(Connection conn) throws SQLException;;
	
	public abstract int almacenarOrden(Connection conn,BUsuario usuario) throws SQLException;;
}
