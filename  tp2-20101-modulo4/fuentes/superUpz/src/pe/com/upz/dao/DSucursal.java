/**
 * Resumen.
 * Objeto                     : DSucursal.
 * Descripción                : Clase DAO de sucursales.
 * Fecha de Creación          : 15/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ISucursal;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de sucursales.
 *
 */
public class DSucursal implements ISucursal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.upz.daoInterface.ISucursal#obtenerListadoSucursales()
	 */
	public Lista obtenerListadoSucursales() throws SQLException {

		Connection conn = ConnectDS.obtenerConeccion();
		BSucursal sucursal;
		Lista lista = new Lista();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SU.SUCURSAL_ID AS CODIGO   , \n");
		sql.append("       SU.DESCRIPCION AS NOMBRE   , \n");
		sql.append("       SU.DIRECCION   AS DIRECCION, \n");
		sql.append("       SU.TELEFONO    AS TELEFONO , \n");
		sql.append("       SU.ESTADO      AS ESTADO \n");
		sql.append("FROM   FIDELIZACION.SUCURSAL SU \n");
		sql.append("WHERE  SU.ESTADO = 1");
		sql.append("ORDER BY SU.DESCRIPCION");

		pstm = conn.prepareStatement(sql.toString());
		// pstm.setInt(1, usuario.getCodigo());

		rs = pstm.executeQuery();

		while (rs.next()) {
			sucursal = new BSucursal();

			sucursal.setCodigo(rs.getInt("CODIGO"));
			sucursal.setDescripcion(rs.getString("NOMBRE"));
			sucursal.setEstado(rs.getInt("ESTADO"));
			sucursal.setDireccion(rs.getString("DIRECCION"));
			sucursal.setTelefono(rs.getString("TELEFONO"));
			lista.setElemento(sucursal);
		}
		rs.close();
		pstm.close();
		conn.close();
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.upz.daoInterface.ISucursal#obtenerSucursal(java.lang.String)
	 */
	@Override
	public BSucursal obtenerSucursal(int codigoSucursal) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		BSucursal sucursal;
		Lista lista = new Lista();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SU.SUCURSAL_ID AS CODIGO   , \n");
		sql.append("       SU.DESCRIPCION AS NOMBRE   , \n");
		sql.append("       SU.DIRECCION   AS DIRECCION, \n");
		sql.append("       SU.TELEFONO    AS TELEFONO , \n");
		sql.append("       SU.ESTADO      AS ESTADO \n");
		sql.append("FROM   FIDELIZACION.SUCURSAL SU \n");
		sql.append("WHERE  SU.SUCURSAL_ID = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigoSucursal);

		rs = pstm.executeQuery();

		sucursal = new BSucursal();
		rs.next();
		sucursal.setCodigo(rs.getInt("CODIGO"));
		sucursal.setDescripcion(rs.getString("NOMBRE"));
		sucursal.setEstado(rs.getInt("ESTADO"));
		sucursal.setDireccion(rs.getString("DIRECCION"));
		sucursal.setTelefono(rs.getString("TELEFONO"));

		rs.close();
		pstm.close();
		conn.close();
		return sucursal;
	}

}
