/**
 * Resumen.
 * Objeto                     : SEquivalencia.
 * Descripci�n                : Servlet del modulo de equivalencia.
 * Fecha de Creaci�n          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.controlador.CEquivalencia;
import pe.com.upz.controlador.CMantenimiento;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Servlet del modulo de equivalencia.
 *
 */
public class SEquivalencia extends HttpServlet {
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
			short indicador = -1;
			if (operacion.equals("inicioAsignacion")) {
				ruta = iniciarAsignacionPuntos(request);
			} else if (operacion.equals("agregarEquivalencia")) {
				ruta = agregarEquivalencia(request);
			} else if (operacion.equals("almacenarEquivalencia")) {
				ruta = almacenarEquivalencia(usuario, request);
			} else if (operacion.equals("inicioCatalogo")) {
				ruta = iniciarCatalogo(request);
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
	 * Muestra la pagina incial del listado de asignacion de puntos.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String iniciarAsignacionPuntos(HttpServletRequest request) {
		String ruta = "";
		try {
			String valorAuxiliar = "";
			String pagina = request.getParameter("hddPagina");

			if (pagina == null || pagina.equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request
					.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if (filtro == 1) {
				valorAuxiliar = request.getParameter("selTipoBuscar");
			} else if (filtro == 2) {
				valorAuxiliar = request.getParameter("txtNombreBuscar");
			} else if (filtro == 3) {
				valorAuxiliar = request.getParameter("txtCodigoBuscar");
			}

			Lista listadoProducto = null;
			Lista listadoTipoProducto = null;

			CEquivalencia cEquivalencia = new CEquivalencia();
			CMantenimiento cMantenimiento = new CMantenimiento();
			listadoProducto = cEquivalencia.obtenerListadoProductosPuntaje(
					true, filtro, valorAuxiliar);
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("filtroPagina", filtro + "");
			request.setAttribute("valorAuxiliar", valorAuxiliar);
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

	/**
	 * Inicio de agregar o modificar equivalencia de puntos.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String agregarEquivalencia(HttpServletRequest request) {
		String ruta = "";
		try {
			String codigo = request.getParameter("chkProducto");
			String nombre = request.getParameter("hddNombre" + codigo);
			String tipoDescripcion = request.getParameter("hddTipo" + codigo);
			int puntaje1 = Integer.parseInt(request.getParameter("hddPuntoUno"
					+ codigo));
			int puntaje2 = Integer.parseInt(request.getParameter("hddPuntoDos"
					+ codigo));
			int puntaje3 = Integer.parseInt(request.getParameter("hddPuntoTres"
					+ codigo));
			double monto1 = Double.parseDouble(request
					.getParameter("hddMontoUno" + codigo));
			double monto2 = Double.parseDouble(request
					.getParameter("hddMontoDos" + codigo));
			double monto3 = Double.parseDouble(request
					.getParameter("hddMontoTres" + codigo));

			BProducto producto = new BProducto();
			producto.setCodigo(Integer.parseInt(codigo));
			producto.setNombre(nombre);
			BTipoProducto tipo = new BTipoProducto();
			tipo.setDescripcion(tipoDescripcion);
			producto.setTipo(tipo);
			BEquivalencia bEquivalencia = new BEquivalencia();
			bEquivalencia.setCantidadPuntoUno(puntaje1);
			bEquivalencia.setCantidadPuntoDos(puntaje2);
			bEquivalencia.setCantidadPuntoTres(puntaje3);
			bEquivalencia.setMontoUno(monto1);
			bEquivalencia.setMontoDos(monto2);
			bEquivalencia.setMontoTres(monto3);
			Lista lista = new Lista();
			lista.setElemento(bEquivalencia);
			producto.setListaPuntaje(lista);

			request.setAttribute("producto", producto);
			ruta = "/jsp/equivalenciaPtos/equ_PuntoXProducto.jsp";
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
	 * Almacena equivalencia de puntos.
	 * 
	 * @param usuario
	 *            usuario de la esion, tipo BUsuario.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarEquivalencia(BUsuario usuario,
			HttpServletRequest request) {
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BProducto producto = new BProducto();
			CEquivalencia cEquivalencia = new CEquivalencia();
			int codProducto = Integer.parseInt(request
					.getParameter("hddCodigoProducto"));

			int puntaje1 = Integer.parseInt(request.getParameter("txtPuntos1"));
			int puntaje2 = Integer.parseInt(request.getParameter("txtPuntos2"));
			int puntaje3 = Integer.parseInt(request.getParameter("txtPuntos3"));
			double monto1 = Double.parseDouble(request
					.getParameter("txtMonto1"));
			double monto2 = Double.parseDouble(request
					.getParameter("txtMonto2"));
			double monto3 = Double.parseDouble(request
					.getParameter("txtMonto3"));

			BEquivalencia bEquivalencia = new BEquivalencia();
			bEquivalencia.setCantidadPuntoUno(puntaje1);
			bEquivalencia.setCantidadPuntoDos(puntaje2);
			bEquivalencia.setCantidadPuntoTres(puntaje3);
			bEquivalencia.setMontoUno(monto1);
			bEquivalencia.setMontoDos(monto2);
			bEquivalencia.setMontoTres(monto3);

			cEquivalencia.alamcenarNuevaEquivalencia(usuario, bEquivalencia,
					conn, codProducto);

			ConnectDS.aceptarTrasaccion(conn);

			return iniciarAsignacionPuntos(request);
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

	/**
	 * Inicia la pagina para mostrar la euivalencia de los productos.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	public String iniciarCatalogo(HttpServletRequest request) {
		String ruta = "";
		try {
			String valorAuxiliar = "";
			String pagina = request.getParameter("hddPagina");

			if (pagina == null || pagina.trim().equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request
					.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if (filtro == 1) {
				valorAuxiliar = request.getParameter("selTipoBuscar");
			} else if (filtro == 2) {
				valorAuxiliar = request.getParameter("txtNombreBuscar");
			} else if (filtro == 3) {
				valorAuxiliar = request.getParameter("txtCodigoBuscar");
			}

			Lista listadoProducto = null;
			Lista listadoTipoProducto = null;

			CEquivalencia cEquivalencia = new CEquivalencia();
			CMantenimiento cMantenimiento = new CMantenimiento();
			listadoProducto = cEquivalencia.obtenerListadoProductosPuntaje(
					true, filtro, valorAuxiliar);
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("filtroPagina", filtro + "");
			request.setAttribute("valorAuxiliar", valorAuxiliar);
			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoProducto", listadoProducto);
			request.setAttribute("listadoTipoProducto", listadoTipoProducto);

			ruta = "/jsp/equivalenciaPtos/equ_Catalogo.jsp";
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