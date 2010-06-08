/**
 * Resumen.
 * Objeto                     : DTipoProducto.
 * Descripción                : Clase DAO de tipos de producto.
 * Fecha de Creación          : 31/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ITipoProducto;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de tipos de producto.
 *
 */
public class DTipoProducto implements ITipoProducto{

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.ITipoProducto#obtenerListadoTipo(boolean)
	 */
	@Override
	public Lista obtenerListadoTipo(boolean soloActivos) throws SQLException {

		Connection conn = ConnectDS.obtenerConeccion();
		BTipoProducto tipo;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT fil.TIPO_PRODUCTO_ID AS CODIGO, \n");
		sql.append("       fil.DESCRIPCION   AS NOMBRE  , \n");
		sql.append("       fil.ESTADO AS ESTADO \n");
		sql.append("FROM   FIDELIZACION.TIPO_PRODUCTO fil \n");
		if(soloActivos){
			sql.append("WHERE  fil.Estado = 1");
		}
		pstm = conn.prepareStatement(sql.toString());
		
		rs = pstm.executeQuery();
		
		while(rs.next()){
			tipo = new BTipoProducto();
			
			tipo.setCodigo(rs.getInt("CODIGO"));
			tipo.setDescripcion(rs.getString("NOMBRE"));
			
			lista.setElemento(tipo);
		}
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.ITipoProducto#obtenerListadoTipo()
	 */
	@Override
	public Lista obtenerListadoTipo() throws SQLException {
		// TODO Auto-generated method stub
		return obtenerListadoTipo(true);
		
	}

}
