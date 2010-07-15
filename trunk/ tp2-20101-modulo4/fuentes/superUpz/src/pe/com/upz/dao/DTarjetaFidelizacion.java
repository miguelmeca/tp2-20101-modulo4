/**
 * Resumen.
 * Objeto                     : DTipoProducto.
 * Descripción                : Clase DAO de tarjeta de fidelizacion.
 * Fecha de Creación          : 31/05/2010.
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
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.daoInterface.ITarjetaFidelizacion;
import pe.com.upz.util.Bean;

/**
 * Clase DAO de tarjeta de fidelizacion.
 *
 */
public class DTarjetaFidelizacion implements ITarjetaFidelizacion {

	@Override
	public void almacenarTarjeta(Connection conn, BTarjetaFidelizacion tarjeta,
			BUsuario usuario, BSucursal sucursal, int codCuenta ) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm;



		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO   fidelizacion.TARJETA_FIDELIZACION \n");
		sql.append("       ( \n");
		sql.append("              CUENTA_ID           , \n");
		sql.append("              CLIENTE_ID          , \n");
		sql.append("              NUMERO              , \n");
		sql.append("              USUARIO_CREACION    , \n");
		sql.append("              FECHA_CREACION      , \n");
		sql.append("              USUARIO_MODIFICACION, \n");
		sql.append("              FECHA_MODIFICACION  , \n");
		sql.append("              ESTADO              , \n");
		sql.append("              TIPO_CLIENTE \n");
		sql.append("       ) \n");
		sql.append("       VALUES \n");
		sql.append("       ( \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              ?      , \n");
		sql.append("              SYSDATE, \n");
		sql.append("              NULL   , \n");
		sql.append("              NULL   , \n");
		sql.append("              1      , \n");
		sql.append("              ? \n");
		sql.append("       )");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, codCuenta);
		pstm.setInt(2,tarjeta.getCliente().getCodigo());
		pstm.setString(3, tarjeta.getNumero());
		pstm.setString(4, usuario.getLogin());
		pstm.setInt(5, tarjeta.getTipoCliente());
		
		pstm.executeUpdate();
	}
	public int obtenerMaximoNumeroTarjeta(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int codigoCliente = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NVL(MAX(PE.CUENTA_ID),0) AS MAXIMO \n");
		sql.append("FROM   fidelizacion.TARJETA_FIDELIZACION PE");

		pstm = conn.prepareStatement(sql.toString());

		rs = pstm.executeQuery();

		if (rs.next()) {
			codigoCliente = (rs.getInt("MAXIMO"));

		}
		rs.close();
		pstm.close();

		return codigoCliente;
	}
	
	public boolean buscarClienteFidelizado(BCliente cliente)throws SQLException {
		// TODO Auto-generated method stub
		boolean encontrado=false; 
		Connection conn = ConnectDS.obtenerConeccion();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT z.cuenta_id \n");
		sql.append("FROM   fidelizacion.tarjeta_fidelizacion z \n");
		sql.append("WHERE  z.cliente_id   = 1 \n");
		sql.append("AND    z.estado       = 1 \n");
		sql.append("AND    z.tipo_cliente = 1");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, cliente.getCodigo());

		rs = pstm.executeQuery();

		if (rs.next()) {
			encontrado=true;
		}
		rs.close();
		pstm.close();
		conn.close();
		
		return encontrado;
	}
	public void eliminarTarjetasCuenta(Connection conn, int codCuenta, BUsuario usuario)throws SQLException{
		PreparedStatement pstm;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tarjeta_fidelizacion \n");
		sql.append("SET    usuario_modificacion = ?      , \n");
		sql.append("       fecha_modificacion   = SYSDATE, \n");
		sql.append("       estado               = 1 \n");
		sql.append("WHERE  cuenta_id            = ?");

		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, usuario.getLogin());
		pstm.setInt(2, codCuenta);
		
		pstm.executeUpdate();
	}
	public boolean buscarClienteFidelizado(BCliente cliente, BCuenta cuenta)throws SQLException {
		// TODO Auto-generated method stub
		boolean encontrado=false; 
		Connection conn = ConnectDS.obtenerConeccion();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT z.cuenta_id \n");
		sql.append("FROM   fidelizacion.tarjeta_fidelizacion z \n");
		sql.append("WHERE  z.cliente_id   = ? \n");
		sql.append("AND    z.cuenta_id = ?");
		sql.append("AND    z.estado       = 1 \n");
		
		pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, cliente.getCodigo());
		pstm.setInt(2, cuenta.getCodigo());

		rs = pstm.executeQuery();

		if (rs.next()) {
			encontrado=true;
		}
		rs.close();
		pstm.close();
		conn.close();
		
		return encontrado;
	}
}
