package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.daoInterface.IDetallePedido;

public class DPedidoDetalle implements IDetallePedido {

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
