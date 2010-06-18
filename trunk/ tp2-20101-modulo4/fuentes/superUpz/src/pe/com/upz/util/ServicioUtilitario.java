package pe.com.upz.util;

import javax.servlet.*;
import javax.servlet.http.*;

import pe.com.upz.bean.BUbigeo;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.IUbigeo;

public class ServicioUtilitario extends ServiceSession {

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

}
