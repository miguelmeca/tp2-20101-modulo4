/**
 * Resumen.
 * Objeto                     : IDetallePedido.
 * Descripción                : Clase Interface para DAO de detalle de pedido.
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BUsuario;


/**
 * Clase Interface para DAO de detalle de pedido.
 *
 */
public interface IDetallePedido {
	/**
	 * @param conn objeto de conexion, tipo Connection.
	 * @param codigoPedido numero de la orden, tipo int.
	 * @param pedidoDetalle detalle de la orden a almacenar, tipo BPedidoDetalle.
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void almacenarDetalle(Connection conn,int codigoPedido, BPedidoDetalle pedidoDetalle,BUsuario usuario)
    throws SQLException;
}
