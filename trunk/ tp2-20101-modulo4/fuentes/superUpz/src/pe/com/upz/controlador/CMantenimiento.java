/**
 * Resumen.
 * Objeto                     : CMantenimiento.
 * Descripción                : Clase controladora para el modulo de mantenimiento. 
 * Fecha de Creación          : 31/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DTipoProducto;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.daoInterface.ITipoProducto;
import pe.com.upz.util.Lista;

import java.util. *;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
//import org.apache.commons.io.*;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase controladora para el modulo de mantenimiento.
 *
 */
public class CMantenimiento {

	
	/**
	 * Obtiene el listado de productos segun un filtro de busqueda.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @param valorAux valor para realizar la busqueda con filtro, tipo String.
	 * @return listadoProducto listado con los productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoProductos(boolean soloActivos, int filtro,String valorAux)throws SQLException{
		Lista listadoProducto=null;
		IProducto daoProducto = new DProducto();
		
		listadoProducto = daoProducto.obtenerListadoProductos(soloActivos,filtro,valorAux);
		
		return listadoProducto;
	}
	/**
	 * Obtiene el lsiatdo de tipos de productos.
	 * @return listadoTipoProducto listado con los tipos de productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoTipoProductos()throws SQLException{
		Lista listadoTipoProducto=null;
		ITipoProducto daoTipoProducto = new DTipoProducto();
		
		listadoTipoProducto = daoTipoProducto.obtenerListadoTipo();
		
		return listadoTipoProducto;
	}	
	
	/**
	 * Almacena el producto nuevo.
	 * @param producto producto a almacenar, tipo BProducto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param conn conexion a la BD, tipo Connection.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public int almacenarProducto(BProducto producto,BUsuario usuario, Connection conn)throws SQLException{
		IProducto dProducto = new DProducto();
		return dProducto.alamacenarProducto(producto,usuario, conn);
	}
	
}
