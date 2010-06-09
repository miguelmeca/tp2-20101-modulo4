/**
 * Resumen.
 * Objeto                     : CEquivalencia.
 * Descripción                : Clase controladora para el modulo de equivalencia. 
 * Fecha de Creación          : 20/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DEquivalencia;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.IEquivalencia;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de equivalencia. 
 *
 */
public class CEquivalencia {

	public Lista obtenerListadoProductosPuntaje(boolean soloActivos, int filtro)throws SQLException{
		Lista listadoProducto=null;
		IProducto daoProducto = new DProducto();
		
		listadoProducto = daoProducto.obtenerListadoProductosPuntaje(soloActivos,filtro);
		
		return listadoProducto;
	}
	public void alamcenarNuevaEquivalencia(BUsuario usuario,BEquivalencia equivalencia, Connection conn, int codProducto)throws SQLException{
		
		IEquivalencia dEquivalencia = new DEquivalencia();
		
		//primero elimino
		dEquivalencia.eliminarEquivalenciasActivasProducto(usuario, conn, codProducto);
		//ahora almaceno
		dEquivalencia.almacenarEquivalencia(usuario, conn, equivalencia, codProducto);
		
	}
	
}
