package pe.com.upz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.controlador.CEquivalencia;
import pe.com.upz.controlador.CMantenimiento;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

public class SEquivalencia extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
try {
	String operacion = (String) request.getParameter("hddOperacion");
	String ruta = "";
	HSSFWorkbook workBook;
	BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
			"usuarioSesion"));
	short indicador = -1;
	if (operacion.equals("inicioAsignacion")) {
		ruta = iniciarAsignacionPuntos(request);
	}

	if(indicador == -1){
		getServletConfig().getServletContext().getRequestDispatcher(ruta)
				.forward(request, response);
	}
} catch (Exception e) {
	System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
			+ "; Clase: " + getClass().getName() + "; Mensaje:" + e);
	request.setAttribute("mensajeSistema", "En este momento no lo podemos atender");
	getServletConfig().getServletContext().getRequestDispatcher("/jsp/comun/msg.jsp")
	.forward(request, response);
}
}
	private String iniciarAsignacionPuntos (HttpServletRequest request){
		String ruta = "";
		try {
			String pagina = request.getParameter("pagina");

			if (pagina == null) {
				pagina = "1";
			}

			String filtroPagina = (String) request.getParameter("selFiltro");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			Lista listadoProducto = null;
			Lista listadoTipoProducto = null;

			CEquivalencia cEquivalencia = new CEquivalencia();
			CMantenimiento cMantenimiento = new CMantenimiento();
			listadoProducto = cEquivalencia.obtenerListadoProductosPuntaje(true,
					filtro);
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoProducto", listadoProducto);
			request.setAttribute("listadoTipoProducto", listadoTipoProducto);

			ruta = "/jsp/equivalenciaPtos/equ_ListadoPuntaje.jsp";
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
