/**
 * Resumen.
 * Objeto                     : DPedido.
 * Descripción                : Clase DAO de pedido.
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.daoInterface.IPedido;
import java.sql.Connection;
/**
 * Clase DAO de pedido.
 *
 */
public class DPedido implements IPedido {


	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IPedido#almacenarOrden(java.sql.Connection)
	 */
	@Override
	public int almacenarOrden(Connection conn, BUsuario usuario, int tipoMov) throws SQLException {
		PreparedStatement pstm;
		StringBuffer sql = new StringBuffer();
		int codigo = obtenerMaximoNumeroPedido(conn)+1;
		sql.append("INSERT \n");
		sql.append("INTO   FIDELIZACION.PEDIDO \n");
		sql.append("       ( \n");
		sql.append("              PEDIDO_ID       , \n");
		sql.append("              FECHA_PEDIDO    , \n");
		sql.append("              USUARIO_CREACION, \n");
		sql.append("              FECHA_CREACION  , \n");
		sql.append("              tipo_mivimiento  , \n");
		sql.append("              ESTADO \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              ?, \n");
		sql.append("              1 \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codigo);
		pstm.setString(2,usuario.getLogin());
		pstm.setInt(3,tipoMov);
		pstm.executeUpdate();
		
		
		return codigo;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IPedido#obtenerMaximoNumeroPedido(java.sql.Connection)
	 */
	@Override
	public int obtenerMaximoNumeroPedido(Connection conn) throws SQLException {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int numeroOrden=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.PEDIDO_ID),0) AS MAXIMO \n");
		sql.append("FROM   FIDELIZACION.PEDIDO PE");
		
		pstm = conn.prepareStatement(sql.toString());
		
		rs = pstm.executeQuery();
		
		if (rs.next()) {
			numeroOrden=(rs.getInt("MAXIMO"));
			
		}
		rs.close();
		pstm.close();
		
		return numeroOrden;
	}

}
