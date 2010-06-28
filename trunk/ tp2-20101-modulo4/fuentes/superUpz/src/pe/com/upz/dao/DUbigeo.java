/**
 * Resumen.
 * Objeto                     : DUsuario.
 * Descripción                : Clase DAO de ubigeo del sistema.
 * Fecha de Creación          : 15/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.IUbigeo;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de ubigeo del sistema.
 *
 */
public class DUbigeo implements IUbigeo{

	@Override
	public Lista obtenerDistritosDeprovincia(String departamento,
			String provincia)throws SQLException {
		// TODO Auto-generated method stub
		return obtenerListadoUbigeo(departamento,provincia,null);
	}

	@Override
	public Lista obtenerProvinciasDeDepartamento(String departamento)throws SQLException {
		// TODO Auto-generated method stub
		return obtenerListadoUbigeo(departamento,null,null);
	}

	@Override
	public Lista obtenerDepartamentos() throws SQLException {
		// TODO Auto-generated method stub
		return obtenerListadoUbigeo(null,null,null);
	}

	@Override
	public Lista obtenerListadoUbigeo(String departamento, String provincia,
			String distrito) throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BUbigeo ubigeo;
		Lista lista = new Lista(); 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int contaParametro=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT U.UBIGEO_ID AS CODIGO, \n");
		sql.append("   U.DEPARTAMENTO AS DEP, \n");
		sql.append("   U.PROVINCIA AS PROV, \n");
		sql.append("   U.DISTRITO AS DIST, \n");
		sql.append("   U.ESTADO AS ESTADO, \n");
		sql.append("   U.NOMBRE AS NOMBRE  \n");
		sql.append(" FROM FIDELIZACION.UBIGEO U \n");
		sql.append(" WHERE U.ESTADO = 1  \n");
		if(departamento != null && provincia == null && distrito == null){
			sql.append(" AND U.DEPARTAMENTO = ?  \n");
			sql.append(" AND U.PROVINCIA <> '00' \n");
			sql.append(" AND U.DISTRITO = '00' \n");
		}else if(departamento != null && provincia != null && distrito == null){
			sql.append(" AND U.DEPARTAMENTO = ?  \n");
			sql.append(" AND U.PROVINCIA = ? \n");
			sql.append(" AND U.DISTRITO <> '00' \n");
		}else if(departamento != null && provincia != null && distrito != null){
			sql.append(" AND U.DEPARTAMENTO = ?  \n");
			sql.append(" AND U.PROVINCIA = ? \n");
			sql.append(" AND U.DISTRITO = ? \n");
		}else{
			sql.append(" AND U.PROVINCIA = '00' \n");
			sql.append(" AND U.DISTRITO = '00' \n");	
		}
		//sql.append(" AND U.DISTRITO = '?' \n");
		sql.append("ORDER BY 2,3,4");
		System.out.println(sql.toString());
		pstm = conn.prepareStatement(sql.toString());
		if(departamento != null && provincia == null && distrito == null){
			contaParametro = contaParametro+1;
			pstm.setString(1, departamento);
		}
		else if(departamento != null && provincia != null && distrito == null){
			contaParametro = contaParametro+1;
			pstm.setString(1, departamento);
			pstm.setString(2, provincia);
		}
		else if(departamento != null && provincia != null && distrito != null){
			contaParametro = contaParametro+1;
			pstm.setString(1, departamento);
			pstm.setString(2, provincia);
			pstm.setString(3, distrito);
		}
		rs = pstm.executeQuery();
		
		BTipoProducto tipo;
		while(rs.next()){
			ubigeo = new BUbigeo();
			ubigeo.setCodigo(rs.getInt("CODIGO"));
			ubigeo.setNombre(rs.getString("NOMBRE"));
			ubigeo.setDepartamento(rs.getString("DEP"));
			ubigeo.setProvincia(rs.getString("PROV"));
			ubigeo.setDistrito(rs.getString("DIST"));
			lista.setElemento(ubigeo);
		}
		
		rs.close();
		pstm.close();
		conn.close();
		
		return lista;
	}

	@Override
	public int obtenerCodigoUbigeo(String departamento, String provincia, String distrito)
			throws SQLException {
		// TODO Auto-generated method stub
		return ((BUbigeo)obtenerListadoUbigeo(departamento, provincia,
				distrito).getElemento(0)).getCodigo();
	}

}
