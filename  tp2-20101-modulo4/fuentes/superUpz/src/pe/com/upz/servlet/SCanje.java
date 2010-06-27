package pe.com.upz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.IUbigeo;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

public class SCanje extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
try {
	String operacion = (String) request.getParameter("hddOperacion");
	String ruta = "";
	BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
			"usuarioSesion"));
	BSucursal sucursal = ((BSucursal) request.getSession().getAttribute(
	"sucursalSesion"));
	short indicador = -1;
	if (operacion.equals("ingresoRegistroCanje")) {
		ruta = ingresoRegistroCanje(request);
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
}
