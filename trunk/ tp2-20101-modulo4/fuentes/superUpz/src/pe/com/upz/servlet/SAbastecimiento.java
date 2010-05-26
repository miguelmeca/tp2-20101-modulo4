/**
 * Resumen.
 * Objeto                     : SAbastecimiento.
 * Descripción                : Servlet del modulo de abastecimiento.
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

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BPedido;
import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BRol;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.dao.DOpcion;
import pe.com.upz.dao.DPedido;
import pe.com.upz.dao.DPedidoDetalle;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DRol;
import pe.com.upz.dao.DUsuario;
import pe.com.upz.daoInterface.IDetallePedido;
import pe.com.upz.daoInterface.IOpcion;
import pe.com.upz.daoInterface.IPedido;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.daoInterface.IRol;
import pe.com.upz.daoInterface.IUsuario;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Servlet del modulo de abastecimiento.
 *
 */
public class SAbastecimiento extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operacion = (String) request.getParameter("hddOperacion");
			String ruta = "";
			BUsuario usuario = ((BUsuario)request.getSession().getAttribute("usuarioSesion"));
			if (operacion.equals("ingresoOrden")) {
				ruta = mostrarIngresoOrden(request, false);
			}else if(operacion.equals("validarPermiso")){
				ruta = validarPermiso(request);
			}else if(operacion.equals("mostrarOrden")){
				ruta = mostrarOrden(request);
			}else if(operacion.equals("almacenarOrden")){
				ruta = almacenarOrden(request,usuario);
			}

			getServletConfig().getServletContext().getRequestDispatcher(ruta).forward(request, response);

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + getClass().getName() + "; Mensaje:" + e);
			response
					.sendRedirect("msg.jsp?tipo=error&titulo=Login&descripcion=En este momento no lo podemos atender&continua=login.jsp");
		}

	}
	/**
	 * Almacena productos para una orden de pedido 
	 * para la orden de pedido
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @return
	 */
	private String almacenarOrden(HttpServletRequest request, BUsuario usuario){
		String ruta = "";
		Connection conn =null;
		try{
			Lista listaDetalle= new Lista();
			BProducto producto;
			BPedido pedido = new BPedido();
			BPedidoDetalle detalle;
			BTipoProducto tipo;
			String[] codigo = request.getParameterValues("chkProducto");
			
			for(int i=0;i< codigo.length;i++){
				detalle = new BPedidoDetalle();
				producto = new BProducto();
				producto.setCodigo(Integer.parseInt(codigo[i]));
				producto.setDescripcion(request.getParameter("hddDescripcion"+codigo[i]));
				//producto.setStock(Integer.parseInt(request.getParameter("txtCantidad"+codigo[i])));
				tipo = new BTipoProducto();
				tipo.setDescripcion(request.getParameter("hddTipo"+codigo[i]));
				producto.setTipo(tipo);
				
				detalle.setProducto(producto);
				detalle.setCantidad(Integer.parseInt(request.getParameter("txtCantidad"+codigo[i])));
				
				listaDetalle.setElemento(detalle);
			}
			pedido.setListaDetalle(listaDetalle);
			
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			IPedido pedidoDao = new DPedido();
			IDetallePedido detalleDao = new DPedidoDetalle();
			
			//almacena cabecera
			pedido.setCodigo(pedidoDao.almacenarOrden(conn, usuario));
			//alamcena detalle
			for (int i = 0; i < pedido.getListaDetalle().getTamanio(); i++) {
				detalleDao.almacenarDetalle(conn, pedido.getCodigo(),
						(BPedidoDetalle) pedido.getListaDetalle()
								.getElemento(i), usuario);
			}
			
			conn.commit();
			
			request.setAttribute("mensajeSistema", "Se ha generado la orden número "+pedido.getCodigo());
			ruta = "/jsp/comun/msg.jsp";
		}catch (SQLException e) {
			try {
				System.out.println(
						"Proyecto: "
							+ Parametros.S_APP_NOMBRE
							+ "; Clase: SAbastecimiento; "
							+ "; Parametros="
							+ Parametros.URL
							+ ":"
							+ Parametros.USUARIO
							+ ":"
							+ Parametros.CLAVE
							+ "; Mensaje:"
							+ e);
				if(conn!=null && !conn.isClosed()){
					conn.rollback();
				}
				
			} catch (SQLException e2) {
				System.out.println(
						"Proyecto: "
							+ Parametros.S_APP_NOMBRE
							+ "; Clase: SAbastecimiento; "
							+ "; Parametros="
							+ Parametros.URL
							+ ":"
							+ Parametros.USUARIO
							+ ":"
							+ Parametros.CLAVE
							+ "; Mensaje:"
							+ e);
			}
		}catch(Exception e){
			
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: SAbastecimiento; "
						+ "; Parametros="
						+ Parametros.URL
						+ ":"
						+ Parametros.USUARIO
						+ ":"
						+ Parametros.CLAVE
						+ "; Mensaje:"
						+ e);
		}finally{
			try {
				if(!conn.isClosed()){
					conn.close();
				}
				
			} catch (SQLException e2) {
				System.out.println(
						"Proyecto: "
							+ Parametros.S_APP_NOMBRE
							+ "; Clase: SAbastecimiento; "
							+ "; Parametros="
							+ Parametros.URL
							+ ":"
							+ Parametros.USUARIO
							+ ":"
							+ Parametros.CLAVE
							+ "; Mensaje:"
							+ e2);
			}
		}
		return ruta;
	}
	/**
	 * Muestra la pagina jsp con el detalle de los productos seleccionados 
	 * para la orden de pedido
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return
	 */
	private String mostrarOrden(HttpServletRequest request){
		
		Lista listaProductos= new Lista();
		BProducto producto;
		BTipoProducto tipo;
		String[] codigo = request.getParameterValues("chkProducto");
		
		String fecha = ConnectDS.obtenerFechaFormato(ConnectDS.FORMATO_DDMMYYHHMMSS);
		
		for(int i=0;i< codigo.length;i++){
			producto = new BProducto();
			producto.setCodigo(Integer.parseInt(codigo[i]));
			producto.setDescripcion(request.getParameter("hddDescripcion"+codigo[i]));
			producto.setStock(Integer.parseInt(request.getParameter("txtCantidad"+codigo[i])));
			tipo = new BTipoProducto();
			tipo.setDescripcion(request.getParameter("hddTipo"+codigo[i]));
			producto.setTipo(tipo);
			listaProductos.setElemento(producto);
		}
		request.setAttribute("listaProductos", listaProductos);
		request.setAttribute("fecha", fecha);
		return "/jsp/abastecimiento/aba_MostrarOrden.jsp";
	}
	/**
	 * valida si el rol e identidad del jefe superior que otorga el permiso.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar.
	 */
	private String validarPermiso(HttpServletRequest request){
		String ruta = "";
		try {
			IUsuario usuarioDao = new DUsuario();
			BUsuario usuario;

			String sLogin;
			String sClave;

			sLogin = request.getParameter("txtUsuario");
			sClave = request.getParameter("txtClave");
			
			if(!usuarioDao.validarExisteUsuario(sLogin)){
				ruta = "/jsp/abastecimiento/aba_Autorizacion.jsp?mensaje=usuarioInvalido";
				return ruta;
			}
			
			usuario = usuarioDao.validarUsuario(sLogin, sClave);
			
			if (usuario != null) {

				IRol rol = new DRol();

				// obteniendo los roles
				rol.obtenerRol(usuario);
				
				//VALIDAR QUE SEA JEFE DE FIDELIZACIÓN
				for (int i=0;i< usuario.getListaRol().getTamanio();i++){
					BRol rolUsuario = (BRol)usuario.getListaRol().getElemento(i);
					if(rolUsuario.getCodigoRol() == Parametros.CODIGO_JEFE_FIDELIZACION){
						ruta = mostrarIngresoOrden(request,true);
						return  ruta;
					}
				}
				ruta = "/jsp/abastecimiento/aba_Autorizacion.jsp?mensaje=usuarioNoJefe";
			} else {
				ruta = "/jsp/abastecimiento/aba_Autorizacion.jsp?mensaje=contraseniaInvalido";
			}
			
		}catch (Exception e) {
			System.out.println(
					"Proyecto: "
						+ Parametros.S_APP_NOMBRE
						+ "; Clase: SAbastecimiento; "
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
	/**
	 * Muestra la pagina de ingreso de orden o de validacion segun sea el caso
	 * @param request objeto de solicitud http, tipo HttpServletRequest. 
	 * @param validado indica si se ha realizado la validación por 
	 * 			parte del jefe superior, tipo boolean.
	 * @return ruta de la pagina a mostrar.
	 */
	private String mostrarIngresoOrden(HttpServletRequest request, boolean validado) {
		String ruta="";
		
		try {
			boolean mostrarValidacion = true;
			int numeroSemana;
			int numeroDia;
			Lista listadoProducto=null;
			String fecha = ConnectDS.obtenerFecha();
			IProducto daoProducto = new DProducto();
			
			
			//obteniendo el nuemro de la semana
			numeroSemana = Integer.parseInt(ConnectDS.obtenerFechaFormato(ConnectDS.FORMATO_NUMERO_SEMANA));
			//obteneiendo el numero del dia
			numeroDia = Integer.parseInt(ConnectDS.obtenerFechaFormato(ConnectDS.FORMATO_NUMERO_DIA_SEMANA));

			if(validado ||( Parametros.NUMERO_DIA_GENERAR_PEDIDO == numeroDia && Parametros.NUMERO_SEMANA_GENERAR_PEDIDO == numeroSemana)){
				listadoProducto = daoProducto.obtenerListadoProductos(true);
				ruta = "/jsp/abastecimiento/aba_GenerarOrden.jsp";
				mostrarValidacion = false;
			}else{
				
				ruta = "/jsp/abastecimiento/aba_Autorizacion.jsp?mensaje=aprobacion";
			}
			request.setAttribute("mostrarValidacion", new Boolean(mostrarValidacion).toString());
			request.setAttribute("fecha", fecha);
			request.setAttribute("listadoProducto", listadoProducto);

			
		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: SAbastecimiento; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}

		return ruta;
	}
}
