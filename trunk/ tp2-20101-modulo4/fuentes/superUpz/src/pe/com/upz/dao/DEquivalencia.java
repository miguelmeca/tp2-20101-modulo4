/**
 * Resumen.
 * Objeto                     : DEquivalencia.
 * Descripción                : Clase DAO de equivalencia de puntos.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.daoInterface.IEquivalencia;

/**
 * Clase DAO de equivalencia de puntos.
 *
 */
public class DEquivalencia implements IEquivalencia {

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IEquivalencia#almacenarEquivalencia(pe.com.upz.bean.BUsuario, java.sql.Connection, pe.com.upz.bean.BEquivalencia, int)
	 */
	public void almacenarEquivalencia(BUsuario usuario, Connection conn,
			BEquivalencia equivalencia, int codProducto) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximaCodificacion(conn) + 1;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   fidelizacion.equivalencia \n");
		sql.append("       ( \n");
		sql.append("              monto_uno          , \n");
		sql.append("              cantidad_punto_uno , \n");
		sql.append("              monto_dos          , \n");
		sql.append("              cantidad_punto_dos , \n");
		sql.append("              monto_tres         , \n");
		sql.append("              cantidad_punto_tres, \n");
		sql.append("              fecha_creacion     , \n");
		sql.append("              usuario_creacion   , \n");
		sql.append("              estado             , \n");
		sql.append("              equivalencia_id    , \n");
		sql.append("              producto_id \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              ?      , \n");
		sql.append("              1      , \n");
		sql.append("              ?      , \n");
		sql.append("              ? \n");
		sql.append("       )");

		pstm = conn.prepareStatement(sql.toString());

		pstm.setDouble(1, equivalencia.getMontoUno());
		pstm.setInt(2, equivalencia.getCantidadPuntoUno());
		pstm.setDouble(3, equivalencia.getMontoDos());
		pstm.setInt(4, equivalencia.getCantidadPuntoDos());
		pstm.setDouble(5, equivalencia.getMontoTres());
		pstm.setInt(6, equivalencia.getCantidadPuntoTres());

		pstm.setString(7, usuario.getLogin());
		pstm.setInt(8, codigo);
		pstm.setInt(9, codProducto);
		pstm.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IEquivalencia#eliminarEquivalenciasActivasProducto(pe.com.upz.bean.BUsuario, java.sql.Connection, int)
	 */
	public void eliminarEquivalenciasActivasProducto(BUsuario usuario,
			Connection conn, int codProducto) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE FIDELIZACION.EQUIVALENCIA EQ \n");
		sql.append("SET    EQ.ESTADO              = 0, \n");
		sql.append("       EQ.USUARIO_MODIFICACION=? , \n");
		sql.append("       EQ.FECHA_MODIFICACION  = SYSDATE \n");
		sql.append("WHERE  EQ.PRODUCTO_ID         = ? \n");
		sql.append("AND    EQ.ESTADO              = 1");
		pstm = conn.prepareStatement(sql.toString());

		pstm.setString(1, usuario.getLogin());
		pstm.setInt(2, codProducto);
		pstm.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IEquivalencia#obtenerMaximaCodificacion(java.sql.Connection)
	 */
	public int obtenerMaximaCodificacion(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigo = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.equivalencia_id),0) AS MAXIMO \n");
		sql.append("FROM   FIDELIZACION.EQUIVALENCIA PE");

		pstm = conn.prepareStatement(sql.toString());

		rs = pstm.executeQuery();

		if (rs.next()) {
			codigo = (rs.getInt("MAXIMO"));

		}
		rs.close();
		pstm.close();

		return codigo;
	}

}
