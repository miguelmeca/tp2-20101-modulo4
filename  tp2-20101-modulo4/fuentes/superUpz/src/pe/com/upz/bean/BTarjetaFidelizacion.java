/**
 * Resumen.
 * Objeto                     : BTarjetaFidelizacion.
 * Descripción                : Bean de tarjeta de fidelizacion.
 * Fecha de Creación          : 15/05/2010.
  * Autor                     : Gonzalo Azabache Carrillo.
 */
package pe.com.upz.bean;

import pe.com.upz.util.Bean;

/**
 * Bean de tarjeta de fidelizacion.
 *
 */
public class BTarjetaFidelizacion extends Bean{

	/**
	 * Cliente de la tarjeta
	 */
	private BCliente cliente;
	/**
	 * Numero de la tarjeta
	 */
	private String numero;
	/**
	 * Estado de la tarjeta
	 */
	private int estado;
	/**
	 * tipo de cliente
	 */
	private int tipoCliente;
	/**
	 * Retorna Cliente e la tarjeta.
	 * @return Cliente e la tarjeta, tipo BCliente.
	 */
	public BCliente getCliente() {
		return cliente;
	}
	/**
	 * Setea Cliente e la tarjeta.
	 * @param cliente Cliente e la tarjeta, tipo BCliente.
	 */
	public void setCliente(BCliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * Retorna numero de la tarjeta.
	 * @return numero de la tarjeta, tipo String.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * Setea numero de la tarjeta.
	 * @param numero numero de la tarjeta, tipo String.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * Retorna de la tarjeta, tipo int.
	 * @return estado de la tarjeta, tipo int.
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Setea de la tarjeta, tipo int.
	 * @param estado de la tarjeta, tipo int.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * Retorna tipo de cliente, tipo int.
	 * @return tipo de cliente, tipo int.
	 */
	public int getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * Setea tipo de cliente, tipo int.
	 * @param tipoCliente tipo de cliente, tipo int.
	 */
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	
	
}
