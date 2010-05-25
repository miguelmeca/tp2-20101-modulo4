/**
 * Resumen.
 * Objeto                     : BPedido.
 * Descripción                : Bean de pedidos .
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;
import pe.com.upz.util.Lista;

/**
 * Bean de pedidos .
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
	 * Listado de detalle del pedido
	 */
	private Lista listaDetalle;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Lista getListaDetalle() {
		return listaDetalle;
	}
	public void setListaDetalle(Lista listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	
}
