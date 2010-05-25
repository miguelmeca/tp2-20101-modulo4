/**
 * Resumen.
 * Objeto                     : SLogeo.
 * Descripción                : Servlet para logearse al sistema.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.upz.bean.BRol;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DOpcion;
import pe.com.upz.dao.DRol;
import pe.com.upz.dao.DUsuario;
import pe.com.upz.daoInterface.IOpcion;
import pe.com.upz.daoInterface.IRol;
import pe.com.upz.daoInterface.IUsuario;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

public class SLogeo extends HttpServlet {
 
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operacion = (String)request.getParameter("hddOperacion");
			String ruta = "";
			if (operacion.equals("validarIngreso")) {
				ruta = validarIngreso(request);
			}

			response.sendRedirect(ruta);

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + getClass().getName() + "; Mensaje:" + e);
			response
					.sendRedirect("msg.jsp?tipo=error&titulo=Login&descripcion=En este momento no lo podemos atender&continua=login.jsp");
		}

	}

	/**
	 * Valida el ingreso al sistema.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar.
	 */
	private String validarIngreso(HttpServletRequest request) {
		String ruta = "";
		try {
			IUsuario usuarioDao = new DUsuario();
			BUsuario usuario;

			String sLogin;
			String sClave;

			sLogin = request.getParameter("txtUsuario");
			sClave = request.getParameter("txtClave");
			
			if(!usuarioDao.validarExisteUsuario(sLogin)){
				ruta = "index.jsp?mensaje=usuarioInvalido";
				return ruta;
			}
			
			usuario = usuarioDao.validarUsuario(sLogin, sClave);

			if (usuario != null) {

				if (request.isRequestedSessionIdValid()) {
					HttpSession tempSession = request.getSession(false);
					tempSession.invalidate();
				}
				IRol rol = new DRol();
				IOpcion opcion = new DOpcion();
				Lista menu = new Lista();

				// obteniendo los roles
				rol.obtenerRol(usuario);
				// obteniendo las opciones
				for (int i = 0; i < usuario.getListaRol().getTamanio(); i++) {
					opcion.obtenerOpcionesUsuario((BRol) usuario.getListaRol()
							.getElemento(i));

				}
				// obteniendo el menu
				menu = usuarioDao.obtenerMenu(usuario);

				HttpSession session = request.getSession(true);
				session.setAttribute("usuarioSesion", usuario);
				session.setAttribute("menu", menu);

				ruta = "jsp/comun/cuerpo.jsp";
			} else {
				ruta = "index.jsp?mensaje=contraseniaInvalido";
			}
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
		return ruta;
	}
}
