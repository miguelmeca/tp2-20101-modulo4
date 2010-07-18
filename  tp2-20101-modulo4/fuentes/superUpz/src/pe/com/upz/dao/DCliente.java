/**
 * Resumen.
 * Objeto                     : DCliente.
 * Descripción                : Clase DAO de clientes
 * Fecha de Creación          : 15/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de clientes
 *
 */
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
			sql.append("AND    NOMBRE           LIKE '%'||?||'%' \n");
			sql.append("AND    APELLIDO_PATERNO LIKE '%'||?||'%' \n");
			sql.append("AND    APELLIDO_MATERNO LIKE '%'||?||'%'");
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

	
	public BCliente obtenerCliente(int codigo)throws SQLException {

		Connection conn = ConnectDS.obtenerConeccion();
		BCliente cliente=null;
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
		sql.append("       ESTADO, \n");
		sql.append("       correo, \n");
		sql.append("       direccion \n");
		sql.append("FROM   FIDELIZACION.CLIENTE \n");
		sql.append("WHERE  CLIENTE_ID  = ? \n");
		sql.append("AND    ESTADO    = 1 \n");
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigo);
		
		rs = pstm.executeQuery();

		BUbigeo ubigeo;
		if(rs.next()) {
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
			cliente.setDireccion(rs.getString("direccion"));
			cliente.setCorreo(rs.getString("correo"));
		}

		rs.close();
		pstm.close();
		conn.close();

		return cliente;
	}
	
	@Override
	public int almacenarCliente(Connection conn, BCliente cliente,
			BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximoNumeroCliente(conn) + 1;

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO FIDELIZACION.CLIENTE \n");
		sql
				.append("  (CLIENTE_ID,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,NUMERO_DOCUMENTO,TELEFONO, \n");
		sql
				.append("   TELEFONO_DOS,UBIGEO_ID,TIPO_CLIENTE_ID,USUARIO_CREACION,USUARIO_MODIFICACION,FECHA_CREACION, \n");
		sql.append("   FECHA_MODIFICACION,ESTADO,DIRECCION,CORREO) \n");
		sql.append("VALUES \n");
		sql.append(" (?,   ?,   ?,   ?,   ?,   ?, \n");
		sql.append("   ?,   ?,   0,   ?,   null,   sysdate, \n");
		sql.append("   null,   1,   ?,   ?) ");
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigo);
		pstm.setString(2, cliente.getNombre());
		pstm.setString(3, cliente.getApellidoPaterno());
		pstm.setString(4, cliente.getApellidoMaterno());
		pstm.setString(5, cliente.getNumeroDocumento());
		pstm.setString(6, cliente.getTelefono());
		pstm.setString(7, cliente.getTelefonoDos());
		pstm.setInt(8, cliente.getUbigeo().getCodigo());
		pstm.setString(9, usuario.getLogin());
		pstm.setString(10, cliente.getDireccion());
		pstm.setString(11, cliente.getCorreo());
		pstm.executeUpdate();

		pstm.close();
		
		return codigo;
	}
	public int obtenerMaximoNumeroCliente(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigoCliente = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.CLIENTE_ID),0) AS MAXIMO \n");
		sql.append("FROM   FIDELIZACION.CLIENTE PE");

		pstm = conn.prepareStatement(sql.toString());

		rs = pstm.executeQuery();

		if (rs.next()) {
			codigoCliente = (rs.getInt("MAXIMO"));

		}
		rs.close();
		pstm.close();

		return codigoCliente;
	}
	public String buscarDniRepetido(String numDocumento, int codigoCliente)throws SQLException{
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String nombreCliente = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cl.apellido_paterno \n");
		sql.append("              ||' ' \n");
		sql.append("              || cl.apellido_materno \n");
		sql.append("              ||', ' \n");
		sql.append("              || cl.nombre AS nombre \n");
		sql.append("FROM   fidelizacion.cliente cl \n");
		sql.append("WHERE  cl.numero_documento = ? \n");
		sql.append("AND    cl.estado           = 1 \n");
		sql.append("AND    cl.cliente_id       =?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, numDocumento);
		pstm.setInt(2, codigoCliente);
		rs = pstm.executeQuery();

		if (rs.next()) {
			nombreCliente = (rs.getString("nombre"));

		}
		rs.close();
		pstm.close();

		return nombreCliente;
	}
	public void almacenarClienteModificado(Connection conn, BCliente cliente,
			BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximoNumeroCliente(conn) + 1;

		StringBuffer sql = new StringBuffer();

		sql.append("update " +
				"cliente    set nombre = ?,        " +
				"apellido_paterno = ?,        " +
				"apellido_materno = ?,        " +
				"numero_documento = ?,        " +
				"telefono = ?,        " +
				"telefono_dos = ?,        " +
				"ubigeo_id = ?,        " +
				"usuario_modificacion = ?,        " +
				"fecha_modificacion = SYSDATE,        " +
				"direccion = ?,        " +
				"correo = ?  " +
				"where cliente_id = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, cliente.getNombre());
		pstm.setString(2, cliente.getApellidoPaterno());
		pstm.setString(3, cliente.getApellidoMaterno());
		pstm.setString(4, cliente.getNumeroDocumento());
		pstm.setString(5, cliente.getTelefono());
		pstm.setString(6, cliente.getTelefonoDos());
		pstm.setInt(7, cliente.getUbigeo().getCodigo());
		pstm.setString(8, usuario.getLogin());
		pstm.setString(9, cliente.getDireccion());
		pstm.setString(10, cliente.getCorreo());
		pstm.setInt(11, cliente.getCodigo());
		pstm.executeUpdate();

		pstm.close();
		
	}
}
