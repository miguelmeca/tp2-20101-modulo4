/**
 * Resumen.
 * Objeto                     : SCanje.
 * Descripción                : Servlet del modulo de canje.
 * Fecha de Creación          : 28/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.controlador.CAbastecimiento;
import pe.com.upz.controlador.CCanje;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.IUbigeo;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Servlet del modulo de canje.
 *
 */
public class SCanje extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operacion = (String) request.getParameter("hddOperacion");
			String ruta = "";
			BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
					"usuarioSesion"));
			BSucursal sucursal = ((BSucursal) request.getSession()
					.getAttribute("sucursalSesion"));
			short indicador = -1;
			if (operacion.equals("ingresoRegistroCanje")) {
				ruta = ingresoRegistroCanje(request);
			} else if (operacion.equals("almacenarCanje")) {
				ruta = almacenarCanje(request,usuario,sucursal);
			}
			if (indicador == -1) {
				getServletConfig().getServletContext().getRequestDispatcher(
						ruta).forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + getClass().getName() + "; Mensaje:" + e);
			request.setAttribute("mensajeSistema",
					"En este momento no lo podemos atender");
			getServletConfig().getServletContext().getRequestDispatcher(
					"/jsp/comun/msg.jsp").forward(request, response);
		}
	}

	/**
	 * Ingreso a la opcion de canje.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String ingresoRegistroCanje(HttpServletRequest request) {
		String ruta = "";
		try {

			ruta = "/jsp/canjeProducto/can_RegistraCanje.jsp";
		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + this.getClass().getName() + ";"
					+ "; Parametros=" + Parametros.URL + ":"
					+ Parametros.USUARIO + ":" + Parametros.CLAVE
					+ "; Mensaje:" + e);
		}
		return ruta;
	}
	/**
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo BSucursal.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarCanje(HttpServletRequest request, BUsuario usuario,BSucursal sucursal) {
		String ruta = "/jsp/canjeProducto/can_RegistraCanje.jsp";
		Connection conn =null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			int codigoProducto = Integer.parseInt(request.getParameter("hddCodigoProducto"));
			int codigoCuenta= Integer.parseInt(request.getParameter("hddCodigoCliente"));
			
			int cantidad =Integer.parseInt(request.getParameter("txtCantidadCanje"));
			String x=request.getParameter("hddPuntaje");
			int puntaje=Integer.parseInt(x);
			double monto= Double.parseDouble(request.getParameter("txtMonto"));
			
			puntaje = puntaje*cantidad;
			
			CCanje cAba = new CCanje();
			//actualiza el puntaje
			cAba.actualizarPuntaje(conn, codigoCuenta, puntaje, usuario);
			//actualiza stock
			String numeroPedido = cAba.alamcenarOrden(request, usuario, codigoProducto,cantidad, conn,sucursal);
			request.setAttribute("mensajeMantenimiento", "Se ha realizado el canje.");
			ruta = "/jsp/canjeProducto/can_RegistraCanje.jsp";
			ConnectDS.aceptarTrasaccion(conn);
		} catch (Exception e) {
			ConnectDS.deshacerTrasaccion(conn);
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + this.getClass().getName() + ";"
					+ "; Parametros=" + Parametros.URL + ":"
					+ Parametros.USUARIO + ":" + Parametros.CLAVE
					+ "; Mensaje:" + e);
		}finally{
			ConnectDS.cerrarConexion(conn);
		}
		return ruta;
	}
}
