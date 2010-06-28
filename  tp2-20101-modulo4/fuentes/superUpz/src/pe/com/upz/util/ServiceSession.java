/**
 * Resumen.
 * Objeto                     : ServiceSession.
 * Descripción                : Clase para servicio de la sesion.
 * Fecha de Creación          : 26/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

/**
 * Clase para servicio de la sesion.
 *
 */
public class ServiceSession extends HttpServlet
{

  /**
   * Metodo para peticiones que viene de formularios con metodo tipo GET
   * @throws java.io.IOException            : Excepcion para el manejo de las entrada y salida de datos.
   * @throws javax.servlet.ServletException : Excepcion para el manejo de objetos del servlet.   
   * @param response  : Parametro de requerimientos o peticiones
   * @param request   : Parametro de respuestas a las peticiones
   */
  public void doGet( HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
  {
        //header params
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=iso-8859-1");

        //method 
        String metodo = request.getParameter("metodo");

        if ((metodo == null) || ! metodo.startsWith("request")) {
            return;
        }

        String resul;

        //method calling
        Method method = null;
        try {
            method = this.getClass().getMethod(metodo, new Class[] { HttpServletRequest.class, HttpServletResponse.class });
            resul = "OK" + (String) method.invoke(this, new Object[] { request, response });
        }catch(InvocationTargetException ite){        	        	
		       String _exmsg = ite.getTargetException().getMessage();
           resul = "Error de sistema:\n" + _exmsg;
        }catch (Exception ex) {
           resul = "Error de sistema:\n" + ex.getMessage();
        }
        response.getWriter().write(resul);
  }

  /**
   * Metodo para peticiones que viene de formularios con metodo tipo POST
   * @throws java.io.IOException            : Excepcion para el manejo de las entrada y salida de datos.
   * @throws javax.servlet.ServletException : Excepcion para el manejo de objetos del servlet.   
   * @param response  : Parametro de requerimientos o peticiones
   * @param request   : Parametro de respuestas a las peticiones
   */
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
  {
  }

  /**
   * Metodo que da como resultado 1 o 0, si la session esta activa o no respectivamente.
   * @throws java.io.IOException            : Excepcion para el manejo de las entrada y salida de datos.
   * @throws javax.servlet.ServletException : Excepcion para el manejo de objetos del servlet.
   * @return          : "0" o "1", cadena de aceptación. 
   * @param response  : Parametro de requerimientos o peticiones
   * @param request   : Parametro de respuestas a las peticiones.
   */
  public String requestExisteSesion(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  { 
    if (request.getSession() == null || request.getSession().getAttribute("UsuarioSesion") == null)
        return "0";
    else 
        return "1";
  }	
}