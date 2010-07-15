/**
 * Resumen.
 * Objeto                     : DCuenta.
 * Descripción                : Clase DAO de cuenta de programa.
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
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.util.Lista;

/**
 * Clase DAO de cuenta de programa.
 *
 */
public class DCuenta implements ICuenta {

	@Override
	public int almacenarCuenta(Connection conn, BCuenta cuenta, BUsuario usuario)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lista obtenerListadoCuenta(boolean soloActivos, int filtro,
			String valorAux, String valorAux2) throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BCuenta cuenta;
		Lista lista = new Lista();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CU.CUENTA_ID        AS CUENTA_ID, \n");
		sql.append("       CU.PUNTOS_ACUMULADOS AS PUNTOS_ACUMULADOS, \n");
		sql.append("       CU.PUNTOS_VENCIDOS  AS PUNTOS_VENCIDOS, \n");
		sql.append("       CU.PUNTOS_CANJEADOS AS UNTOS_CANJEADOS, \n");
		sql.append("       CU.ESTADO           AS ESTADO, \n");
		sql.append("       CL.CLIENTE_ID          AS CLIENTE_ID, \n");
		sql.append("       CL.NOMBRE           AS NOMBRE, \n");
		sql.append("       CL.APELLIDO_PATERNO AS APELLIDO_PATERNO, \n");
		sql.append("       CL.APELLIDO_MATERNO AS APELLIDO_MATERNO, \n");
		sql.append("       CL.NUMERO_DOCUMENTO AS NUMERO_DOCUMENTO, \n");
		sql.append("       TA.TIPO_CLIENTE AS TIPO_CLIENTE, \n");
		sql.append("       TA.NUMERO AS NUM_TARJETA\n");
		sql.append("FROM   FIDELIZACION.CUENTA CU              , \n");
		sql.append("       FIDELIZACION.TARJETA_FIDELIZACION TA, \n");
		sql.append("       FIDELIZACION.CLIENTE CL \n");
		sql.append("WHERE  TA.CUENTA_ID    = CU.CUENTA_ID \n");
		sql.append("AND    TA.CLIENTE_ID   = CL.CLIENTE_ID \n");
		sql.append("AND    CU.ESTADO       = 1 \n");
		sql.append("AND    TA.TIPO_CLIENTE    = 1 \n");
		if (soloActivos) {
			sql.append("AND    TA.ESTADO       = 1 \n");
		}
		if (filtro == 1) {
			sql.append("AND cl.numero_documento = ? \n");
		} else if (filtro == 2) {
			sql.append("AND ta.numero = ? \n");
		}
		sql
				.append("ORDER BY cl.APELLIDO_PATERNO, cl.APELLIDO_MATERNO, cl.NOMBRE");
		System.out.println(sql.toString());
		pstm = conn.prepareStatement(sql.toString());
		if (filtro == 1) {
			pstm.setString(1, valorAux);
		} else if (filtro == 2) {
			pstm.setString(1, valorAux2);
		}
		System.out.println(valorAux2);
		rs = pstm.executeQuery();

		BCliente cliente;
		BTarjetaFidelizacion tarjeta;
		Lista listaTarjeta;
		while (rs.next()) {
			cuenta = new BCuenta();
			cuenta.setCodigo(rs.getInt("CUENTA_ID"));
			cuenta.setPuntosAcumulados(rs.getInt("PUNTOS_ACUMULADOS"));
			cuenta.setPuntosCanjeados(rs.getInt("UNTOS_CANJEADOS"));
			cuenta.setPuntosVencidos(rs.getInt("PUNTOS_VENCIDOS"));
			cuenta.setEstado(rs.getInt("ESTADO"));
			cliente = new BCliente();
			cliente.setCodigo(rs.getInt("CLIENTE_ID"));
			cliente.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
			cliente.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			cliente.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			cliente.setNombre(rs.getString("NOMBRE"));
			tarjeta = new BTarjetaFidelizacion();
			tarjeta.setNumero(rs.getString("NUM_TARJETA"));
			tarjeta.setTipoCliente(rs.getInt("TIPO_CLIENTE"));
			tarjeta.setCliente(cliente);
			listaTarjeta = new Lista();
			listaTarjeta.setElemento(tarjeta);
			cuenta.setTarjeta(listaTarjeta);
			lista.setElemento(cuenta);
		}

		rs.close();
		pstm.close();
		conn.close();

		return lista;
	}

@Override
	public int almacenarCuenta(Connection conn, BCuenta cuenta,
			BUsuario usuario, BSucursal sucursal) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		int codigo = obtenerMaximoNumeroCuenta(conn) + 1;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   fidelizacion.CUENTA \n");
		sql.append("       ( \n");
		sql.append("              CUENTA_ID           , \n");
		sql.append("              PUNTOS_ACUMULADOS   , \n");
		sql.append("              PUNTOS_VENCIDOS     , \n");
		sql.append("              PUNTOS_CANJEADOS    , \n");
		sql.append("              FECHA_CREACION      , \n");
		sql.append("              FECHA_MODIFICACION  , \n");
		sql.append("              ESTADO              , \n");
		sql.append("              USUARIO_CREACION    , \n");
		sql.append("              USUARIO_MODIFICACION, \n");
		sql.append("              SUCURSAL_CREACION   , \n");
		sql.append("              SUCURSAL_BAJA \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              1000   , \n");
		sql.append("              0      , \n");
		sql.append("              0      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              NULL   , \n");
		sql.append("              1      , \n");
		sql.append("              ?      , \n");
		sql.append("              NULL   , \n");
		sql.append("              ?      , \n");
		sql.append("              NULL \n");
		sql.append("       )");
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigo);
		pstm.setString(2, usuario.getLogin());
		pstm.setInt(3, sucursal.getCodigo());
		
		pstm.executeUpdate();

		return codigo;
	}

	public int obtenerMaximoNumeroCuenta(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigoCliente = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.CUENTA_ID),0) AS MAXIMO \n");
		sql.append("FROM   fidelizacion.CUENTA PE");

		pstm = conn.prepareStatement(sql.toString());

		rs = pstm.executeQuery();

		if (rs.next()) {
			codigoCliente = (rs.getInt("MAXIMO"));

		}
		rs.close();
		pstm.close();

		return codigoCliente;
	}

	@Override
	public void actualizarPuntaje(Connection conn, int codcuenta, int cantidadPuntos,
			BUsuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE fidelizacion.cuenta cu \n");
		sql.append("SET    cu.puntos_canjeados   = (NVL(cu.puntos_canjeados,0) + ? ), \n");
		sql.append("       cu.fecha_modificacion = SYSDATE                          , \n");
		sql.append("       cu.usuario_modificacion = ? \n");
		sql.append("WHERE  cu.cuenta_id = ?");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, cantidadPuntos);
		pstm.setString(2, usuario.getLogin());
		pstm.setInt(3, codcuenta);
		
		pstm.executeUpdate();
		
		pstm.close();
	}

	@Override
	public int obtenerPuntajeCuenta(int codcuenta) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int puntaje = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT (cu.puntos_acumulados - cu.puntos_canjeados) AS puntaje \n");
		sql.append("FROM   fidelizacion.cuenta cu \n");
		sql.append("WHERE  cu.cuenta_id = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codcuenta);
		rs = pstm.executeQuery();

		if (rs.next()) {
			puntaje = (rs.getInt("puntaje"));

		}
		rs.close();
		pstm.close();
		conn.close();
		return puntaje;
	}

	@Override
	public int obtenerCantidadCuentasCreadasMes(BSucursal sucursal,int mes) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int cantidad = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT('X') AS cantidad \n");
		sql.append("FROM   fidelizacion.cuenta cu \n");
		sql.append("WHERE  cu.sucursal_creacion                       = ? \n");
		sql.append("AND    to_number(TO_CHAR(cu.fecha_creacion,'MM')) = ? \n");
		sql.append("AND    to_number(TO_CHAR(cu.fecha_creacion,'YY')) = to_number(TO_CHAR(SYSDATE,'YY'))");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, sucursal.getCodigo());
		pstm.setInt(2, mes);
		rs = pstm.executeQuery();

		if (rs.next()) {
			cantidad = (rs.getInt("cantidad"));

		}
		rs.close();
		pstm.close();
		conn.close();
		return cantidad;
	}

	@Override
	public int obtenerCantidadCuentasBajaMes(BSucursal sucursal, int mes)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectDS.obtenerConeccion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int cantidad = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT('X') AS cantidad \n");
		sql.append("FROM   fidelizacion.cuenta cu \n");
		sql.append("WHERE  cu.sucursal_baja                       = ? \n");
		sql.append("AND    to_number(TO_CHAR(cu.fecha_modificacion,'MM')) = ? \n");
		sql.append("AND    to_number(TO_CHAR(cu.fecha_modificacion,'YY')) = to_number(TO_CHAR(SYSDATE,'YY'))");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, sucursal.getCodigo());
		pstm.setInt(2, mes);
		rs = pstm.executeQuery();

		if (rs.next()) {
			cantidad = (rs.getInt("cantidad"));

		}
		rs.close();
		pstm.close();
		conn.close();
		return cantidad;
	}
	
	public void eliminarCuenta(Connection conn, int codCuenta, BUsuario usuario,BSucursal sucursal)throws SQLException{
		PreparedStatement pstm;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE cuenta \n");
		sql.append("SET    fecha_modificacion   = SYSDATE, \n");
		sql.append("       estado               = 0      , \n");
		sql.append("       sucursal_baja = ?      , \n");
		sql.append("       usuario_modificacion = ? \n");
		sql.append("WHERE  cuenta_id            = ?");

		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, sucursal.getCodigo());
		pstm.setString(2, usuario.getLogin());
		pstm.setInt(3, codCuenta);
		
		pstm.executeUpdate();
	}
	public Lista obtenerListadoCuentaAdicional(int codigoCuenta) throws SQLException {
		Connection conn = ConnectDS.obtenerConeccion();
		BCuenta cuenta;
		Lista lista = new Lista();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CU.CUENTA_ID        AS CUENTA_ID, \n");
		sql.append("       CU.PUNTOS_ACUMULADOS AS PUNTOS_ACUMULADOS, \n");
		sql.append("       CU.PUNTOS_VENCIDOS  AS PUNTOS_VENCIDOS, \n");
		sql.append("       CU.PUNTOS_CANJEADOS AS UNTOS_CANJEADOS, \n");
		sql.append("       CU.ESTADO           AS ESTADO, \n");
		sql.append("       CL.CLIENTE_ID           AS CLIENTE_ID, \n");
		sql.append("       CL.NOMBRE           AS NOMBRE, \n");
		sql.append("       CL.APELLIDO_PATERNO AS APELLIDO_PATERNO, \n");
		sql.append("       CL.APELLIDO_MATERNO AS APELLIDO_MATERNO, \n");
		sql.append("       CL.NUMERO_DOCUMENTO AS NUMERO_DOCUMENTO, \n");
		sql.append("       TA.TIPO_CLIENTE AS TIPO_CLIENTE, \n");
		sql.append("       TA.NUMERO AS NUM_TARJETA\n");
		sql.append("FROM   FIDELIZACION.CUENTA CU              , \n");
		sql.append("       FIDELIZACION.TARJETA_FIDELIZACION TA, \n");
		sql.append("       FIDELIZACION.CLIENTE CL \n");
		sql.append("WHERE  TA.CUENTA_ID    = CU.CUENTA_ID \n");
		sql.append("AND    TA.CLIENTE_ID   = CL.CLIENTE_ID \n");
		sql.append("AND    CU.ESTADO       = 1 \n");
		sql.append("AND    TA.TIPO_CLIENTE    = 2 \n");
		sql.append("AND    TA.ESTADO       = 1 \n");
		sql.append("AND    CU.CUENTA_ID = ? \n");
		sql.append("ORDER BY cl.APELLIDO_PATERNO, cl.APELLIDO_MATERNO, cl.NOMBRE");
		System.out.println(sql.toString());
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codigoCuenta);
		rs = pstm.executeQuery();

		BCliente cliente;
		BTarjetaFidelizacion tarjeta;
		Lista listaTarjeta;
		while (rs.next()) {
			cuenta = new BCuenta();
			cuenta.setCodigo(rs.getInt("CUENTA_ID"));
			cuenta.setPuntosAcumulados(rs.getInt("PUNTOS_ACUMULADOS"));
			cuenta.setPuntosCanjeados(rs.getInt("UNTOS_CANJEADOS"));
			cuenta.setPuntosVencidos(rs.getInt("PUNTOS_VENCIDOS"));
			cuenta.setEstado(rs.getInt("ESTADO"));
			cliente = new BCliente();
			cliente.setCodigo(rs.getInt("CLIENTE_ID"));
			cliente.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
			cliente.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			cliente.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			cliente.setNombre(rs.getString("NOMBRE"));
			tarjeta = new BTarjetaFidelizacion();
			tarjeta.setNumero(rs.getString("NUM_TARJETA"));
			tarjeta.setTipoCliente(rs.getInt("TIPO_CLIENTE"));
			tarjeta.setCliente(cliente);
			//listaTarjeta = new Lista();
			//listaTarjeta.setElemento(tarjeta);
			//cuenta.setTarjeta(listaTarjeta);
			lista.setElemento(tarjeta);
		}

		rs.close();
		pstm.close();
		conn.close();

		return lista;
	}
	public void modificarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario, BSucursal sucursal)throws SQLException{
		
	}

	@Override
	public void actualizarTarjetaClienteTitular(Connection conn, BTarjetaFidelizacion tarjeta,
			BUsuario usuario, BSucursal sucursal, int codCuenta)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TARJETA_FIDELIZACION \n");
		sql.append("SET    NUMERO               = ?, \n");
		sql.append("       USUARIO_MODIFICACION = ?, \n");
		sql.append("       FECHA_MODIFICACION   = SYSDATE \n");
		sql.append("WHERE  CUENTA_ID            = ? \n");
		sql.append("AND    CLIENTE_ID           = ?");
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, tarjeta.getNumero());
		pstm.setString(2, usuario.getLogin());
		pstm.setInt(3, codCuenta);
		pstm.setInt(4, tarjeta.getCliente().getCodigo());
		
		pstm.executeUpdate();

	}

}
