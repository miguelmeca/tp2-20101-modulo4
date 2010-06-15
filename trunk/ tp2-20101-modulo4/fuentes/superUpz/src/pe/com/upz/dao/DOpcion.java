/**
 * Resumen.
 * Objeto                     : DOpcion.
 * Descripción                : Clase DAO de opciones del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.dao;

import java.sql.SQLException;

import pe.com.upz.bean.BRol;
import pe.com.upz.daoInterface.IOpcion;
import pe.com.upz.util.Lista;

public class DOpcion implements IOpcion {

	@Override
	public void obtenerOpcionesUsuario(BRol rol) throws SQLException {
		// TODO Auto-generated method stub
		Lista listaOpciones = new Lista();
		
		
		
		rol.setListaopciones(listaOpciones);
	}

	@Override
	public Lista obtenerMenu() throws SQLException {
		// TODO Auto-generated method stub
		Lista listaOpciones = new Lista();
		
		return listaOpciones;
	}

}
