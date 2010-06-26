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
import pe.com.upz.controlador.CReporte;
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
			HSSFWorkbook workBook;
			BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
					"usuarioSesion"));
			short indicador = -1;
			if (operacion.equals("reproteClientes")) {
				ruta = mostrarReporteClientes(request);
			}else if(operacion.equals("mostrarReporteCliente")){
				indicador =0;
				workBook = generarReporteClientes(request,usuario); 
				response.setHeader("Content-Disposition", 
                		"attachment;filename=REPORTE_CLIENTES.xls;");
				response.setContentType("application/vnd.ms-excel");
				workBook.write(response.getOutputStream());
				response.getOutputStream().close();
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
	
	/**
	 * Procesa el reporte de clientea ganados/perdidos en un mes determinado
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return archivo xls del reporte, tipo HSSFWorkbook.
	 */
	private String mostrarReporteClientes(HttpServletRequest request){
		String ruta = "/jsp/reporte/rep_ClientesPorMes.jsp";
		return ruta;
	}

	private HSSFWorkbook generarReporteClientes(
			HttpServletRequest request,
			BUsuario usuario) {

		String rutaInicial = getServletContext().getRealPath("/");
		String rutaExcelPlantilla = "/recursos/plantillas/";
		int numeroMes = Integer.parseInt(request.getParameter("selMes"));
		

		HSSFWorkbook archivoXls = new HSSFWorkbook();
		//NReporte negocioReportes = new NReporte();
		
		CReporte negocioReportes = new CReporte();
		
		try {
			archivoXls = negocioReportes
					.generarReporteExpAdministrativo(usuario,
							rutaExcelPlantilla, rutaInicial,
							numeroMes);
		} catch (Exception e) {
			archivoXls = null;
		}
		return archivoXls;
	}
}
