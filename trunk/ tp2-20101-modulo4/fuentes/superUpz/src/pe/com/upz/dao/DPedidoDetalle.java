/**
 * Resumen.
 * Objeto                     : DPedidoDetalle.
 * Descripción                : Clase DAO de detalle de pedido del sistema.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.daoInterface.IDetallePedido;

/**
 * Clase DAO de detalle de pedido del sistema.
 *
 */
public class DPedidoDetalle implements IDetallePedido {

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IDetallePedido#almacenarDetalle(java.sql.Connection, int, pe.com.upz.bean.BPedidoDetalle, pe.com.upz.bean.BUsuario)
	 */
	@Override
	public void almacenarDetalle(Connection conn,int codigoPedido,BPedidoDetalle pedidoDetalle, BUsuario usuario)
			throws SQLException {
		PreparedStatement pstm;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   FIDELIZACION.DETALLE_PEDIDO \n");
		sql.append("       ( \n");
		sql.append("              PEDIDO_ID       , \n");
		sql.append("              PRODUCTO_ID     , \n");
		sql.append("              CANTIDAD        , \n");
		sql.append("              USUARIO_CREACION, \n");
		sql.append("              FECHA_CREACION  , \n");
		sql.append("              ESTADO \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              1 \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codigoPedido);
		pstm.setInt(2,pedidoDetalle.getProducto().getCodigo());
		pstm.setInt(3,pedidoDetalle.getCantidad());
		pstm.setString(4,usuario.getLogin());
		pstm.executeUpdate();
		
		pstm.close();

	}

}
