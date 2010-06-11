/**
 * Resumen.
 * Objeto                     : DRol.
 * Descripción                : Clase DAO de roles del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BOpcion;
import pe.com.upz.bean.BRol;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IRol;
import pe.com.upz.util.Lista;

public class DRol implements IRol {

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IRol#obtenerRol(pe.com.upz.bean.BUsuario)
	 */
	@Override
	public void obtenerRol(BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		Lista listaRol = new Lista();
		Connection conn = ConnectDS.obtenerConeccion();
		BRol rol;
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT R.ID_ROL  AS CODIGO    , \n");
		sql.append("       R.DE_ROL  AS DESCRIPCIO, \n");
		sql.append("       R.ST_VIGE AS ESTADO \n");
		sql.append("FROM   FIDELIZACION.ROL R, \n");
		sql.append("       FIDELIZACION.USUARIO_ROL U \n");
		sql.append("WHERE  U.ID_USUA = ? \n");
		sql.append("AND    U.ID_ROL  = R.ID_ROL");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		while(rs.next()){
			rol = new BRol();
			
			rol.setCodigoRol(rs.getInt("CODIGO"));
			rol.setDescripcionRol(rs.getString("DESCRIPCIO"));
			
			listaRol.setElemento(rol);
		}
		usuario.setListaRol(listaRol);
	}

}
