/**
 * Resumen.
 * Objeto                     : CCanje.
 * Descripción                : Clase controladora para el modulo de canje. 
 * Fecha de Creación          : 20/06/2010.
 * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.controlador;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import pe.com.upz.bean.BPedido;
import pe.com.upz.bean.BPedidoDetalle;
import pe.com.upz.bean.BProducto;
import pe.com.upz.bean.BSucursal;
import pe.com.upz.bean.BTipoProducto;
import pe.com.upz.bean.BUsuario;
import pe.com.upz.dao.DCuenta;
import pe.com.upz.dao.DPedido;
import pe.com.upz.dao.DPedidoDetalle;
import pe.com.upz.dao.DProducto;
import pe.com.upz.daoInterface.ICuenta;
import pe.com.upz.daoInterface.IDetallePedido;
import pe.com.upz.daoInterface.IPedido;
import pe.com.upz.daoInterface.IProducto;
import pe.com.upz.util.Lista;

/**
 * Clase controladora para el modulo de canje. 
 *
 */
public class CCanje {
	/**
	 * Almacena la orden generada.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param usuario
	 *            usuario de la sesion, tipo BUsuario.
	 * @param codigo
	 *            arreglo con los codigos de los productos a insertar tipo
	 *            String[].
	 * @param conn
	 *            conexion a la base de datos, tipo Connection.
	 * @return codigoGenerado godigo de la orden generada, tipo String.
	 * @throws SQLException
	 *             captura excepciones tipo SQL.
	 */
	public String alamcenarOrden(HttpServletRequest request, BUsuario usuario,
			int codigoProducto,int cantidad, Connection conn, BSucursal sucursal) throws SQLException {
		String codigoGenerado = "";
		Lista listaDetalle = new Lista();
		BProducto producto;
		BPedido pedido = new BPedido();
		BPedidoDetalle detalle;

		detalle = new BPedidoDetalle();
			producto = new BProducto();
			producto.setCodigo(codigoProducto);
			detalle.setProducto(producto);
			detalle.setCantidad(cantidad);
			listaDetalle.setElemento(detalle);
		
		pedido.setListaDetalle(listaDetalle);

		IPedido pedidoDao = new DPedido();
		IProducto dProducto = new DProducto();
		IDetallePedido detalleDao = new DPedidoDetalle();

		// almacena cabecera
		pedido.setCodigo(pedidoDao.almacenarOrden(conn, usuario,sucursal,3));
		// alamcena detalle
		for (int i = 0; i < pedido.getListaDetalle().getTamanio(); i++) {
			detalleDao.almacenarDetalle(conn, pedido.getCodigo(),
					(BPedidoDetalle) pedido.getListaDetalle().getElemento(i),
					usuario);
			//actualiza stok;
			dProducto.actualizarStockProductoSucursal(producto, sucursal, cantidad, conn, usuario);
		}
		
		codigoGenerado = pedido.getCodigo() + "";
		return codigoGenerado;
	}
	
	/**
	 * Actualiza el puntaje de una cuenta.
	 * @param conn conexion a la base de datos, tipo Connection.
	 * @param codcuenta codigo de la cuenta, tipo int.
	 * @param cantidadPuntos cantidad de puntos descontar, tipo int.
	 * @param usuario usuario de la sesion, tipo BUsuario.
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public void actualizarPuntaje(Connection conn, int codcuenta, int cantidadPuntos,BUsuario usuario)throws SQLException {
		ICuenta daoCuenta = new DCuenta();
		
		daoCuenta.actualizarPuntaje(conn, codcuenta, cantidadPuntos, usuario);
	}
}
