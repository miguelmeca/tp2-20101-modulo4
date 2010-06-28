/**
 * Resumen.
 * Objeto                     : ICuenta.
 * Descripción                : Clase Interface para DAO de cuenta.
 * Fecha de Creación          : 25/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Lista;

public interface ICuenta {
	public abstract Lista obtenerListadoCuenta(boolean soloActivos,
			int filtro, String valorAux, String valorAux2)
			throws SQLException;
	
	/**
	 * @param conn
	 * @param cuenta
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario)throws SQLException;
	
	/**
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public abstract int obtenerMaximoNumeroCuenta(Connection conn)throws SQLException;
	
	/**
	 * @param conn
	 * @param cuenta
	 * @param usuario
	 * @param sucursal
	 * @return
	 * @throws SQLException
	 */
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario, BSucursal sucursal)throws SQLException;

	/**
	 * @param conn
	 * @param codcuenta
	 * @param cantidadPuntos
	 * @param usuario
	 * @throws SQLException
	 */
	public abstract void actualizarPuntaje (Connection conn, int codcuenta, int cantidadPuntos,BUsuario usuario)throws SQLException;

	/**
	 * @param codcuenta codigo de cuenta, tipo int.
	 * @return 
	 * @throws SQLException
	 */
	public abstract int obtenerPuntajeCuenta (int codcuenta)throws SQLException;
	
	/**
	 * Busca cuentas abiertas en una sucursal y un mes.
	 * @param sucursal sucursal a buscar, tipo BSucursal.
	 * @param mes mas a buscar, tipo int.
	 * @return cantidad encontrada, tipo int
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerCantidadCuentasCreadasMes(BSucursal sucursal,int mes)throws SQLException;
	
	/**
	 * Busca cuentas dadas de baja en una sucursal y un mes.
	 * @param sucursal sucursal a buscar, tipo BSucursal.
	 * @param mes mas a buscar, tipo int.
	 * @return cantidad encontrada, tipo int
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerCantidadCuentasBajaMes(BSucursal sucursal,int mes)throws SQLException;

}
