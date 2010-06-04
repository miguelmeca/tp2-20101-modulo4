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
import pe.com.upz.dao.DRol;
import pe.com.upz.dao.DUsuario;
import pe.com.upz.daoInterface.IRol;
import pe.com.upz.util.Parametros;

/**
 * Clase controladora para el modulo de seguridad. 
 *
 */
public class CSeguridad {

	/**
	 * Valida que exista el usario.
	 * @param sLogin login del susario, tipo String.
	 * @param sClave clave del usuario, tipo String.
	 * @return usuario usuario del sistema, tipo BUsuario.
	 * @throws SQLException captura las excepciones tipo SQL.
	 */
	public BUsuario validarusuario(String sLogin, String sClave)throws SQLException{
		BUsuario usuario;
		DUsuario usuarioDao = new DUsuario();
		usuario = usuarioDao.validarUsuario(sLogin, sClave);
		
		return usuario;
	}
	
	/**
	 * Retorna valor o verdadero/falso segun sea un usuario tipo jefe de fidelizacion.
	 * @param usuario usuario a ser validado.
	 * @return indica si es o no jefe de fidelizacion
	 * @throws SQLException
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
	
}
