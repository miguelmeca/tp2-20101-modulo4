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
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import org.apache.tomcat.util.http.fileupload.DefaultFileItem;
import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.controlador.CMantenimiento;
import pe.com.upz.controlador.CMantenimientoCliente;
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
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String ruta = "";
			String codigo = "0";
			String nombre="";
			String descripcion="";
			int codigoTipo=-1;
			BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
			"usuarioSesion"));
			BSucursal sucursal = ((BSucursal) request.getSession().getAttribute(
			"sucursalSesion"));
			DiskFileUpload fu = new DiskFileUpload();
			FileInputStream DocBin;
			// maximo numero de bytes
			fu.setSizeMax(1024 * 1024 * 1);
			// tamaño por encima del cual los ficheros son escritos directamente
			// en disco
			fu.setSizeThreshold(4096);
			List fileItems = fu.parseRequest(request);
			Iterator i = fileItems.iterator();
			DefaultFileItem parametro = null;
			File fichero = null;
			FileItem actual = null;
			byte[] vecBytes = null;
			while (i.hasNext()) {
				parametro = (DefaultFileItem) i.next();
				if (parametro.getFieldName().equals("hddOperacion")) { 
					if(parametro.getString().equals("almacenarProducto")){
						ruta = almacenarNuevoProducto(usuario, request,codigo,nombre,descripcion,codigoTipo, sucursal);
					}
				}else if(parametro.getFieldName().equals("txtNombre")){
					nombre = (String)parametro.getString();
				}else if(parametro.getFieldName().equals("txtDescripcion")){
					descripcion = (String) parametro.getString();
				}else if(parametro.getFieldName().equals("selTipo")){
					codigoTipo = Integer.parseInt((String)parametro.getString());
				}else if(parametro.getFieldName().equals("filRutaImagen")){
					actual = (FileItem) parametro;
					String fileName = actual.getName();
					System.out.println(request.getRealPath("")+"\\images\\productos\\"+codigo+".jpg");
					fichero = new File(request.getRealPath("")+"\\images\\productos\\"+codigo+".jpg");
					actual.write(fichero);
				}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operacion = (String) request.getParameter("hddOperacion");
			String ruta = "";
			BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
					"usuarioSesion"));
			BSucursal sucursal = ((BSucursal) request.getSession().getAttribute(
			"sucursalSesion"));
			if (operacion.equals("ingresoMantenerProductos")) {
				ruta = mostrarListadoProductos(request);
			} else if (operacion.equals("ingresoNuevoProducto")) {
				ruta = inicioNuevoActualizaProducto(request);
			}  else if (operacion.equals("buscarProducto")) {
				ruta = mostrarListadoProductos(request);
			}else if (operacion.equals("eliminarProducto")) {
				ruta = eliminarProducto(request, usuario, sucursal);
			}else if (operacion.equals("inicioActualizaProducto")) {
				ruta = inicioActualizaProducto(request);
			}else if (operacion.equals("actualizarProducto")) {
				ruta = almacenarCambiosProducto(request, usuario);
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
	 * @param verMantenimiento indica si se muestra como mantenimiento,  tipo boolean.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String mostrarListadoProductos(HttpServletRequest request,boolean verMantenimiento) {
		String ruta = "";
		try {
			String valorAuxiliar="";
			String pagina = request.getParameter("hddPagina");
			String mostrarMantenimiento = request.getParameter("hddMantenimiento");
			if (verMantenimiento || mostrarMantenimiento == null) {
				mostrarMantenimiento = "1";
			}
			if (pagina == null || pagina.equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if(filtro == 1){
				valorAuxiliar = request.getParameter("selTipoBuscar");
			}else if(filtro == 2){
				valorAuxiliar = request.getParameter("txtNombreBuscar");
			}else if(filtro == 3){
				valorAuxiliar = request.getParameter("txtCodigoBuscar");
			}
			
			Lista listadoProducto = null;
			Lista listadoTipoProducto = null;

			CMantenimiento cMantenimiento = new CMantenimiento();
			listadoProducto = cMantenimiento.obtenerListadoProductos(true,
					filtro, valorAuxiliar);
			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			request.setAttribute("filtroPagina", filtro+"");
			request.setAttribute("valorAuxiliar", valorAuxiliar);
			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoProducto", listadoProducto);
			request.setAttribute("listadoTipoProducto", listadoTipoProducto);
			request.setAttribute("mantenimiento", mostrarMantenimiento);
			//request.setAttribute("mostrar", "1");

			ruta = "/jsp/maestroProductos/mae_ListadoProductos.jsp?mostrar="+mostrarMantenimiento;
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

			int codigo = Integer.parseInt(((String) request
					.getParameter("hddCodigo") == null ? "-1"
					: (String) request.getParameter("hddCodigo")));
			String nombre = (String) request.getParameter("hddNomnre" + codigo);
			String descripcion = (String) request.getParameter("hddDescripcion"
					+ codigo);
			int codigoTipo = Integer.parseInt(((String) request
					.getParameter("hddCodigoTipo" + codigo) == null ? "0"
					: (String) request.getParameter("hddCodigoTipo" + codigo)));
			String rutaImagen = (String) request.getParameter("hddRutaImagen"
					+ codigo);

			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			BProducto producto = new BProducto();
			if (codigo == -1) {
				producto = null;
			} else {
				producto.setCodigo(codigo);
				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setRutaImagen(rutaImagen);
				BTipoProducto tipo = new BTipoProducto();
				tipo.setCodigo(codigoTipo);
				producto.setTipo(tipo);
			}

			request.setAttribute("listadoTipoProducto", listadoTipoProducto);

			request.setAttribute("producto", producto);

			ruta = "/jsp/maestroProductos/mae_MantenerProducto.jsp";
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
	 * @param usuario
	 *            usuario de la sesion, tipo BUsuario.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param codigo
	 *            codigo del producto a generar, tipo String.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarNuevoProducto(BUsuario usuario,
			HttpServletRequest request,
			String codigo,String nombre,String descripcion,int codigoTipo, BSucursal sucursal) {
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BProducto producto = new BProducto();
			BTipoProducto tipoProducto = new BTipoProducto();
			// int codigo =
			// Integer.parseInt((String)request.getParameter("hddCodigo"));
			//String nombre = (String) request.getParameter("txtNombre");
			//String descripcion = (String) request
			//		.getParameter("txtDescripcion");
			//int codigoTipo = Integer.parseInt((String) request
			//		.getParameter("selTipo"));
			//String rutaImagen = (String) request.getParameter("txtRutaImagen");

			tipoProducto.setCodigo(codigoTipo);
			producto.setNombre(nombre);
			producto.setDescripcion(descripcion);
			producto.setTipo(tipoProducto);
			//producto.setRutaImagen(rutaImagen);

			CMantenimiento cMantenimiento = new CMantenimiento();
			codigo = new String(cMantenimiento.almacenarProducto(producto, usuario, conn,sucursal)+"");
			
			ConnectDS.aceptarTrasaccion(conn);

			request.setAttribute("mensajeMantenimiento", "Se ha registrado el nuevo producto.");

			ruta = mostrarListadoProductos(request);

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
	//gonza
	/**
	 * Muestra la pantalla de listado de productos.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String mostrarListadoProductos(HttpServletRequest request) {
		return mostrarListadoProductos(request,false);
	}
	/**
	 * Elimina producto.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo BSucursal.
	 * @return
	 */
	private String eliminarProducto(HttpServletRequest request,BUsuario usuario, BSucursal sucursal){
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			int codigoProducto=Integer.parseInt(request.getParameter("hddCodigoSeleccionado"));
						
			CMantenimiento cMantenimiento = new CMantenimiento();
			cMantenimiento.eliminarProducto(codigoProducto, usuario, conn, sucursal);
			ConnectDS.aceptarTrasaccion(conn);
			
			request.setAttribute("mensajeMantenimiento", "Se ha dado de baja al producto.");
			ruta = mostrarListadoProductos(request,true);
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
	/**
	 * Muestra la pantalla de modificar producto.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String inicioActualizaProducto(HttpServletRequest request) {
		String ruta = "";
		try {

			Lista listadoTipoProducto = null;
			CMantenimiento cMantenimiento = new CMantenimiento();

			int codigo = Integer.parseInt(((String) request
					.getParameter("hddCodigoSeleccionado") == null ? "-1"
					: (String) request.getParameter("hddCodigoSeleccionado")));
			
			//String rutaImagen = (String) request.getParameter("hddRutaImagen"
			//		+ codigo);

			listadoTipoProducto = cMantenimiento.obtenerListadoTipoProductos();

			BProducto producto = cMantenimiento.obtenerProductos(codigo);
			

			request.setAttribute("listadoTipoProducto", listadoTipoProducto);

			request.setAttribute("producto", producto);

			ruta = "/jsp/maestroProductos/mae_ModificarProducto.jsp";
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
	 * Almacena cambios producto producto.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
		private String almacenarCambiosProducto(HttpServletRequest request,BUsuario usuario){
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
					
			BProducto producto = new BProducto();
			BTipoProducto tipo = new BTipoProducto();
			
			producto.setCodigo(Integer.parseInt(request.getParameter("hddCodigo")));
			producto.setNombre(request.getParameter("txtNombre"));
			producto.setDescripcion(request.getParameter("txtDescripcion"));
			tipo.setCodigo(Integer.parseInt(request.getParameter("selTipo")));
			producto.setTipo(tipo);
		
		
			CMantenimiento cMantenimiento = new CMantenimiento();
			cMantenimiento.guardarCambiosProducto(producto, usuario, conn);
			ConnectDS.aceptarTrasaccion(conn);
			
			request.setAttribute("mensajeMantenimiento", "Se ha modificado al producto.");
			ruta = mostrarListadoProductos(request,true);
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
	//gonza
}
