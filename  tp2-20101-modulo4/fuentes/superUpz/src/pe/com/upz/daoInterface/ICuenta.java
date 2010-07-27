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
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de cuenta.
 *
 */
public interface ICuenta {
	/**
	 * Obtiene el lsitado de cuentas.
	 * @param soloActivos indica obtener solo los activos, tipo boolean.
	 * @param filtro indica el filtro a aplicar, tipo int.
	 * @param valorAux valor del filtro 1, tipo String.
	 * @param valorAux2 valor del filtro 2, tipo String.
	 * @return listado de clientes de cuenta, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoCuenta(boolean soloActivos,
			int filtro, String valorAux, String valorAux2)
			throws SQLException;
	
	/**
	 * @param conn conexion a la BD, tipo Connection.
	 * @param cuenta  cuenta a almacenar, tipo BCuenta.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return  codigo de cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario)throws SQLException;
	
	/**
	 * Obtiene la maxima codificacion de cuenta.
	 * @param conn conexion a la BD, tipo Connection.
	 * @return codigo maximo de cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerMaximoNumeroCuenta(Connection conn)throws SQLException;
	
	/**
	 * Almacena cuenta nueva.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param cuenta cuenta a almacenar, tipo BCuenta.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo BSucursal.
	 * @return codigo de cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int almacenarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario, BSucursal sucursal)throws SQLException;

	/**
	 * Actualiza puntaje de cuenta
	 * @param conn conexion a la BD, tipo Connection.
	 * @param codcuenta codigo de cuenta, tipo int.
	 * @param cantidadPuntos cantidad de puntos de canje, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void actualizarPuntaje (Connection conn, int codcuenta, int cantidadPuntos,BUsuario usuario)throws SQLException;

	/**
	 * Obtiene el puntaje de una cuenta.
	 * @param codcuenta codigo de cuenta, tipo int.
	 * @return puntaje de la cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerPuntajeCuenta (int codcuenta)throws SQLException;
	
	/**
	 * Elimina la cuenta
	 * @param conn connexion a BD, tipo Connection.
	 * @param codCuenta codigo de la cuenta, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal a buscar, tipo BSucursal.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void actualizarTarjetaClienteTitular(Connection conn, BTarjetaFidelizacion tarjeta,
			BUsuario usuario, BSucursal sucursal, int codCuenta)throws SQLException;

	
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
	
	/**
	 * Elimina la cuenta
	 * @param conn connexion a BD, tipo Connection.
	 * @param codCuenta codigo de la cuenta, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal a buscar, tipo BSucursal.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void eliminarCuenta(Connection conn, int codCuenta, BUsuario usuario,BSucursal sucursal)throws SQLException;
	
	/**
	 * Obtiene listado de clientes adicionales de una cuenta.
	 * @param codigoCuenta codigo de la cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoCuentaAdicional(int codigoCuenta) throws SQLException;
	
	/**
	 * Almacena cuenta nueva.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param cuenta cuenta a almacenar, tipo BCuenta.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo BSucursal.
	 * @return codigo de cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void modificarCuenta(Connection conn, BCuenta cuenta,BUsuario usuario, BSucursal sucursal)throws SQLException;

}
