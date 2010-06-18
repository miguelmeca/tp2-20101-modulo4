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
	
	public abstract int almacenarCliente(Connection conn, BCliente cliente,BUsuario usuario)throws SQLException;
	
	public abstract int obtenerMaximoNumeroCliente(Connection conn)throws SQLException;
}
