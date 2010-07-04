/**
 * Resumen.
 * Objeto                     : BPedido.
 * Descripción                : Bean de pedidos .
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

/**
 * Bean de pedidos.
 *
 */
public class BPedido extends Bean {
	/**
	 * numero del pedido
	 */
	private int codigo;
	/**
	 * fecha del pedido
	 */
	private String fechaPedido;
	/**
	 * Estado de la orden de pedido.
	 */
	private int estado;
	
	/**
	 * codigo del peddido.
	 * Listado de detalle del pedido
	 */
	private Lista listaDetalle;
	
	/**
	 * cantidad de productos en la lista.
	 */
	private int cantidadProductos;
	
	/**
	 * Retorna codigo del peddido.
	 * @return codigo del peddido, tipo String.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Setea codigo del peddido.
	 * @param codigo codigo del peddido, tipo String.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna fecha de generacion del pedido.
	 * @return fecha de generacion del pedido, tipo String.
	 */
	public String getFechaPedido() {
		return fechaPedido;
	}
	/**
	 * Setea fecha de generacion del pedido.
	 * @param fechaPedido fecha de generacion del pedido, tipo String.
	 */
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	/**
	 * Retorna estado del pedido.
	 * @return estado del pedido, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea estado del pedido.
	 * @param estado estado del pedido, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * Retorna listado de detalle.
	 * @return listado de detalle, tipo Lista.
	 */
	public Lista getListaDetalle() {
		return listaDetalle;
	}
	/**
	 * Setea listado de detalle.
	 * @param listaDetalle listado de detalle, tipo Lista.
	 */
	public void setListaDetalle(Lista listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	/**
	 * Retorna cantidad de productos de la orden, tipo int.
	 * @return cantidad de productos de la orden, tipo int.
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}
	/**
	 * Setea la cantidad de productos de la orden, tipo int.
	 * @param cantidadProductos cantidad de productos de la orden, tipo int.
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	
}
