package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BOpcion;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

public class DProducto implements IProducto{

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerListadoProductos(boolean)
	 */
	@Override
	public Lista obtenerListadoProductos(boolean soloActivos,int filtro)
			throws SQLException {
		
		Connection conn = ConnectDS.obtenerConeccion();
		BProducto producto;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT   PD.PRODUCTO_ID      AS CODIGO     , \n");
		sql.append("         PD.TIPO_PRODUCTO_ID AS TIPO       , \n");
		sql.append("         TP.DESCRIPCION      AS DESTIPO    , \n");
		sql.append("         PD.NOMBRE      AS NOMBRE, \n");
		sql.append("         PD.DESCRIPCION      AS DESCRIPCION, \n");
		sql.append("         PD.STOCK            AS STOCK,       \n");
		sql.append("         PD.ESTADO AS ESTADO                 \n");
		sql.append("FROM     FIDELIZACION.PRODUCTO PD, \n");
		sql.append("         FIDELIZACION.TIPO_PRODUCTO TP \n");
		sql.append("WHERE    PD.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		if(soloActivos){
			sql.append("AND      PD.ESTADO           = 1 \n");
		}
		sql.append("ORDER BY PD.NOMBRE");
		
		pstm = conn.prepareStatement(sql.toString());
		//pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		while(rs.next()){
			producto = new BProducto();
			
			producto.setCodigo(rs.getInt("CODIGO"));
			producto.setNombre(rs.getString("NOMBRE"));
			producto.setDescripcion(rs.getString("DESCRIPCION"));
			producto.setEstado(rs.getInt("ESTADO"));
			tipo = new BTipoProducto();
			tipo.setCodigo(rs.getInt("TIPO"));
			tipo.setDescripcion(rs.getString("DESTIPO"));
			producto.setTipo(tipo);
			producto.setStock(rs.getLong("STOCK"));
			lista.setElemento(producto);
		}
		
		return lista;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#validarFechaGeneracionOrden(java.lang.String)
	 */
	@Override
	public boolean validarFechaGeneracionOrden(String fechaHoy)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#alamacenarProducto(pe.com.upz.bean.BProducto, pe.com.upz.bean.BUsuario, java.sql.Connection)
	 */
	@Override
	public int alamacenarProducto(BProducto producto, BUsuario usuario, Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximoNumeroProducto(conn)+1;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   PRODUCTO \n");
		sql.append("       ( \n");
		sql.append("              PRODUCTO_ID         , \n");
		sql.append("              TIPO_PRODUCTO_ID    , \n");
		sql.append("              DESCRIPCION         , \n");
		sql.append("              STOCK               , \n");
		sql.append("              USUARIO_CREACION    , \n");
		sql.append("              USUARIO_MODIFICACION, \n");
		sql.append("              FECHA_CREACION      , \n");
		sql.append("              FECHA_MODIFICACION  , \n");
		sql.append("              ESTADO              , \n");
		sql.append("              NOMBRE \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              0      , \n");
		sql.append("              ?      , \n");
		sql.append("              NULL   , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              NULL   , \n");
		sql.append("              1      , \n");
		sql.append("              ? \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1,codigo);
		pstm.setInt(2,producto.getTipo().getCodigo());
		pstm.setString(3,producto.getDescripcion());
		pstm.setString(4,usuario.getLogin());
		pstm.setString(5,producto.getNombre());
		pstm.executeUpdate();
		
		return codigo;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerMaximoNumeroProducto(java.sql.Connection)
	 */
	public int obtenerMaximoNumeroProducto(Connection conn)throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigoProducto=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.PRODUCTO_ID),0) AS MAXIMO \n");
		sql.append("FROM   FIDELIZACION.PRODUCTO PE");
		
		pstm = conn.prepareStatement(sql.toString());
		
		rs = pstm.executeQuery();
		
		if (rs.next()) {
			codigoProducto=(rs.getInt("MAXIMO"));
			
		}
		rs.close();
		pstm.close();
		
		return codigoProducto;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IProducto#obtenerListadoProductosPuntaje(boolean, int)
	 */
	public Lista obtenerListadoProductosPuntaje(boolean soloActivos, int filtro)
			throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BProducto producto;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PR.PRODUCTO_ID  AS CODIGO            , \n");
		sql.append("       PR.TIPO_PRODUCTO_ID  AS TIPO        , \n");
		sql.append("       TP.DESCRIPCION  AS DESTIPO            , \n");
		sql.append("       PR.NOMBRE    AS NOMBRE               , \n");
		sql.append("       NVL(EQ.MONTO_UNO,0)   AS   EQM1    , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_UNO,0) AS EQP1, \n");
		sql.append("       NVL(EQ.MONTO_DOS,0)    AS   EQM2     , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_DOS,0) AS EQP2, \n");
		sql.append("       NVL(EQ.MONTO_TRES,0)   AS   EQM3     , \n");
		sql.append("       NVL(EQ.CANTIDAD_PUNTO_TRES,0) AS EQP3 \n");
		sql.append("FROM   FIDELIZACION.PRODUCTO PR     , \n");
		sql.append("       FIDELIZACION.TIPO_PRODUCTO TP, \n");
		sql.append("       FIDELIZACION.EQUIVALENCIA EQ \n");
		sql.append("WHERE  PR.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		if(soloActivos){
			sql.append("AND    PR.ESTADO           = 1 \n");
		}
		sql.append("AND    PR.PRODUCTO_ID      = EQ.PRODUCTO_ID(+) \n");
		sql.append("AND    EQ.ESTADO(+)        = 1");
		sql.append("ORDER BY PR.NOMBRE");
		
		pstm = conn.prepareStatement(sql.toString());
		//pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		Lista equivalenciaListado;
		BEquivalencia equivalencia;
		while(rs.next()){
			producto = new BProducto();
			
			producto.setCodigo(rs.getInt("CODIGO"));
			producto.setNombre(rs.getString("NOMBRE"));
			tipo = new BTipoProducto();
			tipo.setCodigo(rs.getInt("TIPO"));
			tipo.setDescripcion(rs.getString("DESTIPO"));
			producto.setTipo(tipo);
			equivalenciaListado = new Lista();
			equivalencia = new BEquivalencia();
			equivalencia.setCantidadPuntoUno(rs.getInt("EQP1"));
			equivalencia.setCantidadPuntoDos(rs.getInt("EQP2"));
			equivalencia.setCantidadPuntoTres(rs.getInt("EQP3"));
			equivalencia.setMontoUno(rs.getDouble("CODIGO"));
			equivalencia.setMontoDos(rs.getDouble("CODIGO"));
			equivalencia.setMontoTres(rs.getDouble("CODIGO"));
			equivalenciaListado.setElemento(equivalencia);
			producto.setListaPuntaje(equivalenciaListado);
			lista.setElemento(producto);
		}
		
		return lista;
	}

}
