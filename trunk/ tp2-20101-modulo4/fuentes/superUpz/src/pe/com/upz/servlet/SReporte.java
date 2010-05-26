/**
 * Resumen.
 * Objeto                     : SAbastecimiento.
 * Descripción                : Servlet del modulo de reportes.
 * Fecha de Creación          : 25/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pe.com.upz.bean.BUsuario;
import pe.com.upz.negocio.NReporte;
import pe.com.upz.util.Parametros;

public class SReporte extends HttpServlet {
	
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
			if (operacion.equals("reproteClientes")) {
				ruta = mostrarReporteClientes(request);
			} 

			
			getServletConfig().getServletContext().getRequestDispatcher(ruta)
					.forward(request, response);

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + getClass().getName() + "; Mensaje:" + e);
			response
					.sendRedirect("msg.jsp?tipo=error&titulo=Login&descripcion=En este momento no lo podemos atender&continua=login.jsp");
		}
	}
	
	private String mostrarReporteClientes(HttpServletRequest request){
		String ruta = "/jsp/reporte/rep_ClientesPorMes.jsp";
		return ruta;
	}

	private HSSFWorkbook generarReporteExpAdministrativo(
			HttpServletRequest request,
			BUsuario usuario) {

		String fechaActual;

		String rutaInicial = getServletContext().getRealPath("/");
		String rutaExcelPlantilla = "recursos/plantillas/";

		String fechaInicio = "";//Util.convNulo(request.getParameter("FechaInicio"));
		String fechaFin = "";//Util.convNulo(request.getParameter("FechaFin"));

		HSSFWorkbook archivoXls = new HSSFWorkbook();
		NReporte negocioReportes = new NReporte();

		try {

			//LMensaje listaMensajes = (LMensaje) this.getServletConfig()
			//		.getServletContext().getAttribute("listaMensajes");

			//Connection objConexion = Negocio.getConexionBD(usuario);

			fechaActual ="";// negConPenal.obtenerFechaActual(objConexion,
			//		"dd/mm/yyyy hh24:mi:ss");

			//Negocio.CerrarConexionBD(objConexion);

			archivoXls = negocioReportes
					.generarReporteExpAdministrativo(usuario, fechaActual,
							rutaExcelPlantilla, rutaInicial,
							0,0,
							fechaInicio, fechaFin);
		} catch (Exception e) {
			archivoXls = null;
		}
		return archivoXls;
	}
}
