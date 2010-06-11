/**
 * Resumen.
 * Objeto                     : CAbastecimiento.
 * Descripción                : Clase controladora para el modulo de seguridad. 
 * Fecha de Creación          : 31/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.SQLException;

import pe.com.upz.bean.BRol;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DOpcion;
import pe.com.upz.dao.DRol;
import pe.com.upz.dao.DSucursal;
import pe.com.upz.dao.DUsuario;
import pe.com.upz.daoInterface.IOpcion;
import pe.com.upz.daoInterface.IRol;
import pe.com.upz.daoInterface.ISucursal;
import pe.com.upz.daoInterface.IUsuario;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Clase controladora para el modulo de seguridad. 
 *
 */
public class CSeguridad {

	
	/**
	 * Valida que existe el login del usuario.
	 * @param sLogin login del usuario, tipo String. 
	 * @return retorna true o false segun exista el login ingresado, tipo boolean.
	 */
	public boolean validarExisteUsuario (String sLogin){
		IUsuario usuarioDao = new DUsuario();
		
		return usuarioDao.validarExisteUsuario(sLogin);
	}
	
	/**
	 * Valida que exista el usario con su clave y login.
	 * @param sLogin login del usuario, tipo String.
	 * @param sClave clave del usuario, tipo String.
	 * @return usuario usuario del sistema, tipo BUsuario.
	 * @throws SQLException captura las excepciones tipo SQL.
	 */
	public BUsuario validarUsuario(String sLogin, String sClave)throws SQLException{
		BUsuario usuario;
		DUsuario usuarioDao = new DUsuario();
		usuario = usuarioDao.validarUsuario(sLogin, sClave);
		
		return usuario;
	}
	
	/**
	 * Retorna valor o verdadero/falso segun sea un usuario tipo jefe de fidelizacion.
	 * @param usuario usuario a ser validado, tipo BUsuario.
	 * @return indica si es o no jefe de fidelizacion, tipo boolean
	 * @throws SQLException captura las excepciones tipo SQL.
	 */
	public boolean esUsuarioJefeFidelizacion(BUsuario usuario)throws SQLException{
		
		if (usuario != null) {

			IRol rol = new DRol();

			// obteniendo los roles
			rol.obtenerRol(usuario);
			
			//VALIDAR QUE SEA JEFE DE FIDELIZACIÓN
			for (int i=0;i< usuario.getListaRol().getTamanio();i++){
				BRol rolUsuario = (BRol)usuario.getListaRol().getElemento(i);
				if(rolUsuario.getCodigoRol() == Parametros.CODIGO_JEFE_FIDELIZACION){
					return  true;
				}
			}
			return false;
		} else {
			return false;
		}
		
	}

	/**
	 * Coloca los roles a un usuario.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura las excepciones tipo SQL.
	 */
	public void colocarRolesUsuario(BUsuario usuario)throws SQLException{
		IRol rol = new DRol();

		// obteniendo los roles
		rol.obtenerRol(usuario);
		
	}
	
	/**
	 * Obtiene las opciones del menu para un determinado usuario.
	 * @param usuario usuarioa a colocar opciones del menu, tipo BUsuario.
	 * @return lista con la opciones del menu, tipo Lista.
	 * @throws Exception captura las excepciones.
	 */
	public Lista obtenerOpcionesMenu(BUsuario usuario) throws Exception{
		IOpcion opcion = new DOpcion();
		
		IUsuario usuarioDao = new DUsuario();
		// obteniendo las opciones
		for (int i = 0; i < usuario.getListaRol().getTamanio(); i++) {
			opcion.obtenerOpcionesUsuario((BRol) usuario.getListaRol()
					.getElemento(i));

		}
		// obteniendo el menu
		return usuarioDao.obtenerMenu(usuario);
	}
	
	public Lista obtenerSucursalesLogeo()throws Exception{
		ISucursal dSucursal = new DSucursal();
		
		return dSucursal.obtenerListadoSucursales();
	}
}
