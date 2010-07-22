/**
 * Resumen.
 * Objeto                     : ITarjetaFidelizacion.
 * Descripción                : Clase Interface para DAO de Tarjeta de fidelizacion.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUsuario;

/**
 * Clase Interface para DAO de Tarjeta de fidelizacion.
 *
 */
public interface ITarjetaFidelizacion {
	
	/**
	 * almacena la tarjeta de fidelizacion.
	 * @param conn connexion a BD, tipo Connection.
	 * @param tarjeta tarjeta de fideliacion, tipo BTarjetaFidelizacion.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo  BSucursal.
	 * @param codCuenta codigo de la cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void almacenarTarjeta(Connection conn, BTarjetaFidelizacion tarjeta,
			BUsuario usuario, BSucursal sucursal, int codCuenta)throws SQLException;
	
	/**
	 * Busca cliente de cuenta.
	 * @param cliente cliente a buscar, tipo BCliente.
	 * @return indica si se encontro, tipo boolean.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract boolean buscarClienteFidelizado(BCliente cliente)throws SQLException;

	/**
	 * Elimina las tarjetas de una cuenta
	 * @param conn connexion a BD, tipo Connection.
	 * @param codCuenta codigo de la cuenta, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void eliminarTarjetasCuenta(Connection conn, int codCuenta, BUsuario usuario)throws SQLException;
	
	public abstract boolean buscarClienteFidelizado(BCliente cliente, BCuenta cuenta)throws SQLException;
	
	public abstract String buscarTarjetaRepetido(String numTarjeta)throws SQLException;
	public abstract BTarjetaFidelizacion buscarCuentaExistente(int codigoCliente)throws SQLException;
}
