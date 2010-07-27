/**
 * Resumen.
 * Objeto                     : ICliente.
 * Descripción                : Clase Interface para DAO de clientes del sistema.
 * Fecha de Creación          : 24/05/2010.
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

/**
 * Clase Interface para DAO de clientes del sistema.
 * 
 */
public interface ICliente {
	/**
	 * obtiene un listado con los productos registrados.
	 * 
	 * @param soloActivos
	 *            indica si solo se obtiene los productos activos.
	 * @param filtro
	 *            opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo,
	 *            tipo int.
	 * @param valorAux
	 *            valor para realizar la busqueda con filtro, tipo String.
	 * @return listado de productos
	 * @throws SQLException
	 *             captura excepciones tipo SQL.
	 */
	public abstract Lista obtenerListadoClientes(boolean soloActivos,
			int filtro, String valorAux, String valorAux2, String valorAux3)
			throws SQLException;
	
	/**
	 * Almacena un cliente nuevo.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param cliente cliente a almacenar, tipo BCliente.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return codigo de cliente almacenado, tipo int.
	 * @throws SQLException
	 */
	public abstract int almacenarCliente(Connection conn, BCliente cliente,BUsuario usuario)throws SQLException;
	
	/**
	 * Obtiene la maxima codificacion de cuenta.
	 * @param conn conexion a la BD, tipo Connection.
	 * @return codigo maximo de cuenta, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract int obtenerMaximoNumeroCliente(Connection conn)throws SQLException;

	/**
	 * Busca dni repetidos
	 * @param numDocumento
	 * @param codigoCliente
	 * @return
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract String buscarDniRepetido(String numDocumento, int codigoCliente)throws SQLException;

	/**
	 * obtiene un cliente
	 * @param codigo  coigo a buscar, tipo int.
	 * @return cliente, tipo BCliente
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract BCliente obtenerCliente(int codigo)throws SQLException;
	/**
	 * @param conn
	 * @param cliente
	 * @param usuario
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void almacenarClienteModificado(Connection conn, BCliente cliente,
			BUsuario usuario) throws SQLException;
	
	/**
	 * Elimina un cliente
	 * @param conn connexión a la BD, tipo Connection
	 * @param cliente cliente a eliminar, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public abstract void eliminarCliente(Connection conn, int cliente,
			BUsuario usuario) throws SQLException;
}
