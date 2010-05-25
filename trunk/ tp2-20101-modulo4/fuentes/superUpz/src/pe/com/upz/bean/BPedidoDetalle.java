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
	private BProducto producto;
	private int cantidad;
	private int estado;
	public BProducto getProducto() {
		return producto;
	}
	public void setProducto(BProducto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
