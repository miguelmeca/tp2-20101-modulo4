/**
 * Resumen.
 * Objeto                     : DUsuario.
 * Descripción                : Clase DAO de usuarios del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BOpcion;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IUsuario;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

public class DUsuario implements IUsuario {

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IUsuario#obtenerUsuarios()
	 */
	@Override
	public Lista obtenerUsuarios() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IUsuario#obtenerUsuariosxTipo(java.lang.String)
	 */
	@Override
	public Lista obtenerUsuariosxTipo(String tipo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IUsuario#validarUsuario(java.lang.String, java.lang.String)
	 */
	@Override
	public BUsuario validarUsuario(String login, String clave)
			throws SQLException {
		// TODO Auto-generated method stub
		 
		Connection conn = ConnectDS.obtenerConeccion();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("ID_USUA AS id, ");
		sql.append("DE_USUA AS login, ");
		sql.append("DE_CLAV AS clave, ");
		sql.append("NO_USUA AS nombre, ");
		sql.append("AP_PATE_USUA AS paterno, ");
		sql.append("AP_MATE_USUA AS materno, ");
		sql.append("ST_VIGE AS estado ");
		sql.append("FROM fidelizacion.USUARIO ");
		sql.append("WHERE DE_CLAV = ? ");
		sql.append("AND DE_USUA = ? ");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, clave);
		pstm.setString(2, login);

		rs = pstm.executeQuery();
		BUsuario usuario = null;

		if (rs.next()) {
			usuario = new BUsuario();
			usuario.setCodigo(rs.getInt("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setPassword(rs.getString("clave"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellidoPaterno(rs.getString("paterno"));
			usuario.setApellidoMaterno(rs.getString("materno"));
		}
		rs.close();
		pstm.close();
		conn.close();
		
		return usuario;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IUsuario#obtenerMenu(pe.com.upz.bean.BUsuario)
	 */
	@Override
	public Lista obtenerMenu(BUsuario usuario) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		BOpcion opcion;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT DISTINCT ");
		sql.append("op.id_opci AS codigo, "); 
		sql.append("op.de_opci AS descripcion, "); 
		sql.append("op.de_ruta AS ruta, "); 
		sql.append("op.st_vige AS estado, "); 
		sql.append("op.id_opci_padr AS padre, "); 
		sql.append("op.nu_nivel AS nivel,  ");
		sql.append("NVL(op.nu_orde,0) AS orden ");
		sql.append("FROM   fidelizacion.usuario_rol ur, "); 
		sql.append("fidelizacion.rol_opcion ro, "); 
		sql.append("fidelizacion.opcion op ");
		sql.append("WHERE  ur.id_usua = ? ");
		sql.append("AND    ur.id_rol=ro.id_rol ");
		sql.append("AND    ro.id_opci = op.id_opci ");
		sql.append("AND    ur.st_vige = 1 ");
		sql.append("AND    ro.st_vige = 1 ");
		sql.append("AND    op.st_vige = 1 ");
		sql.append("order by op.id_opci ");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, usuario.getCodigo());
		
		rs = pstm.executeQuery();
		
		
		while(rs.next()){
			opcion = new BOpcion();
			
			opcion.setCodigoOpcion(rs.getInt("codigo"));
			opcion.setDescripcionOpcion(rs.getString("descripcion"));
			opcion.setRuta(rs.getString("ruta"));
			opcion.setCodigoPadre(rs.getInt("padre"));
			opcion.setNivel(rs.getInt("nivel"));
			opcion.setOrden(rs.getInt("orden"));
			lista.setElemento(opcion);
		}
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see pe.com.upz.daoInterface.IUsuario#validarExisteUsuario(java.lang.String)
	 */
	@Override
	public boolean validarExisteUsuario( String sCodigo) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			conn = ConnectDS.obtenerConeccion();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ");
			sql.append("ID_USUA AS id, ");
			sql.append("DE_USUA AS login, ");
			sql.append("DE_CLAV AS clave, ");
			sql.append("NO_USUA AS nombre, ");
			sql.append("AP_PATE_USUA AS paterno, ");
			sql.append("AP_MATE_USUA AS materno, ");
			sql.append("ST_VIGE AS estado ");
			sql.append("FROM fidelizacion.USUARIO ");
			sql.append("WHERE DE_USUA = ? ");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, sCodigo);

			rs = pstm.executeQuery();

			if (rs.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: "
						+getClass().getName()
						+"; Metodo"
						+"validarExisteUsuario"
						+ "; Mensaje:"
						+ e);
		} finally{
			try {
				if(!conn.isClosed()){
					if(rs!=null){
						rs.close();
					}
					if(pstm!=null){
						pstm.close();
					}
					conn.close();
				}
				
			} catch (SQLException e2) {
				// TODO: handle exception
			}
			
		}
		return existe;
	}

}
