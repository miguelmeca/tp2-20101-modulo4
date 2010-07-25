/**
 * Resumen.
 * Objeto                     : CMantenimiento.
 * Descripci�n                : Clase controladora para el modulo de mantenimiento. 
 * Fecha de Creaci�n          : 31/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DImagenProducto;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DTipoProducto;
import pe.com.upz.daoInterface.IImagenProducto;
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
	 * @param bSucursal sucursal de creacion, tipo BSucursal.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public int almacenarProducto(BProducto producto,BUsuario usuario, Connection conn, BSucursal bSucursal)throws SQLException{
		int codigoGenerado;
		IProducto dProducto = new DProducto();
		codigoGenerado = dProducto.alamacenarProducto(producto,usuario, conn);
		producto.setCodigo(codigoGenerado);
		dProducto.crearProductoSucursal(producto, bSucursal, conn, usuario);
		return codigoGenerado;
	}
	//gonza
	/**
	 * Elimina logicamente el producto.
	 * @param producto producto, tipo BProducto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param conn conexion a la BD, tipo Connection.
	 * @param bSucursal sucursal de eliminacion, tipo BSucursal.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public void eliminarProducto(int producto,BUsuario usuario, Connection conn, BSucursal bSucursal)throws SQLException{
		
		IProducto dProducto = new DProducto();
		dProducto.eliminarProducto(producto,usuario, conn);
		dProducto.eliminarProductoSucursal(producto,usuario, conn);
		
	}	
	/**
	 * Obtiene un producto por codigo.
	 * @param codigo codigo del producto, tipo int.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public BProducto obtenerProductos(int codigo) throws SQLException{
		IProducto dProducto = new DProducto();
		return dProducto.obtenerProductos(codigo);
	}
	/**
	 * Almacena cambios de un producto.
	 * @param producto producto a actualizar, tipo BProducto.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param conn conexion a la BD, tipo Connection.
	 * @throws SQLException captura excepciones tipo SQL.
	 */	
	public void guardarCambiosProducto(BProducto producto, BUsuario usuario, Connection conn)throws SQLException{
		IProducto dProducto = new DProducto();
		dProducto.guardarCambiosProducto(producto, usuario, conn);
	}
	
	/**
	 * Obtiene el listado de imagenes de galeria.
	 * @return listadoImagenes listado de imagenes de galeria, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoImagenes()throws SQLException{
		Lista listadoImagenes=null;
		IImagenProducto daoTipoProducto = new DImagenProducto();
		
		listadoImagenes = daoTipoProducto.obtenerListadoImagenes();
		
		return listadoImagenes;
	}	 
	//gonza
}
