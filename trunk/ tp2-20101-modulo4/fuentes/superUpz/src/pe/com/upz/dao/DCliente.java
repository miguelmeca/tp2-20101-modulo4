package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.util.Lista;

public class DCliente implements ICliente {
	public Lista obtenerListadoClientes(boolean soloActivos, int filtro,
			String valorAux, String valorAux2, String valorAux3)
			throws SQLException {

		Connection conn = ConnectDS.obtenerConeccion();
		BCliente cliente;
		Lista lista = new Lista();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CLIENTE_ID      , \n");
		sql.append("       NOMBRE          , \n");
		sql.append("       APELLIDO_PATERNO, \n");
		sql.append("       APELLIDO_MATERNO, \n");
		sql.append("       NUMERO_DOCUMENTO, \n");
		sql.append("       TELEFONO        , \n");
		sql.append("       TELEFONO_DOS    , \n");
		sql.append("       UBIGEO_ID       , \n");
		sql.append("       TIPO_CLIENTE_ID , \n");
		sql.append("       ESTADO \n");
		sql.append("FROM   FIDELIZACION.CLIENTE \n");
		sql.append("WHERE  1  = 1 \n");
		if(soloActivos){
			sql.append("AND    ESTADO    = 1 \n");
		}
		if (filtro == 1) {
			sql.append("AND    NUMERO_DOCUMENTO    = ? \n");
		}else if (filtro == 2) {
			sql.append("AND    NOMBRE           LIKE '%?%' \n");
			sql.append("AND    APELLIDO_PATERNO LIKE '%?%' \n");
			sql.append("AND    APELLIDO_MATERNO LIKE '%?%'");
		}
		sql.append("ORDER BY APELLIDO_PATERNO, APELLIDO_MATERNO, NOMBRE");

		pstm = conn.prepareStatement(sql.toString());
		if (filtro == 1) {
			pstm.setString(1, valorAux);
		} else if (filtro == 2) {
			pstm.setString(1, valorAux);
			pstm.setString(2, valorAux2);
			pstm.setString(3, valorAux3);
		} 
		
		rs = pstm.executeQuery();

		BUbigeo ubigeo;
		while (rs.next()) {
			cliente = new BCliente();

			cliente.setCodigo(rs.getInt("CLIENTE_ID"));
			cliente.setNombre(rs.getString("NOMBRE"));
			cliente.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			cliente.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			cliente.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
			cliente.setTelefono(rs.getString("TELEFONO"));
			cliente.setTelefonoDos(rs.getString("TELEFONO_DOS"));
			ubigeo = new BUbigeo();
			ubigeo.setCodigo(rs.getInt("UBIGEO_ID"));
			cliente.setUbigeo(ubigeo);
			cliente.setTipoCliente(rs.getInt("UBIGEO_ID"));
			cliente.setEstado(rs.getInt("ESTADO"));
			lista.setElemento(cliente);
		}

		rs.close();
		pstm.close();
		conn.close();

		return lista;
	}

}
