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
import pe.com.upz.bean.BRol;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Bean;

/**
 * Clase Interface para DAO de detalle de pedido.
 *
 */
public interface IDetallePedido {
	public abstract void almacenarDetalle(Connection conn,int codigoPedido, BPedidoDetalle pedidoDetalle,BUsuario usuario)
    throws SQLException;
}
