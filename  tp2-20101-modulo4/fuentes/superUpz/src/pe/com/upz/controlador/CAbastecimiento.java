/**
 * Resumen.
 * Objeto                     : CAbastecimiento.
 * Descripción                : Clase controladora para el modulo de abastecimiento. 
 * Fecha de Creación          : 31/05/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import pe.com.upz.bean.BPedido;
import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.comun.ConnectDS;
import pe.com.upz.dao.DPedido;
import pe.com.upz.dao.DPedidoDetalle;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.IDetallePedido;
import pe.com.upz.daoInterface.IPedido;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;
import pe.com.upz.util.Parametros;

/**
 * Clase que controladora para el modulo de abastecimiento.
 * 
 */
public class CAbastecimiento {

	/**
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param codigo arreglo con los codigos de los productos a insertar tipo String[].
	 * @return listaProductos listado con los productos, tipo Lista.
	 */
	public Lista mostrarOrden (HttpServletRequest request,String[] codigo){
		Lista listaProductos= new Lista();
		BProducto producto;
		BTipoProducto tipo;
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
		return listaProductos;
	}
	
	/**
	 * @param request objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @param codigo arreglo con los codigos de los productos a insertar tipo String[].
	 * @param conn conexion a la base de datos.
	 * @return codigoGenerado godigo de la orden generada, tipo String.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public String alamcenarOrden(HttpServletRequest request, BUsuario usuario,
			String[] codigo, Connection conn) throws SQLException {
		String codigoGenerado="";
		Lista listaDetalle = new Lista();
		BProducto producto;
		BPedido pedido = new BPedido();
		BPedidoDetalle detalle;
		BTipoProducto tipo;

		for (int i = 0; i < codigo.length; i++) {
			detalle = new BPedidoDetalle();
			producto = new BProducto();
			producto.setCodigo(Integer.parseInt(codigo[i]));
			producto.setDescripcion(request.getParameter("hddDescripcion"
					+ codigo[i]));
			// producto.setStock(Integer.parseInt(request.getParameter("txtCantidad"+codigo[i])));
			tipo = new BTipoProducto();
			tipo.setDescripcion(request.getParameter("hddTipo" + codigo[i]));
			producto.setTipo(tipo);

			detalle.setProducto(producto);
			detalle.setCantidad(Integer.parseInt(request
					.getParameter("txtCantidad" + codigo[i])));

			listaDetalle.setElemento(detalle);
		}
		pedido.setListaDetalle(listaDetalle);

		conn = ConnectDS.obtenerConeccion();
		conn.setAutoCommit(false);
		IPedido pedidoDao = new DPedido();
		IDetallePedido detalleDao = new DPedidoDetalle();

		// almacena cabecera
		pedido.setCodigo(pedidoDao.almacenarOrden(conn, usuario));
		// alamcena detalle
		for (int i = 0; i < pedido.getListaDetalle().getTamanio(); i++) {
			detalleDao.almacenarDetalle(conn, pedido.getCodigo(),
					(BPedidoDetalle) pedido.getListaDetalle().getElemento(i),
					usuario);
		}
		codigoGenerado = pedido.getCodigo() + "";
		return codigoGenerado;
	}
	public boolean mostrarIngresoOrden(boolean validado){
		

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
			return true;
		}else{
			return false;
		}
		
	}
	
	//public boolean validarusuario
}
