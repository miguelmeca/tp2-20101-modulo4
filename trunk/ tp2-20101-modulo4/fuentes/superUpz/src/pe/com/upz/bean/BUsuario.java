/**
 * Resumen.
 * Objeto                     : BUsuario.
 * Descripción                : Bean de usuarios del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

/**
 * @author Gonzalo
 *
 */
/**
 * @author DMJAZABACHE
 *
 */
public class BUsuario extends Bean{

	/**
	 * codigo del usuario.
	 */
	private int codigo;
	/**
	 * login del usuario.
	 */
	private String login;
	/**
	 * clave del usurio.
	 */
	private String password;
	/**
	 * nombre del usuario.
	 */
	private String nombre;
	/**
	 * apellido paterno dl usuario.
	 */
	private String apellidoPaterno;
	/**
	 * apellido materno dl usuario.
	 */
	private String apellidoMaterno;
	/**
	 * Listado de roles del usuario.
	 */
	private Lista listaRol;
	
	
	/**
	 * Retorna login del usuario.
	 * @return login del usuario.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Setea login del usuario.
	 * @param login login del usuario.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * Retorna clave del usuario.
	 * @return clave del usuario.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setea clave del usuario.
	 * @param password clave del usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Retorna nombre del usuario.
	 * @return nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setea nombre del usuario.
	 * @param nombre nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna apellido paterno del usuario.
	 * @return apellido paterno del usuario.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * Setea apellido paterno del usuario.
	 * @param apellidoPaterno apellido paterno del usuario.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * Retorna apellido materno del usuario.
	 * @return apellido materno del usuario.
	 */ 
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Setea apellido materno del usuario.
	 * @param apellidoMaterno apellido materno del usuario.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * Retorna listado de roles.
	 * @return listado de roles.
	 */
	public Lista getListaRol() {
		return listaRol;
	}

	/**
	 * lSetea istado de roles.
	 * @param listaRol listado de roles.
	 */
	public void setListaRol(Lista listaRol) {
		this.listaRol = listaRol;
	}

	/**
	 * Retorna codigo del usuario.
	 * @return codigo del usuario.
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Setea codigo del usuario.
	 * @param codigo codigo del usuario.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	
}
