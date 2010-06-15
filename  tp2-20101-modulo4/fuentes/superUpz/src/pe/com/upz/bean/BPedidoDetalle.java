/**
 * Resumen.
 * Objeto                     : BPedidoDetalle.
 * Descripción                : Bean del detalle del pedido.
 * Fecha de Creación          : 25/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean del detalle del pedido.
 *
 */
public class BPedidoDetalle extends Bean {
	/**
	 * producto del detalle.
	 */
	private BProducto producto;
	/**
	 * cantidad a solicitar
	 */
	private int cantidad;
	/**
	 * estado del detalle
	 */
	private int estado;
	/**
	 * Retorna producto del detalle.
	 * @return producto del detalle, tipo BProducto.
	 */
	public BProducto getProducto() {
		return producto;
	}
	/**
	 * Setea producto del detalle.
	 * producto del detalle, tipo BProducto.
	 * @param producto producto del detalle, tipo BProducto.
	 */
	public void setProducto(BProducto producto) {
		this.producto = producto;
	}
	/**
	 * Retorna  cantidad a pedir en el detalle.
	 * @return cantidad a pedir en el detalle, tipo int.
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Setea cantidad a pedir en el detalle.
	 * @param cantidad cantidad a pedir en el detalle, tipo int.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Retorna estado del detalle pedido.
	 * @return estado del detalle pedido, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea estado del pedido.
	 * @param estado estado del detalle pedido, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
