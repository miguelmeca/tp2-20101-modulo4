/**
 * Resumen.
 * Objeto                     : SMantenimiento.
 * Descripción                : Servlet para lel mantenimiento de los objetos del modulo de fidelizacion.
 * Fecha de Creación          : 02/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.controlador.CMantenimiento;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Servlet para lel mantenimiento de los objetos del modulo de fidelizacion.
 * 
 */
public class SMantenimiento extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operacion = (String) request.getParameter("hddOperacion");
			String ruta = "";
			BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
					"usuarioSesion"));
			if (operacion.equals("ingresoMantenerProductos")) {
				ruta = mostrarListadoProductos(request);
			} else if (operacion.equals("ingresoNuevoProducto")) {
				ruta = inicioNuevoActualizaProducto(request);
			} else if (operacion.equals("almacenarProducto")) {
				ruta = almacenarNuevoProducto(request);
			}

			getServletConfig().getServletContext().getRequestDispatcher(ruta)
					.forward(request, response);

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
	 * Muestra la pantalla de listado de productos.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String mostrarListadoProductos(HttpServletRequest request) {
		String ruta = "";
		try {
			int filtro = Integer.parseInt((String) request
					.getParameter("selFiltro"));

			Lista listadoProducto = null;
			Lista listadoTipoProducto = null;

			CMantenimiento cMantenimiento = new CMantenimiento();
			listadoProducto = cMantenimiento.obtenerListadoProductos(true,
					filtro);
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("listadoProducto", listadoProducto);
			request.setAttribute("listadoTipoProducto", listadoTipoProducto);
			ruta = "/jsp/abastecimiento/aba_GenerarOrden.jsp";
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
	 * Muestra la pantalla de agregar producto.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String inicioNuevoActualizaProducto(HttpServletRequest request) {
		String ruta = "";
		try {

			Lista listadoTipoProducto = null;
			CMantenimiento cMantenimiento = new CMantenimiento();
			
			int codigo = Integer.parseInt(((String)request.getParameter("hddCodigo")==null?"0":(String)request.getParameter("hddCodigo")));
			String descripcion = (String)request.getParameter("hddDescripcion"+codigo);
			int codigoTipo = Integer.parseInt(((String)request.getParameter("hddCodigoTipo"+codigo)==null?"0":(String)request.getParameter("hddCodigoTipo"+codigo)));
			String rutaImagen = (String)request.getParameter("hddRutaImagen"+codigo);
			
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("listadoTipoProducto", listadoTipoProducto);
			
			request.setAttribute("codigo", codigo);
			request.setAttribute("descripcion", descripcion);
			request.setAttribute("codigoTipo", codigoTipo);
			request.setAttribute("rutaImagen", rutaImagen);
			
			ruta = "/jsp/abastecimiento/aba_GenerarOrden.jsp";
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
	 * Almacena el nuevo producto.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarNuevoProducto(HttpServletRequest request) {
		String ruta = "";
		Connection conn =null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BProducto producto = new BProducto();
			BTipoProducto tipoProducto = new BTipoProducto();
			//int codigo = Integer.parseInt((String)request.getParameter("hddCodigo"));
			String descripcion = (String)request.getParameter("txtDescripcion");
			int codigoTipo = Integer.parseInt((String)request.getParameter("setTipo"));
			String rutaImagen = (String)request.getParameter("hddRutaImagen");

			tipoProducto.setCodigo(codigoTipo);
			
			producto.setDescripcion(descripcion);
			producto.setTipo(tipoProducto);
			producto.setRutaImagen(rutaImagen);
			
			CMantenimiento cMantenimiento = new CMantenimiento();
			cMantenimiento.almacenarProducto(producto,conn);
			ConnectDS.aceptarTrasaccion(conn);
		} catch (Exception e) {
			ConnectDS.deshacerTrasaccion(conn);
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + this.getClass().getName() + ";"
					+ "; Parametros=" + Parametros.URL + ":"
					+ Parametros.USUARIO + ":" + Parametros.CLAVE
					+ "; Mensaje:" + e);
		}
		return ruta;
	}
}
