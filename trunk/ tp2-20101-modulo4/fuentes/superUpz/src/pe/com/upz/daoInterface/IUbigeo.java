/**
 * Resumen.
 * Objeto                     : IUbigeo.
 * Descripción                : Clase Interface para DAO de ubigeos del sistema.
 * Fecha de Creación          : 26/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.daoInterface;

import java.sql.SQLException;

import pe.com.upz.bean.BUbigeo;
import pe.com.upz.util.Lista;

/**
 * Clase Interface para DAO de ubigeos del sistema.
 *
 */
public interface IUbigeo {

	/**
	 * Listado de ubigeos del sistema.
	 * @param departamento departamento a buscar, tipo int.
	 * @param provincia provincia a buscar, tipo int.
	 * @param distrito distrito a buscar, tipo int.
	 * @return listado de ubigeos, tipo Lista.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract Lista obtenerListadoUbigeo(String departamento, String provincia, String distrito)throws SQLException;

	/**
	 * Obtiene listado de departamentos.
	 * @return listado de ubigeo, tipo Lista.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract Lista obtenerDepartamentos()throws SQLException;
	
	/**
	 * Obtiene listado de provincias de departamento.
	 * @param departamento departamento a buscar, tipo int.
	 * @return listado de ubigeo, tipo Lista.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract Lista obtenerProvinciasDeDepartamento(String departamento)throws SQLException;
	
	/**
	 * obtiene listado de distripos de provincias.
	 * @param departamento departamento a buscar, tipo int.
	 * @param provincia provincia a buscar, tipo int.
	 * @return listado de ubigeo, tipo Lista.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract Lista obtenerDistritosDeprovincia(String departamento,String provincia)throws SQLException;

	/**
	 * Obtiene codigo del ubigeo
	 * @param departamento departamento a buscar, tipo int.
	 * @param provincia provincia a buscar, tipo int.
	 * @param distrito distrito a buscar, tipo int.
	 * @return codigo del ubigeo, tipo int.
	 * @throws SQLException captura las excepciones del sistema.
	 */
	public abstract int obtenerCodigoUbigeo(String departamento,String provincia, String distrito)throws SQLException;
}
