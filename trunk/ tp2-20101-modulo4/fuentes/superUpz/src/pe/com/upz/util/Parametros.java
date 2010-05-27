/**
 * Resumen.
 * Objeto                     : Parametros.
 * Descripción                : Clase que contiene los parametros del sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.util;
/**
 * Clase que contiene los parametros del sistema.
 */
public class Parametros {
	/**
	 * Nombre de la empresa.
	 */
	public final static String ENTERPRISE_NOMBRE = "Supermercados UPZ";
	/**
	 * Nombre de la aplicacion.
	 */
	public final static String S_APP_NOMBRE = "Módulo de Fidelización ";
	/**
	 * 
	 */
	public static String URL;
	/**
	 * 
	 */
	public static String USUARIO;
	/**
	 * 
	 */
	public static String CLAVE;
	
	/**
	 * Numero del dia en la que se generara
	 *  una orden de pedido si necesidad de una autorizacion
	 */
	public static int NUMERO_DIA_GENERAR_PEDIDO=2;
	/**
	 * Numero de la semana en la que se generara
	 *  una orden de pedido si necesidad de una autorizacion
	 */
	public static int NUMERO_SEMANA_GENERAR_PEDIDO=3;
	
	/**
	 * codigo del rol de jefe de fidelizacion.
	 */
	public static int CODIGO_JEFE_FIDELIZACION=7;
	
	/**
	 * Cantidad de productos permitida en una orden de pedido.
	 */
	public static int CANTIDAD_PRODUCTOS_X_ORDEN=2;
	
	public static String[] NOMBRE_MES = { "enero", "febrero", "marzo", "abril",
			"mayo", "junio", "julio", "agosto", "septiembre", "octubre",
			"noviembre", "diciembre" };
}

