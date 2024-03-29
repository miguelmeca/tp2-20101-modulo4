/**
 * Resumen.
 * Objeto                     : SMantenimientoCliente.
 * Descripci�n                : Servlet para lel mantenimiento de los clientes del modulo de fidelizacion.
 * Fecha de Creaci�n          : 02/06/2010.
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
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTarjetaFidelizacion;
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
			BSucursal sucursal = ((BSucursal) request.getSession().getAttribute(
			"sucursalSesion"));
			short indicador = -1;
			if (operacion.equals("ingresoMantenerClientes")) {
				ruta = iniciarListadoClientes(request);
			} else if (operacion.equals("nuevoCliente")) {
				ruta = inicioNuevoActualizaCliente(request);
			} else if (operacion.equals("almacenarCliente")) {
				ruta = almacenarCliente(usuario, request);
			} else if (operacion.equals("buscarCliente")) {
				ruta = iniciarListadoClientes(request);
			} else if (operacion.equals("ingresoMantenerCuenta")) {
				ruta = iniciarListadoCuenta(request);
			} else if (operacion.equals("nuevoCuenta")) {
				ruta = inicioNuevoActualizaCuenta(request);
			}else if (operacion.equals("asignarTarjeta")) {
				ruta = asignarTarjeta(request);
			}else if (operacion.equals("almacenarCuenta")) {
				ruta = almacenarCuenta(usuario, sucursal, request);
			}else if (operacion.equals("buscarCuenta")) {
				ruta = iniciarListadoCuenta(request);
			}
			//gonza
			
			else if (operacion.equals("almacenarClienteModificado")) {
				ruta = almacenarClienteModificado(usuario, request);
			}else if (operacion.equals("iniciarModificarCliente")) {
				ruta = iniciarModificarCliente(request);
			}else if(operacion.equals("ingresoModificarCuenta")) {
				ruta = iniciarModificarCuenta(request);
			}else if(operacion.equals("eliminarCuenta")) {
				ruta = eliminarCuenta(request,usuario,sucursal);
			}else if(operacion.equals("agregarAdicional")){
				ruta = agregarAdicional(request);
			}else if(operacion.equals("modificarAdicional")){
				ruta = modificarAdicional(request);
			}else if(operacion.equals("almacenarUnAdicional")){
				ruta = anadirUnAdicional(request);
			}else if(operacion.equals("modificarUnAdicional")){
				ruta = modificarUnAdicional(request);
			}else if(operacion.equals("almacenarCuentaModificada")){
				ruta = almacenarCambios(request,usuario,sucursal);
			}else if(operacion.equals("eliminarCliente")) {
				ruta = eliminarCliente(request,usuario,sucursal);
			}
			//gonza
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
	 * Inicia el listado de cuentas.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String iniciarListadoCuenta(HttpServletRequest request) {
		return iniciarListadoCuenta(request,false);
	}
	/**
	 * Inicia el listado de cuentas.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param verMantenimiento indica si se muestra como mantenimiento,  tipo boolean.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */	
	private String iniciarListadoCuenta(HttpServletRequest request,boolean verMantenimiento) {
		String ruta = "";
		try {
			String valorAux = "";
			String valorAux2 = "";
			String pagina = request.getParameter("hddPagina");
			String mostrarMantenimiento = request.getParameter("hddMantenimiento");
			if (verMantenimiento || mostrarMantenimiento == null) {
				mostrarMantenimiento = "1";
			}
			
			if (pagina == null || pagina.equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request
					.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if (filtro == 1) {
				valorAux = request.getParameter("txtDNIBuscar");
			} else if (filtro == 2) {
				valorAux2 = request.getParameter("txtNumeroTarjeta");
			}

			Lista listadoCuenta = null;

			CMantenimientoCliente cMantenimiento = new CMantenimientoCliente();
			listadoCuenta = cMantenimiento.obtenerListadoCuenta(true,
					filtro, valorAux, valorAux2);

			request.setAttribute("filtroPagina", filtro + "");
			request.setAttribute("valorAux", valorAux);
			request.setAttribute("valorAux2", valorAux2);
			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoCuenta", listadoCuenta);
			request.setAttribute("mantenimiento", mostrarMantenimiento);
			//request.setAttribute("mostrar", "1");

			ruta = "/jsp/maestroCliente/mae_ListadoCuenta.jsp?mostrar="+mostrarMantenimiento;
		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: " + this.getClass().getName() + ";"
					+ "; Parametros=" + Parametros.URL + ":"
					+ Parametros.USUARIO + ":" + Parametros.CLAVE
					+ "; Mensaje:" + e);
		}
		return ruta;
	}
	
	private String iniciarListadoClientes(HttpServletRequest request) {
		return iniciarListadoClientes(request,false);
	}
	
	/**
	 * Inicia el listado de clientes.
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param verMantenimiento
	 *           indica si es mantnimiento.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String iniciarListadoClientes(HttpServletRequest request,boolean verMantenimiento) {
		String ruta = "";
		try {
			String valorAux = "";
			String valorAux2 = "";
			String valorAux3 = "";
			String pagina = request.getParameter("hddPagina");
			String mostrarMantenimiento = request.getParameter("hddMantenimiento");
			if (verMantenimiento || mostrarMantenimiento == null) {
				mostrarMantenimiento = "1";
			}
			if (pagina == null || pagina.equals("")) {
				pagina = "1";
			}

			String filtroPagina = (String) request
					.getParameter("selTipoBusqueda");
			int filtro = Integer.parseInt(filtroPagina == null ? "0"
					: filtroPagina);

			if (filtro == 1) {
				valorAux = request.getParameter("txtDNIBuscar");
			} else if (filtro == 2) {
				valorAux = request.getParameter("txtNombreBuscar");
				valorAux2 = request.getParameter("txtPaternoBuscar");
				valorAux3 = request.getParameter("txtMaternoBuscar");
			}

			Lista listadoCliente = null;

			CMantenimientoCliente cMantenimiento = new CMantenimientoCliente();
			listadoCliente = cMantenimiento.obtenerListadoClientes(true,
					filtro, valorAux, valorAux2, valorAux3);

			request.setAttribute("filtroPagina", filtro + "");
			request.setAttribute("valorAux", valorAux);
			request.setAttribute("valorAux2", valorAux2);
			request.setAttribute("valorAux3", valorAux3);
			request.setAttribute("pagina", pagina);
			request.setAttribute("listadoCliente", listadoCliente);
			request.setAttribute("mantenimiento", mostrarMantenimiento);
			//request.setAttribute("mostrar", "0");

			ruta = "/jsp/maestroCliente/mae_ListadoClientes.jsp?mostrar="+mostrarMantenimiento;
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

	/**
	 * Almacena cliente nuevo.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarCliente(BUsuario usuario, HttpServletRequest request) {
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
			cliente.setApellidoMaterno(materno);
			cliente.setCorreo(correo);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono1);
			cliente.setTelefonoDos(telefono2);
			BUbigeo ubigeo = new BUbigeo();
			IUbigeo daoUbigeo = new DUbigeo();
			ubigeo.setCodigo(daoUbigeo.obtenerCodigoUbigeo(departamento,
					provincia, distrito));
			cliente.setUbigeo(ubigeo);

			CMantenimientoCliente daoCliente = new CMantenimientoCliente();

			daoCliente.almacenarCliente(conn, cliente, usuario);

			ConnectDS.aceptarTrasaccion(conn);
			request.setAttribute("mensajeMantenimiento", "Se ha Creado el cliente.");
			ruta = iniciarListadoClientes(request, true);
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
	 * Muetra la pagina de nueva cuenta.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String inicioNuevoActualizaCuenta(HttpServletRequest request) {
		String ruta = "";
		try {
			
			ruta = "/jsp/maestroCliente/mae_MantenerCuenta.jsp";
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
	 * Muetra la pagina de tarjeta de fidelizacion.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String asignarTarjeta(HttpServletRequest request) {
		String ruta = "";
		try {
			
			ruta = "/jsp/maestroCliente/mae_AsignarTarjeta.jsp?mostrar=0";
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
	 * Almacena la cuenta de un cliente.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tio BSucursal.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarCuenta(BUsuario usuario, BSucursal sucursal, HttpServletRequest request) {
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BCuenta cuenta;
			BTarjetaFidelizacion tarjetaFidel;
			BCliente cliente;
			Lista listaTarjeta;
			int codigoCliente;
			String numeroTarjeta;

			codigoCliente = Integer.parseInt(request.getParameter("hddCodigoCliente"));
			numeroTarjeta = request.getParameter("txtNumeroTarjeta");
			cliente = new BCliente();
			cliente.setCodigo(codigoCliente);
			tarjetaFidel = new BTarjetaFidelizacion();
			listaTarjeta = new Lista();
	
			tarjetaFidel.setCliente(cliente);
			tarjetaFidel.setTipoCliente(1);
			tarjetaFidel.setNumero(numeroTarjeta);
			listaTarjeta.setElemento(tarjetaFidel);

			cuenta = new BCuenta();
			cuenta.setTarjeta(listaTarjeta);
			
			CMantenimientoCliente daoCliente = new CMantenimientoCliente();

			daoCliente.almacenarCuenta(conn, cuenta, usuario,sucursal);

			ConnectDS.aceptarTrasaccion(conn);
			request.setAttribute("mensajeMantenimiento", "Se ha creado la cuenta.");
			ruta = iniciarListadoCuenta(request);
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
	 * Muestra la pantalla de modificar cuenta.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String iniciarModificarCuenta(HttpServletRequest request){
		String ruta = "";
		try {
			String codigoCuenta=request.getParameter("hddCodigoSeleccionado");
			String numTarjeta=request.getParameter("hddTarjeta"+codigoCuenta);
			String codigoCliente=request.getParameter("hddCodigoCliente"+codigoCuenta);
			String nombreCliente=request.getParameter("hddApellidoPaterno"+codigoCuenta)+" ";
			nombreCliente=nombreCliente+request.getParameter("hddApellidoMaterno"+codigoCuenta)+", ";
			nombreCliente=nombreCliente+request.getParameter("hddNombre"+codigoCuenta);
			Lista listadoCuenta;
			CMantenimientoCliente cMantenimientoCliente = new CMantenimientoCliente();
			listadoCuenta = cMantenimientoCliente.obtenerListaClientesAdicionales(Integer.parseInt(codigoCuenta));
			
			//request.setAttribute("listadoCuenta", listadoCuenta);
			request.getSession().setAttribute("listadoCuenta", listadoCuenta);
			
			request.setAttribute("codigoCliente", codigoCliente);
			request.setAttribute("codigoCuenta", codigoCuenta);
			request.setAttribute("numTarjeta", numTarjeta);
			request.setAttribute("nombreCliente", nombreCliente);
			ruta = "/jsp/maestroCliente/mae_ModificarCuenta.jsp";
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
	 * Elimina cuenta.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tio BSucursal.
	 * @return
	 */
	private String eliminarCuenta(HttpServletRequest request,BUsuario usuario, BSucursal sucursal){
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			String codigoCuenta=request.getParameter("hddCodigoSeleccionado");
						
			CMantenimientoCliente daoCliente = new CMantenimientoCliente();
			daoCliente.eliminarCuentaCliente(conn,Integer.parseInt(codigoCuenta),usuario,sucursal);
			ConnectDS.aceptarTrasaccion(conn);
			
			request.setAttribute("mensajeMantenimiento", "Se ha dado de baja a la cuenta.");
			ruta = iniciarListadoCuenta(request);
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
	 * Elimina cliente.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tio BSucursal.
	 * @return
	 */
	private String eliminarCliente(HttpServletRequest request,BUsuario usuario, BSucursal sucursal){
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			String codigoCliente=request.getParameter("hddCodigoSeleccionado");
						
			CMantenimientoCliente daoCliente = new CMantenimientoCliente();
			daoCliente.eliminarCliente(conn,Integer.parseInt(codigoCliente),usuario);
			ConnectDS.aceptarTrasaccion(conn);
			
			request.setAttribute("mensajeMantenimiento", "Se ha dado de baja al cliente.");
			ruta = iniciarListadoClientes(request);
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
	 * Muetra la pagina de agregar adicional.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String agregarAdicional(HttpServletRequest request) {
		String ruta = "";
		try {
			//obtiene los datos del principal
			String numTarjeta= request.getParameter("txtNumeroTarjeta");
			String nombreCliente= request.getParameter("txtCliente");
			String codigoCliente = request.getParameter("hddCodigoCliente");
			
			request.setAttribute("numTarjeta", numTarjeta);
			request.setAttribute("nombreCliente", nombreCliente);
			request.setAttribute("codigoCliente", codigoCliente);
			
			ruta = "/jsp/maestroCliente/mae_MantenerCuentaAdicional.jsp";
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
	 * Muetra la pagina de modificar adicional.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String modificarAdicional(HttpServletRequest request) {
		String ruta = "";
		try {
			//obtiene los datos del principal
			String numTarjeta= request.getParameter("txtNumeroTarjeta");
			String nombreCliente= request.getParameter("txtCliente");
			String codigoCliente = request.getParameter("hddCodigoCliente");
			
			request.setAttribute("numTarjeta", numTarjeta);
			request.setAttribute("nombreCliente", nombreCliente);
			request.setAttribute("codigoCliente", codigoCliente);
			
			ruta = "/jsp/maestroCliente/mae_ModificarCuentaAdicional.jsp";
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
	 * Agrega un adicional.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String anadirUnAdicional(HttpServletRequest request) {
		String ruta = "";
		try {
			Lista listadoCuenta = (Lista)request.getSession().getAttribute("listadoCuenta");
			BCuenta cuenta;
			BTarjetaFidelizacion tarjetaFidel;
			BCliente cliente;
			Lista listaTarjeta;
			int codigoCliente;
			String numeroTarjeta;
			//datos del titular
			String numTarjetaTitular= request.getParameter("hddNumeroTarjeta");
			String nombreClienteTitular= request.getParameter("hddNombreCliente");
			String codigoClienteTitular = request.getParameter("hddCodigoCli");
			String codigoCuenta = request.getParameter("hddCodigoCuenta");
			//datos del adicional
			String nombreAdicional = request.getParameter("hddClienteNombre");
			String paternoAdicional = request.getParameter("hddClientePaterno");
			String maternoAdicional = request.getParameter("hddClienteMaterno");
			codigoCliente = Integer.parseInt(request.getParameter("hddCodigoCliente"));
			numeroTarjeta = request.getParameter("txtNumeroTarjeta");
			cliente = new BCliente();
			cliente.setCodigo(codigoCliente);
			cliente.setNombre(nombreAdicional);
			cliente.setApellidoPaterno(paternoAdicional);
			cliente.setApellidoMaterno(maternoAdicional);
			tarjetaFidel = new BTarjetaFidelizacion();
			listaTarjeta = new Lista();
			boolean encontrado = false;
			if(codigoCliente == Integer.parseInt(codigoClienteTitular)){
				encontrado=true;
				request.setAttribute("mensajeMantenimiento", "El cliente ya se encontraba en la cuenta.");
			}
			
			for(int i=0 ;i< listadoCuenta.getTamanio();i++){
				BTarjetaFidelizacion tar = (BTarjetaFidelizacion)listadoCuenta.getElemento(i);
				if(tar.getCliente().getCodigo() == codigoCliente){
					encontrado = true;
					request.setAttribute("mensajeMantenimiento", "El cliente ya se encontraba en la cuenta.");
				}
			}
			
			tarjetaFidel.setCliente(cliente);
			tarjetaFidel.setTipoCliente(2);
			tarjetaFidel.setNumero(numeroTarjeta);
			if(!encontrado){
				listadoCuenta.setElemento(tarjetaFidel);
				
			}

			request.getSession().setAttribute("listadoCuenta", listadoCuenta);
			request.setAttribute("numTarjeta", numTarjetaTitular);
			request.setAttribute("nombreCliente", nombreClienteTitular);
			request.setAttribute("codigoCuenta", codigoCuenta);
			request.setAttribute("codigoCliente", codigoClienteTitular);
						
			ruta = "/jsp/maestroCliente/mae_ModificarCuenta.jsp";
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
	 * Almacena cambios de cuenta adicional.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return ruta de pagina a mostrar, tipo String.
	 */
	private String modificarUnAdicional(HttpServletRequest request) {
			String ruta = "";
			try {
				Lista listadoCuenta = (Lista)request.getSession().getAttribute("listadoCuenta");
				BTarjetaFidelizacion tarjetaFidel;
				BCliente cliente;
				int codigoCliente;
				String numeroTarjeta;
				//datos del titular
				String numTarjetaTitular= request.getParameter("hddNumeroTarjeta");
				String nombreClienteTitular= request.getParameter("hddNombreCliente");
				String codigoClienteTitular = request.getParameter("hddCodigoCli");
				String codigoCuenta = request.getParameter("hddCodigoCuenta");
				//datos del adicional
				/*String nombreAdicional = request.getParameter("hddClienteNombre");
				String paternoAdicional = request.getParameter("hddClientePaterno");
				String maternoAdicional = request.getParameter("hddClienteMaterno");*/
				codigoCliente = Integer.parseInt(request.getParameter("hddCodigoCliente"));
				numeroTarjeta = request.getParameter("txtNumeroTarjeta");
				
				for(int i=0; i<listadoCuenta.getTamanio();i++){
					tarjetaFidel = (BTarjetaFidelizacion)listadoCuenta.getElemento(i);
					
					if(tarjetaFidel.getCliente().getCodigo() == codigoCliente){
						((BTarjetaFidelizacion)listadoCuenta.getElemento(i)).setNumero(numeroTarjeta);
					}
				}

				request.getSession().setAttribute("listadoCuenta", listadoCuenta);
				
				request.setAttribute("numTarjeta", numTarjetaTitular);
				request.setAttribute("nombreCliente", nombreClienteTitular);
				request.setAttribute("codigoCuenta", codigoCuenta);
				request.setAttribute("codigoCliente", codigoClienteTitular);
							
				ruta = "/jsp/maestroCliente/mae_ModificarCuenta.jsp";
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
	 * Almacena cambios de cuenta.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return ruta de pagina a mostrar, tipo String.
	 */
	private String almacenarCambios(HttpServletRequest request,BUsuario usuario,BSucursal sucursal){
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			Lista listadoCuenta = (Lista)request.getSession().getAttribute("listadoCuenta");
			BCuenta cuenta;
			BTarjetaFidelizacion tarjetaFidel;
			BCliente cliente;
			Lista listaTarjeta;
			int codigoCliente;
			String numeroTarjeta;
			String codigoCuenta;

			codigoCliente = Integer.parseInt(request.getParameter("hddCodigoCliente"));
			numeroTarjeta = request.getParameter("txtNumeroTarjeta");
			codigoCuenta = request.getParameter("hddCodigoCuenta");
			cliente = new BCliente();
			cliente.setCodigo(codigoCliente);
			tarjetaFidel = new BTarjetaFidelizacion();
			listaTarjeta = new Lista();
	
			tarjetaFidel.setCliente(cliente);
			tarjetaFidel.setTipoCliente(1);
			tarjetaFidel.setNumero(numeroTarjeta);
			listaTarjeta.setElemento(tarjetaFidel);

			cuenta = new BCuenta();
			cuenta.setCodigo(Integer.parseInt(codigoCuenta));
			cuenta.setTarjeta(listaTarjeta);
			
			CMantenimientoCliente daoCliente = new CMantenimientoCliente();

			daoCliente.almacenarCambiosCuenta(conn, cuenta, usuario, listadoCuenta, sucursal);

			ConnectDS.aceptarTrasaccion(conn);
			
			request.setAttribute("mensajeMantenimiento", "Se ha Modificado la cuenta.");
			ruta = iniciarListadoCuenta(request,true);
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
	 * Muetra la pagina de cambiar cliente.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String iniciarModificarCliente(HttpServletRequest request) {
		String ruta = "";
		try {
			int codigo = Integer.parseInt(request.getParameter("hddCodigoSeleccionado"));
			CMantenimientoCliente daoCliente = new CMantenimientoCliente();
			
			Lista listaDepartamento,listaProvincia,listaDistrito;

			IUbigeo dUbigeo = new DUbigeo();
			BCliente cliente = daoCliente.obtenerCliente(codigo);
			BUbigeo ubigeo = daoCliente.obtenerUbigeoDeCliente(cliente);
			
			listaDepartamento = dUbigeo.obtenerDepartamentos();
			listaProvincia = dUbigeo.obtenerProvinciasDeDepartamento(ubigeo.getDepartamento());
			listaDistrito = dUbigeo.obtenerDistritosDeprovincia(ubigeo.getDepartamento(), ubigeo.getProvincia());
			
			request.setAttribute("cliente", cliente);
			request.setAttribute("ubigeo", ubigeo);
			request.setAttribute("listaDepartamento", listaDepartamento);
			request.setAttribute("listaProvincia", listaProvincia);
			request.setAttribute("listaDistrito", listaDistrito);
			
			ruta = "/jsp/maestroCliente/mae_ModificarCliente.jsp";
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
	 * Almacena cliente modificado.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @return ruta de la pagina a mostrar, tipo String.
	 */
	private String almacenarClienteModificado(BUsuario usuario, HttpServletRequest request) {
		String ruta = "";
		Connection conn = null;
		try {
			conn = ConnectDS.obtenerConeccion();
			conn.setAutoCommit(false);
			BCliente cliente;
			String codigo;
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
			
			codigo = request.getParameter("hddCodigo");
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
			cliente.setCodigo(Integer.parseInt(codigo));
			cliente.setNumeroDocumento(numDocumento);
			cliente.setNombre(nombre);
			cliente.setApellidoPaterno(paterno);
			cliente.setApellidoMaterno(materno);
			cliente.setCorreo(correo);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono1);
			cliente.setTelefonoDos(telefono2);
			BUbigeo ubigeo = new BUbigeo();
			IUbigeo daoUbigeo = new DUbigeo();
			ubigeo.setCodigo(daoUbigeo.obtenerCodigoUbigeo(departamento,
					provincia, distrito));
			cliente.setUbigeo(ubigeo);

			CMantenimientoCliente daoCliente = new CMantenimientoCliente();

			daoCliente.almacenarClienteModificado(conn, cliente, usuario);

			ConnectDS.aceptarTrasaccion(conn);

			ruta = iniciarListadoClientes(request,true);
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
