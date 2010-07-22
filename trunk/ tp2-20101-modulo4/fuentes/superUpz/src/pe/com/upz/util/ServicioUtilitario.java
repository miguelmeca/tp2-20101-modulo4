/**
 * Resumen.
 * Objeto                     : ServicioUtilitario.
 * Descripción                : Clase que contiene llamadas a metodos generales.
 * Fecha de Creación          : 28/06/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.util;

import javax.servlet.*;
import javax.servlet.http.*;

import pe.com.upz.bean.BEquivalencia;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.dao.DCliente;
import pe.com.upz.dao.DCuenta;
import pe.com.upz.dao.DEquivalencia;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DTarjetaFidelizacion;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.daoInterface.IEquivalencia;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.daoInterface.ITarjetaFidelizacion;
import pe.com.upz.daoInterface.IUbigeo;

/**
 * Clase que contiene llamadas a metodos generales.
 *
 */
public class ServicioUtilitario extends ServiceSession {

	/**
	 * Metodo que obtiene el puntaje de una cuenta.
	 * 
	 * @param HttpServletRequest
	 *            Parametro de peticiones, tipo response.
	 * @param HttpServletResponse
	 *            Parametro de respuestas, tipo request.
	 * @return sRuta Cadena de aceptacion, tipo String.
	 */
	public String requestObtenerPuntajeCuenta(HttpServletRequest request,
			HttpServletResponse response) {
		String sRuta = "0";
		try {
						
			String codCuenta = (String) request
					.getParameter("codCuenta");

	
			
			ICuenta dCuenta = new DCuenta();
			
			sRuta = dCuenta.obtenerPuntajeCuenta(Integer.parseInt(codCuenta))+"";
			
			if (sRuta == null) {
				sRuta = "_NoExiste";
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return sRuta;
	}
	/**
	 * Metodo que obtiene el stock de un producto en sucursal.
	 * 
	 * @param HttpServletRequest
	 *            Parametro de peticiones, tipo response.
	 * @param HttpServletResponse
	 *            Parametro de respuestas, tipo request.
	 * @return sRuta Cadena de aceptacion, tipo String.
	 */
	
	public String requestObtenerStock(HttpServletRequest request,
			HttpServletResponse response) {
		String sRuta = "0";
		try {
			BProducto bProducto = new BProducto();
			BSucursal bSucursal = ((BSucursal) request.getSession().getAttribute(
			"sucursalSesion"));
			
			String codProd = (String) request
					.getParameter("codProducto");

			bProducto.setCodigo(Integer.parseInt(codProd));
			
			
			IProducto dProducto = new DProducto();
			
			sRuta = dProducto.obtenerStockLocalProducto(bProducto, bSucursal)+"";
			
			if (sRuta == null) {
				sRuta = "_NoExiste";
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return sRuta;
	}
	/**
	 * Metodo que obtiene los puntajes de un producto.
	 * 
	 * @param HttpServletRequest
	 *            Parametro de peticiones, tipo response.
	 * @param HttpServletResponse
	 *            Parametro de respuestas, tipo request.
	 * @return sRuta Cadena de aceptacion, tipo String.
	 */
	public String requestObtenerPuntaje(HttpServletRequest request,
			HttpServletResponse response) {
		String sRuta = "";
		try {
			BProducto bProducto = new BProducto();
			BEquivalencia equival;
			String codProd = (String) request
					.getParameter("codProducto");

			bProducto.setCodigo(Integer.parseInt(codProd));
			
			IEquivalencia dEquival = new DEquivalencia();
			
			equival = dEquival.obtenerEquivalenciaProducto(bProducto);
			
			if (equival == null) {
				sRuta = "_NoExiste";
			}else {
				// pasando las provincias a cadena
				StringBuffer cadena = new StringBuffer("_");
				if(equival.getCantidadPuntoUno() > 0){
					cadena.append(equival.getMontoUno()+"|");
					cadena.append(equival.getCantidadPuntoUno()+";");
				}
				if(equival.getCantidadPuntoDos() > 0){
					cadena.append(equival.getMontoDos()+"|");
					cadena.append(equival.getCantidadPuntoDos()+";");
				}
				if(equival.getCantidadPuntoDos() > 0){
					cadena.append(equival.getMontoTres()+"|");
					cadena.append(equival.getCantidadPuntoTres()+"");
				}
				sRuta = cadena.toString();
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return sRuta;
	}
	
	/**
	 * Metodo que obtiene las provincias en una cadena.
	 * 
	 * @param request
	 *            Parametro de peticiones, tipo HttpServletRequest.
	 * @param response
	 *            Parametro de respuestas, tipo HttpServletResponse.
	 * @return sRuta Cadena de aceptacion, tipo String.
	 */
	public String requestRefrescaProvincia(HttpServletRequest request,
			HttpServletResponse response) {

		String sRuta = "";
		try {
			BUbigeo provincia = new BUbigeo();
			String departamento = (String) request
					.getParameter("selDepartamento");

			IUbigeo dUbigeo = new DUbigeo();

			Lista listaProvincia = dUbigeo
					.obtenerProvinciasDeDepartamento(departamento);

			if (listaProvincia.getTamanio() == 0) {
				sRuta = "_NoExiste";
			}

			else {
				// pasando las provincias a cadena
				StringBuffer cadenaProvincia = new StringBuffer("_");
				for (int i = 0; i < listaProvincia.getTamanio(); i++) {
					provincia = (BUbigeo) listaProvincia.getElemento(i);
					cadenaProvincia.append(provincia.getProvincia());
					cadenaProvincia.append("|");
					cadenaProvincia.append(provincia.getNombre());
					if (i + 1 != listaProvincia.getTamanio()) {
						cadenaProvincia.append(";");
					}
				}

				sRuta = cadenaProvincia.toString();
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return sRuta;
	}

	/**
	 * Metodo que obtiene los distritos en una cadena.
	 * 
	 * @param HttpServletRequest
	 *            Parametro de peticiones, tipo response.
	 * @param HttpServletResponse
	 *            Parametro de respuestas, tipo request.
	 * @return sRuta Cadena de aceptacion, tipo String.
	 */
	public String requestRefrescaDistrito(HttpServletRequest request,
			HttpServletResponse response) {

		String sRuta = "";
		try {
			BUbigeo distrito = new BUbigeo();
			String departamento = (String) request
					.getParameter("selDepartamento");
			String provincia = (String) request.getParameter("selProvincia");

			IUbigeo dUbigeo = new DUbigeo();

			Lista listaProvincia = dUbigeo.obtenerDistritosDeprovincia(
					departamento, provincia);

			if (listaProvincia.getTamanio() == 0) {
				sRuta = "_NoExiste";
			}

			else {
				// pasando las provincias a cadena
				StringBuffer cadenaDistrito = new StringBuffer("_");
				for (int i = 0; i < listaProvincia.getTamanio(); i++) {
					distrito = (BUbigeo) listaProvincia.getElemento(i);
					cadenaDistrito.append(distrito.getProvincia());
					cadenaDistrito.append("|");
					cadenaDistrito.append(distrito.getNombre());
					if (i + 1 != listaProvincia.getTamanio()) {
						cadenaDistrito.append(";");
					}
				}

				sRuta = cadenaDistrito.toString();
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return sRuta;
	}
	public String requestObtenerDniRepetido(HttpServletRequest request,
			HttpServletResponse response) {
		String nombreCliente = "0";
		try {
			String dni = (String) request
					.getParameter("txtNumeroDocumento");
			String codigoCliente= (String) request
			.getParameter("hddCodigoCliente");
						
			ICliente daoCliente = new DCliente();
			
			nombreCliente = daoCliente.buscarDniRepetido(dni,(codigoCliente==null?-1:Integer.parseInt(codigoCliente)));
			
			if (nombreCliente == null) {
				nombreCliente = "_NoExiste";
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return nombreCliente;
	}
	public String requestObtenerTarjetaRepetido(HttpServletRequest request,
			HttpServletResponse response) {
		String numeroTarjeta = null;
		try {
			String numTarjeta = (String) request
					.getParameter("txtNumeroTarjeta");
									
			ITarjetaFidelizacion daoCliente = new DTarjetaFidelizacion();
			
			numeroTarjeta = daoCliente.buscarTarjetaRepetido(numTarjeta);
			
			if (numeroTarjeta == null) {
				numeroTarjeta = "_NoExiste";
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return numeroTarjeta;
	}
	public String requestValidarExisteClienteEnCuenta(HttpServletRequest request,
			HttpServletResponse response) {
		BTarjetaFidelizacion tarjeta = null;
		String clienteCuenta="";
		try {
			String codCliente = (String) request
					.getParameter("hddCodigoSeleccionado");
									
			ITarjetaFidelizacion daoCliente = new DTarjetaFidelizacion();
			
			tarjeta = daoCliente.buscarCuentaExistente(Integer.parseInt(codCliente));
			
			if (tarjeta == null) {
				clienteCuenta = "_NoExiste";
			}else{
				clienteCuenta = tarjeta.getCliente().getApellidoPaterno()+" "+
				tarjeta.getCliente().getApellidoMaterno()+" "+
				tarjeta.getCliente().getNombre()+" con DNI "+
				tarjeta.getCliente().getNumeroDocumento();
			}

		} catch (Exception e) {
			System.out.println("Proyecto: " + Parametros.S_APP_NOMBRE
					+ "; Clase: ServicioUtilitario; " + "; Parametros="
					+ Parametros.URL + ":" + Parametros.USUARIO + ":"
					+ Parametros.CLAVE + "; Mensaje:" + e);
		}
		return clienteCuenta;
	}
}
