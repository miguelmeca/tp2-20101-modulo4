/**
 * Resumen.
 * Objeto                     : ConnectDS.
 * Descripción                : Clase para la conexión a base de datos.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.comun;

import java.sql.*;

import pe.com.upz.util.Parametros;
/**
 * Clase para la conexión a base de datos.
 *
 */
public class ConnectDS {
 
	/**
	 * Formato DD/MM/YYYY para las fechas.
	 */
	public static final String  FORMATO_FECHA_DDMMYYYY="DD/MM/YYYY";
	/**
	 * Formato para obtener el numero de la semana en un mes.
	 */
	public static final String  FORMATO_NUMERO_SEMANA="W";
	/**
	 * Formato para obtener el numero del dia de la semana.
	 */
	public static final String  FORMATO_NUMERO_DIA_SEMANA="D";
	/**
	 * Formato DD/MM/YYYY H24:MI:SS para las fechas.
	 */
	public static final String  FORMATO_DDMMYYHHMMSS="DD/MM/YYYY HH24:MI:SS";
	/**
	 * Formato H24:MI para las fechas.
	 */	
	public static final String  FORMATO_HHMI="hh24:mi";
	/**
	 * realiza la conexion a la base de datos.
	 * @return objeto de conexion a la BD.
	 */
	public static Connection obtenerConeccion(){
		Connection connection = null;
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());    
			connection = DriverManager.getConnection          
			("jdbc:oracle:thin:@localhost:1521:XE", "fidelizacion", "fidelizacion");
			
			//Class.forName ("oracle.jdbc.driver.OracleDriver");    
			//connection = DriverManager.getConnection ("jdbc:oracle:oci:@XE", "fidelizacion", "fidelizacion");          
			//@TNSNames_Entry,  userid,  password
			
		} catch (Exception e) {
			System.out.println(
				"Proyecto: "
					+ Parametros.S_APP_NOMBRE
					+ "; Clase: ConnectDS; "
					+ "; Parametros="
					+ Parametros.URL
					+ ":"
					+ Parametros.USUARIO
					+ ":"
					+ Parametros.CLAVE
					+ "; Mensaje:"
					+ e);

		}

		return connection;
	}
	/**
	 * obtiene la fecha con formato dd/mm/yyyy.
	 * @return fecha con formato dd/mm/yyyy
	 */
	public static String obtenerFecha(){
		return obtenerFechaFormato(FORMATO_FECHA_DDMMYYYY);
	}
	public static void cerrarConexion(Connection conn){
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}catch(Exception e){
			
		}
	}
	/**
	 * Realiza commit a la BD.
	 * @param conn conexion a la BD, tipo Connection.
	 */
	public static void aceptarTrasaccion(Connection conn){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.commit();
			}
			
		} catch (SQLException e) {
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: ConnectDS; "
						+ "; Parametros="
						+ Parametros.URL
						+ ":"
						+ Parametros.USUARIO
						+ ":"
						+ Parametros.CLAVE
						+ "; Mensaje:"
						+ e);
		}
	}
	/**
	 * realiza un rollbacka  a la BD.
	 * @param conn conexion a la BD, tipo Connection.
	 */
	public static void deshacerTrasaccion(Connection conn){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.rollback();
			}
			
		} catch (SQLException e) {
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: ConnectDS; "
						+ "; Parametros="
						+ Parametros.URL
						+ ":"
						+ Parametros.USUARIO
						+ ":"
						+ Parametros.CLAVE
						+ "; Mensaje:"
						+ e);
		}
	}
	/**
	 * Retorna una fecha en un formato espeficico.
	 * @param formato formato para obtener la fecha.
	 * @return fecha formateada.
	 */
	public static String obtenerFechaFormato(String formato){
		String fecha="";
		Connection conn =null;
		Statement stm=null;;
		ResultSet rs=null;;
		try {
			conn = ConnectDS.obtenerConeccion();
			stm=conn.createStatement();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT TO_CHAR(SYSDATE,'"+formato+"') FROM DUAL");

			rs=stm.executeQuery(sql.toString());
			
			if (rs.next()) {
				fecha = rs.getString(1);
			}
			stm.close();
			conn.close();				
		}catch (SQLException e) {
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: ConnectDS; "
						+ "; Parametros="
						+ Parametros.URL
						+ ":"
						+ Parametros.USUARIO
						+ ":"
						+ Parametros.CLAVE
						+ "; Mensaje:"
						+ e);
		}finally{
			try {
				if(!conn.isClosed()){
					if(rs!=null){
						rs.close();
					}
					if(stm!=null){
						stm.close();
					}
					conn.close();
				}
				
			} catch (SQLException e2) {
				// TODO: handle exception
			}
			
		}
		return fecha;
	}
}
