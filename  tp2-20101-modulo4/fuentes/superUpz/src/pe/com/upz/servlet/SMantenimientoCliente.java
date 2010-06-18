/**
 * Resumen.
 * Objeto                     : SMantenimientoCliente.
 * Descripción                : Servlet para lel mantenimiento de los clientes del modulo de fidelizacion.
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

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.controlador.CMantenimiento;
import pe.com.upz.controlador.CMantenimientoCliente;
import pe.com.upz.dao.DCliente;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.daoInterface.IUbigeo;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Servlet para lel mantenimiento de los clientes del modulo de fidelizacion.
 *
 */
public class SMantenimientoCliente extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
try {
	String operacion = (String) request.getParameter("hddOperacion");
	String ruta = "";
	BUsuario usuario = ((BUsuario) request.getSession().getAttribute(
			"usuarioSesion"));
	short indicador = -1;
	if (operacion.equals("ingresoMantenerClientes")) {
		ruta = iniciarListadoClientes(request);
	} else if (operacion.equals("nuevoCliente")) {
		ruta = inicioNuevoActualizaCliente(request);
	}else if (operacion.equals("almacenarCliente")) {
		ruta = almacenarCliente(usuario,request);
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
	private String iniciarListadoClientes(HttpServletRequest request){
		String ruta = "";
		try {
			String valorAux="";
			String valorAux2="";
			String valorAux3="";
			String pagina = request.getParameter("hddPagina");

			if (pagina == null || pagina.equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if(filtro == 1){
				valorAux = request.getParameter("txtDNIBuscar");
			}else if(filtro == 2){
				valorAux = request.getParameter("txtNombreBuscar");
				valorAux2 = request.getParameter("txtPaternoBuscar");
				valorAux3 = request.getParameter("txtMaternoBuscar");
			}
			
			Lista listadoCliente = null;
			
			CMantenimientoCliente cMantenimiento = new CMantenimientoCliente();
			listadoCliente = cMantenimiento.obtenerListadoClientes(true, filtro, valorAux, valorAux2, valorAux3);

			request.setAttribute("filtroPagina", filtro+"");
			request.setAttribute("valorAux", valorAux);
			request.setAttribute("valorAux2", valorAux2);
			request.setAttribute("valorAux3", valorAux3);
			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoCliente", listadoCliente);
			request.setAttribute("mantenimiento", "1");
			request.setAttribute("mostrar", "1");

			ruta = "/jsp/maestroCliente/mae_ListadoClientes.jsp";
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
	private String inicioNuevoActualizaCliente(HttpServletRequest request) {
		String ruta = "";
		try {
			Lista listaDepartamento;
			
			IUbigeo dUbigeo = new DUbigeo();
			
			listaDepartamento = dUbigeo.obtenerDepartamentos();
			request.setAttribute("listaDepartamento", listaDepartamento);
			ruta = "/jsp/maestroCliente/mae_MantenerCliente.jsp";
		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + this.getClass().getName() + ";"
					+ "; Parametros=" + Parametros.URL + ":"
					+ Parametros.USUARIO + ":" + Parametros.CLAVE
					+ "; Mensaje:" + e);
		}
		return ruta;
	}
	private String almacenarCliente(BUsuario usuario,HttpServletRequest request) {
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BCliente cliente;
			String nombre;
			String paterno;
			String materno;
			String direccion;
			String numDocumento;
			String correo;
			String telefono1;
			String telefono2;
			String departamento;
			String provincia;
			String distrito;
			
			nombre = request.getParameter("txtNombre");
			paterno = request.getParameter("txtApellidoPaterno");
			materno = request.getParameter("txtApellidoMaterno");
			direccion = request.getParameter("txtDireccion");
			numDocumento = request.getParameter("txtNumeroDocumento");
			correo = request.getParameter("txtEMail");
			telefono1 = request.getParameter("txtTelefono");
			telefono2 = request.getParameter("txtCelular");
			departamento = request.getParameter("selDepartamento");
			provincia = request.getParameter("selProvincia");
			distrito = request.getParameter("selDistrito");
			
			cliente = new BCliente();
			cliente.setNumeroDocumento(numDocumento);
			cliente.setNombre(nombre);
			cliente.setApellidoPaterno(paterno);
			cliente.setApellidoPaterno(materno);
			cliente.setCorreo(correo);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono1);
			cliente.setTelefonoDos(telefono2);
			BUbigeo ubigeo = new BUbigeo();
			IUbigeo daoUbigeo = new DUbigeo();
			ubigeo.setCodigo(daoUbigeo.obtenerCodigoUbigeo(departamento, provincia, distrito));
			cliente.setUbigeo(ubigeo);
			
			ICliente daoCliente = new DCliente();
			
			daoCliente.almacenarCliente(conn, cliente, usuario);
			
			ConnectDS.aceptarTrasaccion(conn);
			
			ruta = "/jsp/maestroCliente/mae_MantenerCliente.jsp";
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
