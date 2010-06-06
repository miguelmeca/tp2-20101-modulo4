package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BOpcion;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
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
		sql.append("         PD.DESCRIPCION      AS DESCRIPCION, \n");
		sql.append("         PD.STOCK            AS STOCK,       \n");
		sql.append("         PD.ESTADO AS ESTADO                 \n");
		sql.append("FROM     FIDELIZACION.PRODUCTO PD, \n");
		sql.append("         FIDELIZACION.TIPO_PRODUCTO TP \n");
		sql.append("WHERE    PD.TIPO_PRODUCTO_ID = TP.TIPO_PRODUCTO_ID \n");
		if(soloActivos){
			sql.append("AND      PD.ESTADO           = 1 \n");
		}
		sql.append("ORDER BY PD.DESCRIPCION");
		
		pstm = conn.prepareStatement(sql.toString());
		//pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		while(rs.next()){
			producto = new BProducto();
			
			producto.setCodigo(rs.getInt("CODIGO"));
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
		// TODO Auto-generated method stub
		
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

}
