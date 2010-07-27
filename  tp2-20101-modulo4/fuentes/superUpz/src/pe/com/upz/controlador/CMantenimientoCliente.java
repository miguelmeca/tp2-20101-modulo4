/**
 * Resumen.
 * Objeto                     : CMantenimientoCliente.
 * Descripción                : Clase controladora para el modulo de clientes. 
 * Fecha de Creación          : 15/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import pe.com.upz.bean.BCliente;
import pe.com.upz.bean.BCuenta;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTarjetaFidelizacion;
import pe.com.upz.bean.BUbigeo;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DCliente;
import pe.com.upz.dao.DCuenta;
import pe.com.upz.dao.DProducto;
import pe.com.upz.dao.DTarjetaFidelizacion;
import pe.com.upz.dao.DUbigeo;
import pe.com.upz.daoInterface.ICliente;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.daoInterface.ITarjetaFidelizacion;
import pe.com.upz.daoInterface.IUbigeo;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de clientes. 
 *
 */
public class CMantenimientoCliente {
	/**
	 * Obtiene el listado de clientes segun un filtro de busqueda.
	 * @param soloActivos indica si solo se obtiene los productos activos.
	 * @param filtro opcion para los filtro. 0 = sin filtro, 1 = fitro por tipo, tipo int.
	 * @param valorAux valor para realizar la busqueda con filtro, tipo String.
	 * @return listadoProducto listado con los productos, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoClientes(boolean soloActivos, int filtro,String valorAux,String valorAux2,String valorAux3)throws SQLException{
		Lista listadoCliente=null;
		ICliente daoCliente = new DCliente();
		
		listadoCliente = daoCliente.obtenerListadoClientes(soloActivos, filtro, valorAux, valorAux2, valorAux3);
		
		return listadoCliente;
	}
	
	/**
	 * Almacena un cliente nuevo.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param cliente cliente a almacenar, tipo BCliente.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return codigo de cliente almacenado, tipo int.
	 * @throws SQLException
	 */
	public int almacenarCliente(Connection conn, BCliente cliente,BUsuario usuario)throws SQLException{
		ICliente daoCliente = new DCliente();

		return daoCliente.almacenarCliente(conn, cliente, usuario);
	}
	/**
	 * Obtiene el lsitado de cuentas.
	 * @param soloActivos indica obtener solo los activos, tipo boolean.
	 * @param filtro indica el filtro a aplicar, tipo int.
	 * @param valorAux valor del filtro 1, tipo String.
	 * @param valorAux2 valor del filtro 2, tipo String.
	 * @return listado de clientes de cuenta, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListadoCuenta(boolean soloActivos, int filtro,String valorAux,String valorAux2)throws SQLException{
		Lista listadoCliente=null;
		ICuenta daoCliente = new DCuenta();
		
		listadoCliente = daoCliente.obtenerListadoCuenta(soloActivos, filtro, valorAux, valorAux2);
		
		return listadoCliente;
	}
	/**
	 * Almacena una nueva cuenta de cliente.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param cuenta cuenta a almacenar, tipo BCuenta
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param sucursal sucursal de la sesion, tipo BSucursal.
	 * @return codigoCuenta codigo generado, tipo int.
	 * @throws SQLException  captura excepciones tipo SQL.
	 */
	public int almacenarCuenta(Connection conn, BCuenta cuenta, BUsuario usuario, BSucursal sucursal)throws SQLException{
		ICuenta daoCliente = new DCuenta();
		ITarjetaFidelizacion tarjetaFidel = new DTarjetaFidelizacion();
		int codigoCuenta = daoCliente.almacenarCuenta(conn, cuenta, usuario,sucursal);
		
		tarjetaFidel.almacenarTarjeta(conn, (BTarjetaFidelizacion)cuenta.getTarjeta().getElemento(0), usuario, sucursal, codigoCuenta);
		
		return codigoCuenta;
	}
	//gonza
	/**
	 * Elimina los datos de cliente.
	 * @param conn connexion a BD, tipo Connection.
	 * @param cliente codigo de cliente, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public void eliminarCliente(Connection conn, int cliente, BUsuario usuario)throws SQLException{
		ICliente daoCliente = new DCliente();
		daoCliente.eliminarCliente(conn, cliente, usuario);
	}
	/**
	 * Elimina los datos de cliente.
	 * @param conn connexion a BD, tipo Connection.
	 * @param cliente codigo de cliente, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public BCliente obtenerCliente(int codigo)throws SQLException{
		ICliente daoCliente = new DCliente();
		return daoCliente.obtenerCliente(codigo);
	}
	/**
	 * Elimina los datos de cliente.
	 * @param conn connexion a BD, tipo Connection.
	 * @param cliente codigo de cliente, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public BUbigeo obtenerUbigeoDeCliente(BCliente cliente)throws SQLException{
		IUbigeo daoUbigeo = new DUbigeo();
		return daoUbigeo.obtenerUbigeo(cliente.getUbigeo().getCodigo());
	}
	/**
	 * Elimina los datos asociados a la cuenta.
	 * @param conn connexion a BD, tipo Connection.
	 * @param codCuenta codigo de la cuenta, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param  sucursal sucrusal de eliminacion, tipo BSucursal.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public void eliminarCuentaCliente(Connection conn, int codCuenta, BUsuario usuario, BSucursal sucursal)throws SQLException{
		ICuenta daoCuenta = new DCuenta();
		ITarjetaFidelizacion daoTarjeta = new DTarjetaFidelizacion();
		// elimina las tarjetas
		daoTarjeta.eliminarTarjetasCuenta(conn, codCuenta, usuario);
		//elimina las cuentas
		daoCuenta.eliminarCuenta(conn, codCuenta, usuario,sucursal);
	}
	
	/**
	 * Obtiene listado de clientes adicionales de una cuenta.
	 * @param codigoCuenta codigo de la cuenta, tipo int.
	 * @return lsiatdo de clientes adicionales, tipo Lista.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public Lista obtenerListaClientesAdicionales(int codigoCuenta)throws SQLException{
		Lista listadoCliente=null;
		ICuenta daoCliente = new DCuenta();
		
		listadoCliente = daoCliente.obtenerListadoCuentaAdicional(codigoCuenta);
		
		return listadoCliente;
	}
	/**
	 * Almacena cambios de una cuenta de cliente.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param cuenta cuenta a almacenar, tipo BCuenta
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return codigoCuenta codigo generado, tipo int.
	 * @throws SQLException  captura excepciones tipo SQL.
	 */
	public int almacenarCambiosCuenta(Connection conn, BCuenta cuenta, BUsuario usuario, Lista listaAdicionales, BSucursal sucursal)throws SQLException{
		ICuenta daoCliente = new DCuenta();
		ITarjetaFidelizacion tarjetaFidel = new DTarjetaFidelizacion();
		//actualiza la tarjeta del cliente titular
		daoCliente.actualizarTarjetaClienteTitular(conn, (BTarjetaFidelizacion)cuenta.getTarjeta().getElemento(0), usuario, sucursal, cuenta.getCodigo());
		BTarjetaFidelizacion tarjeta;
		//primero debo eliminarlos
		
		for(int i=0; i< listaAdicionales.getTamanio();i++){
			tarjeta = (BTarjetaFidelizacion)listaAdicionales.getElemento(i);
			if(tarjetaFidel.buscarClienteFidelizado(tarjeta.getCliente(),cuenta)){
				daoCliente.actualizarTarjetaClienteTitular(conn, tarjeta, usuario, sucursal, cuenta.getCodigo());
			}else{
				tarjetaFidel.almacenarTarjeta(conn, tarjeta, usuario, sucursal, cuenta.getCodigo());				
			}
			

		}
		return cuenta.getCodigo();
	}

	/**
	 * Almacena un cliente modificado.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param cliente cliente a almacenar, tipo BCliente.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @return codigo de cliente almacenado, tipo int.
	 * @throws SQLException
	 */
	public void almacenarClienteModificado(Connection conn, BCliente cliente,BUsuario usuario)throws SQLException{
		ICliente daoCliente = new DCliente();

		daoCliente.almacenarClienteModificado(conn, cliente, usuario);
		
	}
	//gonza
}
