package pe.com.upz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.util.Lista;

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
		sql.append("       CL.NOMBRE           AS NOMBRE, \n");
		sql.append("       CL.APELLIDO_PATERNO AS APELLIDO_PATERNO, \n");
		sql.append("       CL.APELLIDO_MATERNO AS APELLIDO_MATERNO, \n");
		sql.append("       CL.NUMERO_DOCUMENTO AS NUMERO_DOCUMENTO, \n");
		sql.append("       TA.NUMERO AS NUM_TARJETA\n");
		sql.append("FROM   FIDELIZACION.CUENTA CU              , \n");
		sql.append("       FIDELIZACION.TARJETA_FIDELIZACION TA, \n");
		sql.append("       FIDELIZACION.CLIENTE CL \n");
		sql.append("WHERE  TA.CUENTA_ID    = CU.CUENTA_ID \n");
		sql.append("AND    TA.CLIENTE_ID   = CL.CLIENTE_ID \n");
		sql.append("AND    CU.ESTADO       = 1 \n");
		sql.append("AND    TA.TIPO_CLIENTE    = 1 \n");
		if(soloActivos){
			sql.append("AND    TA.ESTADO       = 1 \n");
		}
		if (filtro == 1) {
			sql.append("AND cl.numero_documento = ? \n");
		}else if (filtro == 2) {
			sql.append("AND ta.numero = ? \n");
		}
		sql.append("ORDER BY cl.APELLIDO_PATERNO, cl.APELLIDO_MATERNO, cl.NOMBRE");

		pstm = conn.prepareStatement(sql.toString());
		if (filtro == 1) {
			pstm.setString(1, valorAux);
		} else if (filtro == 2) {
			pstm.setString(1, valorAux2);
		} 
		
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
			cliente.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
			cliente.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			cliente.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			cliente.setNombre(rs.getString("NOMBRE"));
			tarjeta = new BTarjetaFidelizacion();
			tarjeta.setNumero(rs.getString("NUM_TARJETA"));
			tarjeta.setCliente(cliente);
			listaTarjeta = new Lista();
			listaTarjeta.setElemento(tarjeta);
			
			lista.setElemento(cliente);
		}

		rs.close();
		pstm.close();
		conn.close();

		return lista;
	}

	@Override
	public int obtenerMaximoNumeroCuenta(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
