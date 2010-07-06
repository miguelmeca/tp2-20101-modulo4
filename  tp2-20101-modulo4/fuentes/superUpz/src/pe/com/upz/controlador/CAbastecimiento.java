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
import pe.com.upz.bean.BSucursal;
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
	 * Muestra la orden actual.
	 * 
	 * @param request
	 *            objeto de solicitud http, tipo HttpServletRequest.
	 * @param codigo
	 *            arreglo con los codigos de los productos a insertar tipo
	 *            String[].
	 * @return listaProductos listado con los productos, tipo Lista.
	 */
	public Lista mostrarOrden(HttpServletRequest request, String[] codigo) {
		Lista listaProductos = new Lista();
		BProducto producto;
		BTipoProducto tipo;
		for (int i = 0; i < codigo.length; i++) {
			producto = new BProducto();
			producto.setCodigo(Integer.parseInt(codigo[i]));
			producto.setDescripcion(request.getParameter("hddDescripcion"
					+ codigo[i]));
			producto.setStock(Integer.parseInt(request
					.getParameter("txtCantidad" + codigo[i])));
			tipo = new BTipoProducto();
			tipo.setDescripcion(request.getParameter("hddTipo" + codigo[i]));
			producto.setTipo(tipo);
			listaProductos.setElemento(producto);
		}
		return listaProductos;
	}

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
			String[] codigo, Connection conn,int  tipoMov) throws SQLException {
		String codigoGenerado = "";
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

		IPedido pedidoDao = new DPedido();
		IDetallePedido detalleDao = new DPedidoDetalle();

		// almacena cabecera
		pedido.setCodigo(pedidoDao.almacenarOrden(conn, usuario,tipoMov));
		// alamcena detalle
		for (int i = 0; i < pedido.getListaDetalle().getTamanio(); i++) {
			detalleDao.almacenarDetalle(conn, pedido.getCodigo(),
					(BPedidoDetalle) pedido.getListaDetalle().getElemento(i),
					usuario);
		}
		codigoGenerado = pedido.getCodigo() + "";
		return codigoGenerado;
	}

	/**
	 * Indica si no se solicita el permiso para generar una orden.
	 * 
	 * @param validado
	 *            indica si se realiza validacion del dia de la fecha, tipo boolean.
	 * @return true o false en caso se debe mostrar el permiso para generar una
	 *         orden, tipo boolean.
	 * @throws SQLException
	 */
	public boolean mostrarIngresoOrden(boolean validado) throws SQLException {

		int numeroSemana;
		int numeroDia;

		// obteniendo el nuemro de la semana
		numeroSemana = Integer.parseInt(ConnectDS
				.obtenerFechaFormato(ConnectDS.FORMATO_NUMERO_SEMANA));
		// obteneiendo el numero del dia
		numeroDia = Integer.parseInt(ConnectDS
				.obtenerFechaFormato(ConnectDS.FORMATO_NUMERO_DIA_SEMANA));

		if (validado
				|| (Parametros.NUMERO_DIA_GENERAR_PEDIDO == numeroDia && Parametros.NUMERO_SEMANA_GENERAR_PEDIDO == numeroSemana)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Obtiene el listado de ordenes generadas.
	 * @return listado de ordenes, tipo Lista.
	 * @throws SQLException
	 */
	public Lista mostrarOrdenes()throws SQLException {
		IPedido daoPedido = new DPedido();
		
		return daoPedido.obtenerListaOrdenes();
	}
	/**
	 * Obtiene detalle de orden de pedido.
	 * @param numPedido numero de pedido, tipo int.
	 * @return listado del detalle, tipo Lista.
	 * @throws SQLException
	 */
	public Lista obtenerDetalleOrden(int numPedido)throws SQLException {
		IPedido daoPedido = new DPedido();
		
		return daoPedido.obtenerDetalleOrden(numPedido);
	}
	/**
	 * Actualiza el estado de la orden.
	 * @param conn objeto de conexion, tipo Connection.
	 * @param numPedido numero de la orden del pedido. 
	 * @param usuario usuario de la sesion, tipo BUsuario
	 * @throws SQLException captura excepciones tipo SQL.
	 */
	public void actualizarOrden(Connection conn,int numPedido, BUsuario usuario, int estado)throws SQLException{
		IPedido daoPedido = new DPedido();
		daoPedido.actualizarEstadoPedido(conn, numPedido, usuario,estado);
	}
	/**
	 * Almacena la orden actualizada.
	 * 
	 * @param usuario
	 *            usuario de la sesion, tipo BUsuario.
	 * @param pedido
	 *            nuevo pedido generado, tipo
	 *            BPedido.
	 * @param conn
	 *            conexion a la base de datos, tipo Connection.
	 * @return codigoGenerado godigo de la orden generada, tipo String.
	 * @param numPedidoInicio
	 *            numero del pedido de inicio, tipo
	 *            int.
	 * @throws SQLException
	 *             captura excepciones tipo SQL.
	 */
	public String alamcenarOrdenActualizada(BUsuario usuario,
			BPedido pedido, Connection conn,int  tipoMov, int numPedidoInicio, BSucursal bSucursal) throws SQLException {
		String codigoGenerado = "";
		
		BProducto bProducto = new BProducto();
		IPedido pedidoDao = new DPedido();
		IDetallePedido detalleDao = new DPedidoDetalle();
		IProducto daoProducto = new DProducto();
		//inactiva el pedido existente
		pedidoDao.actualizarEstadoPedido(conn, numPedidoInicio, usuario, 0);
		// almacena cabecera nuevo pedido
		pedido.setCodigo(pedidoDao.almacenarOrden(conn, usuario,tipoMov));
		for (int i = 0; i < pedido.getListaDetalle().getTamanio(); i++) {
			// alamcena detalle nuevo pedido
			detalleDao.almacenarDetalle(conn, pedido.getCodigo(),
					(BPedidoDetalle) pedido.getListaDetalle().getElemento(i),
					usuario);
			//actualiza stock productos del pedido
			bProducto = ((BPedidoDetalle) pedido.getListaDetalle().getElemento(i)).getProducto();
			daoProducto.actualizarStockProductoSucursal(bProducto, bSucursal, ((BPedidoDetalle) pedido.getListaDetalle().getElemento(i)).getCantidad()*-1, conn, usuario);
		}
		
		codigoGenerado = pedido.getCodigo() + "";
		return codigoGenerado;
	}
}
