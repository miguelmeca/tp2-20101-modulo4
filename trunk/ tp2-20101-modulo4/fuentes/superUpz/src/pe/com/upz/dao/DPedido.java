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

import pe.com.upz.bean.BPedido;
import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IPedido;
import pe.com.upz.util.Lista;

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
	public int almacenarOrden(Connection conn, BUsuario usuario,BSucursal sucursal, int tipoMov) throws SQLException {
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
		sql.append("              TIPO_MOVIMIENTO  , \n");
		sql.append("              SUCURSAL_ID  , \n");
		sql.append("              ESTADO \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              ?, \n");
		sql.append("              ?, \n");
		sql.append("              1 \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codigo);
		pstm.setString(2,usuario.getLogin());
		pstm.setInt(3,tipoMov);
		pstm.setInt(4,sucursal.getCodigo());
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

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IPedido#obtenerListaOrdenes()
	 */
	@Override
	public Lista obtenerListaOrdenes() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT   PE.PEDIDO_ID                          AS ID   , \n");
		sql.append("         TO_CHAR(PE.FECHA_PEDIDO,'DD/MM/YYYY') AS FECHA, \n");
		sql.append("         COUNT(1)                              AS CANTIDAD \n");
		sql.append("FROM     FIDELIZACION.PEDIDO PE, \n");
		sql.append("         FIDELIZACION.DETALLE_PEDIDO DT \n");
		sql.append("WHERE    PE.ESTADO    = 1 \n");
		sql.append("AND      PE.TIPO_MOVIMIENTO    = 1 \n");
		sql.append("AND      DT.ESTADO    = 1 \n");
		sql.append("AND      PE.PEDIDO_ID = DT.PEDIDO_ID \n");
		sql.append("GROUP BY PE.PEDIDO_ID, \n");
		sql.append("         TO_CHAR(PE.FECHA_PEDIDO,'DD/MM/YYYY') \n");
		sql.append("ORDER BY 1");
		
		pstm = conn.prepareStatement(sql.toString());
		//pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		BPedido pedido;
		while(rs.next()){
			pedido = new BPedido();
			
			pedido.setCodigo(rs.getInt("ID"));
			pedido.setFechaPedido(rs.getString("FECHA"));
			pedido.setCantidadProductos(rs.getInt("CANTIDAD"));
			lista.setElemento(pedido);
		}
		
		rs.close();
		pstm.close();
		conn.close();
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IPedido#obtenerDetalleOrden(int)
	 */
	@Override
	public Lista obtenerDetalleOrden(int numPedido) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DP.PRODUCTO_ID AS IDPRODUCTO, \n");
		sql.append("       PR.NOMBRE      AS NOMBRE    , \n");
		sql.append("       TP.DESCRIPCION AS TIPO      , \n");
		sql.append("       DP.CANTIDAD    AS CANTIDAD \n");
		sql.append("FROM   FIDELIZACION.DETALLE_PEDIDO DP, \n");
		sql.append("       FIDELIZACION.PRODUCTO PR      , \n");
		sql.append("       FIDELIZACION.TIPO_PRODUCTO TP \n");
		sql.append("WHERE  DP.PEDIDO_ID        = ? \n");
		sql.append("AND    DP.ESTADO           = 1 \n");
		sql.append("AND    PR.PRODUCTO_ID      = DP.PRODUCTO_ID \n");
		sql.append("AND    PR.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		sql.append("ORDER BY 2");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, numPedido);
		
		rs = pstm.executeQuery();
		
		BPedidoDetalle detalle;
		BProducto producto;
		BTipoProducto tipo;
		while(rs.next()){
			detalle = new BPedidoDetalle();
			producto = new BProducto();
			tipo = new BTipoProducto();
			
			producto.setCodigo(rs.getInt("IDPRODUCTO"));
			producto.setNombre(rs.getString("NOMBRE"));
			tipo.setDescripcion(rs.getString("TIPO"));
			detalle.setCantidad(rs.getInt("CANTIDAD"));
			producto.setTipo(tipo);
			detalle.setProducto(producto);
			lista.setElemento(detalle);
		}
		
		rs.close();
		pstm.close();
		conn.close();
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IPedido#actualizarEstadoPedido(int)
	 */
	@Override
	public void actualizarEstadoPedido(Connection conn,int numPedido, BUsuario usuario, int estado) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE fidelizacion.pedido PE \n");
		sql.append("SET    pe.estado               = ?     , \n");
		sql.append("       pe.fecha_modificacion   = SYSDATE, \n");
		sql.append("       pe.usuario_modificacion = ? \n");
		sql.append("WHERE  pe.pedido_id            = ?");
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,estado);
		pstm.setString(2,usuario.getLogin());
		pstm.setInt(3,numPedido);
		pstm.executeUpdate();
		
		pstm.close();
	}
	
}
